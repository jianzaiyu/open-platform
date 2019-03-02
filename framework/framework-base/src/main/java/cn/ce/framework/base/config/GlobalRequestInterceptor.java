package cn.ce.framework.base.config;

import cn.ce.framework.base.annotation.AccessPermit;
import cn.ce.framework.base.common.AccessAuthenticationService;
import cn.ce.framework.base.support.RequestLogSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: ggs
 * @date: 2018-10-15 17:11
 **/
@Component
public class GlobalRequestInterceptor extends HandlerInterceptorAdapter {
    @Autowired(required = false)
    private AccessAuthenticationService accessAuthenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestLogSupport.handleLog(request);
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //类、方法级别带有@AccessPermit注解不校验权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class clazz = handlerMethod.getBeanType();
        Method method = handlerMethod.getMethod();
        if (clazz.isAnnotationPresent(AccessPermit.class) || method.isAnnotationPresent(AccessPermit.class)) {
            return true;
        } else {
            if (accessAuthenticationService == null) {
                return true;
            }
            return accessAuthenticationService.hasPermit(request, response, handler);//restTemplate
        }
    }
}
