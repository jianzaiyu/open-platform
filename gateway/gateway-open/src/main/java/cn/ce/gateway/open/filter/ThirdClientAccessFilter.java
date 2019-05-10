package cn.ce.gateway.open.filter;

import cn.ce.framework.redis.support.RedisUtil;
import cn.ce.gateway.open.config.CustomRouteLocator;
import cn.ce.gateway.open.service.GatewayService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * @author: ggs
 * @date: 2019-03-26 20:37
 **/
@Slf4j
@Component
public class ThirdClientAccessFilter extends ZuulFilter {
    @Autowired
    private CustomRouteLocator customRouteLocator;

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 网关代码不能为了简洁牺牲性能.
     * 要以性能为重构目标.
     * 逐步优化速度.
     * 这个方法是逻辑最复杂的地方,
     * 要懂得开放平台与生产的业务
     * 还得深入理解zuul的底层实现原理
     * 否则看不懂
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //限流
        if(!isRateLimit(ctx)){
            //路由
            updateRoute(ctx);
        }
        return null;
    }

    private boolean isRateLimit(RequestContext ctx) {
        HttpServletRequest request = ctx.getRequest();
        String clientId = request.getHeader("App-Key");
        Integer rate = gatewayService.getClientToRate().get(clientId);
        Long remaining = 0L;
        if (rate != null) {
            Long limit = Long.valueOf(rate);
            String key = "rateLimit_" + clientId;
            try {

                Long expire = this.redisUtil.getExpire(key);
                if (expire == null || expire == -1) {
                    this.redisUtil.expire(key, 60, SECONDS);
                }
            } catch (RuntimeException e) {
                String msg = "Failed retrieving expiration for " + key + ", will reset now";
                log.info(msg, e);
            }
            Long current = 0L;
            try {
                current = this.redisUtil.increment(key, 1L);
            } catch (RuntimeException e) {
                String msg = "Failed retrieving rate for " + key + ", will return limit";
                log.info(msg, e);
            }
            remaining = Math.max(-1, limit - current);
        }
        if (remaining < 0) {
            HttpStatus tooManyRequests = HttpStatus.TOO_MANY_REQUESTS;
            ctx.setResponseStatusCode(tooManyRequests.value());
            ctx.setSendZuulResponse(false);
            try {
                ctx.getResponse().getWriter().write("Request too many times");
            }catch (IOException e){
                String msg = "Failed to get ctx Writer when rate limit";
                log.info(msg, e);
            }
            return true;
        }
        return false;
    }


    private void updateRoute(RequestContext ctx) {
        HttpServletRequest request = ctx.getRequest();
        String path = new UrlPathHelper().getRequestUri(request);
        String clientId = request.getHeader("App-Key");
        String version = request.getHeader("api-version");

        //找 finalUrl
        String tenantId = gatewayService.getClientToSaas().get(clientId);
        String resourceType = gatewayService.getPathVersionToResourceType().get(path + version);
        String finalUrl = gatewayService.getSaasTypeToUrl().get(tenantId + resourceType);

        //添加 tentantId 参数
        Map<String, List<String>> requestQueryParams = new HashMap<>();
        requestQueryParams.put("tenantId", Collections.singletonList(tenantId));
        if (ctx.getRequestQueryParams() != null) {
            requestQueryParams.putAll(ctx.getRequestQueryParams());
        }
        ctx.setRequestQueryParams(requestQueryParams);

        //更新 zuul路由表 如果找不到就用默认targetUrl
        if (!StringUtils.isEmpty(finalUrl)) {
            finalUrl = finalUrl.startsWith(HTTP_SCHEME + ":") || finalUrl.startsWith(HTTPS_SCHEME + ":")
                    ? finalUrl : HTTP_SCHEME + "://" + finalUrl;
            Map<String, ZuulProperties.ZuulRoute> params = new LinkedHashMap<>();
            params.put(path, new ZuulProperties.ZuulRoute(path, finalUrl));
            this.customRouteLocator.modifyRouteMap(params);
        }
    }

}
