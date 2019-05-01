package cn.ce.gateway.filter;

import cn.ce.gateway.entity.TargetUrlAndTenantIdEntity;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author: ggs
 * @date: 2019-03-26 20:37
 **/
public class ThirdClientFilter extends ZuulFilter {
    private AtomicReference<Map<String, TargetUrlAndTenantIdEntity>> pathUrlRelation;

    private RestTemplate restTemplate;

    public ThirdClientFilter(AtomicReference<Map<String, TargetUrlAndTenantIdEntity>> pathUrlRelation,
                             RestTemplate restTemplate) {
        this.pathUrlRelation = pathUrlRelation;
        this.restTemplate = restTemplate;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        //获取浏览器信息
////        String userAgentInfo = request.getHeader("User-Agent");
//        String basicAuth = request.getHeader("Authorization");
//        String appkey = request.getHeader("App-Key");
//        String version = request.getHeader("api-version");
//        return !StringUtils.isEmpty(basicAuth)
//                && !StringUtils.isEmpty(appkey)
//                && !StringUtils.isEmpty(version);
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String appkey = request.getHeader("App-Key");
        String version = request.getHeader("api-version");
        String RequestURI = request.getRequestURI();
        String superRequestURI = "/" + appkey + "/" + version + RequestURI;
//        TargetUrlAndTenantIdEntity targetUrlAndTenantIdEntity = pathUrlRelation.get().get(superRequestURI);
//        String finalUrl = targetUrlAndTenantIdEntity.getTargetUrl();
//        String tenantId = targetUrlAndTenantIdEntity.getTenantId();
        String body = null;
        try {
            body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.print(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> headers = Collections.list(((HttpServletRequest) request).getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, ((HttpServletRequest) request)::getHeader));
        ;

        MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();
        stringMultiValueMap.setAll(headers);
        HttpEntity<String> entity = new HttpEntity<String>(body, stringMultiValueMap);
        restTemplate.exchange("http://localhost:8080/uaa/oauth/token", HttpMethod.POST, entity, Map.class);
        return "sssss";
    }
}
