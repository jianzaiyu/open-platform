package cn.ce.gateway.config;

import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;

/**
 * @author: ggs
 * @date: 2019-04-30 10:12
 **/
public class CustomPatternServiceRouteMapper extends PatternServiceRouteMapper {
    public CustomPatternServiceRouteMapper(String servicePattern, String routePattern) {
        super(servicePattern, routePattern);
    }

    @Override
    public String apply(String serviceId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        return "";
    }
}
