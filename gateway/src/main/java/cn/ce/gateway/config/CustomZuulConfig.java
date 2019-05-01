package cn.ce.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @author: ggs
 * @date: 2019-04-28 15:43
 **/
@Configuration
public class CustomZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    DispatcherServletPath dispatcherServletPath;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public CustomRouteLocator routeLocator() {
        CustomRouteLocator routeLocator = new CustomRouteLocator(dispatcherServletPath.getPrefix(), this.zuulProperties);
        routeLocator.setJdbcTemplate(jdbcTemplate);
        return routeLocator;
    }

    //    @Bean
//    public CustomPatternServiceRouteMapper customPatternServiceRouteMapper(){
//        return new CustomPatternServiceRouteMapper("","");
//    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public ThirdClientFilter thirdClientFilter(CustomRouteLocator customRouteLocator, RestTemplate restTemplate) {
//        return new ThirdClientFilter(customRouteLocator.getPathUrlRelation(), restTemplate);
//    }


}
