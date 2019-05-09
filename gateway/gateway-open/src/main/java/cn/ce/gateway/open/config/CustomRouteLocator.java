package cn.ce.gateway.open.config;

import cn.ce.gateway.open.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ggs
 * @date: 2019-04-28 15:43
 **/
@Slf4j
@Component
public class CustomRouteLocator extends SimpleRouteLocator {

    @Autowired
    private GatewayService gatewayService;

    private ZuulProperties properties;

    public CustomRouteLocator(@Autowired DispatcherServletPath dispatcherServletPath, @Autowired ZuulProperties zuulProperties) {
        super(dispatcherServletPath.getPrefix(), zuulProperties);
        this.properties = zuulProperties;
        log.info("servletPath:{}", dispatcherServletPath.getPrefix());
    }

    public void doRefresh() {
        super.doRefresh();
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        gatewayService.builtMeta();
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        //优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<Map<String, String>> list = gatewayService.selectPathVersionResourceMap();
        for (Map<String, String> map : list) {
            String path = map.get("listen_path");
            if (!StringUtils.isEmpty(path)) {
                ZuulRoute zuulRoute = new ZuulRoute();
                zuulRoute.setRetryable(false);
                zuulRoute.setStripPrefix(false);
                zuulRoute.setPath(path);
                zuulRoute.setId(path);
                zuulRoute.setUrl(map.get("default_target_url"));
                routes.put(zuulRoute.getPath(), zuulRoute);
            }
        }
        return routes;
    }

    public void modifyRouteMap(Map<String, ZuulRoute> params) {
        Map<String, ZuulRoute> routeMap = getRoutesMap();
        routeMap.putAll(params);
    }
}
