package cn.ce.gateway.filter;

import cn.ce.gateway.config.CustomRouteLocator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.HTTPS_SCHEME;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.HTTP_SCHEME;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * @author: ggs
 * @date: 2019-03-26 20:37
 **/
public class ThirdClientAccessFilter extends ZuulFilter {

    private CustomRouteLocator customRouteLocator;

    public ThirdClientAccessFilter(CustomRouteLocator customRouteLocator){
        this.customRouteLocator = customRouteLocator;
    }

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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //获取浏览器信息
//        String userAgentInfo = request.getHeader("User-Agent");
        String basicAuth = request.getHeader("Authorization");
        String appkey = request.getHeader("App-Key");
        String version = request.getHeader("api-version");
        return !StringUtils.isEmpty(basicAuth)
                && !StringUtils.isEmpty(appkey)
                && !StringUtils.isEmpty(version);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String path = new UrlPathHelper().getRequestUri(request);
        Route route = this.customRouteLocator.getMatchingRoute(path);
        Map<String, String> finalMap = this.customRouteLocator.getFinalUrlRelation().get();
        String apiDetalInfo = route.getLocation();
        String clientId = request.getHeader("App-Key");
        String finalUrl = null;
        if (apiDetalInfo != null) {
            if (!apiDetalInfo.contains(";")) {
                String[] api = apiDetalInfo.split(",");
                String defaultTargetUrl = api[0];
                String resourceType = api[1];
                finalUrl = finalMap.get(clientId + resourceType);
                finalUrl = StringUtils.isEmpty(finalUrl) ? defaultTargetUrl : finalUrl;
            } else {
                String versionParam = request.getHeader("api-version");
                String[] versions = apiDetalInfo.split(";");
                for (String version : versions) {
                    String[] api = version.split(",");
                    if (!api[2].equals(versionParam)) continue;
                    String defaultTargetUrl = api[0];
                    String resourceType = api[1];
                    finalUrl = finalMap.get(clientId + resourceType);
                    finalUrl = StringUtils.isEmpty(finalUrl) ? defaultTargetUrl : finalUrl;
                }
            }
        }
        finalUrl = finalUrl.startsWith(HTTP_SCHEME + ":") || finalUrl.startsWith(HTTPS_SCHEME + ":")
                ? finalUrl : HTTP_SCHEME + ":" + finalUrl;
        Map<String, ZuulProperties.ZuulRoute> params = new LinkedHashMap<>();
        params.put(path, new ZuulProperties.ZuulRoute(path, finalUrl));
        this.customRouteLocator.modifyRouteMap(params);
        return null;
    }
}
