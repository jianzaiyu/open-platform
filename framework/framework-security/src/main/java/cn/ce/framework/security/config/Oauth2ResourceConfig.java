package cn.ce.framework.security.config;

import cn.ce.framework.security.common.SecurityWhiteListProperty;
import cn.ce.framework.security.common.ServicePath;
import cn.ce.framework.security.common.Swagger2Constants;
import cn.ce.framework.security.exception.CustomAccessDeniedHandler;
import cn.ce.framework.security.exception.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * @author ggs
 * @date 2019/3/9 3:47
 */
@Order(1)
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityWhiteListProperty.class)
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityWhiteListProperty securityWhiteListProperty;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry
                = http.httpBasic().disable().authorizeRequests();
        initWhiteLists(expressionInterceptUrlRegistry);
        expressionInterceptUrlRegistry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().disable()
                .csrf().disable()
                .cors().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    private void initWhiteLists(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        if (securityWhiteListProperty.getWhiteList() != null
                && securityWhiteListProperty.getWhiteList().size() != 0) {
            for (Map.Entry<String, ServicePath> entry : this.securityWhiteListProperty.getWhiteList().entrySet()) {
                if (entry.getKey().equals("emptyPrefix")) {
                    addWhiteListByHttpMethod("", entry.getValue(), expressionInterceptUrlRegistry);
                } else {
                    addWhiteListByHttpMethod("/"+entry.getKey(), entry.getValue(), expressionInterceptUrlRegistry);
                }
            }
        }

    }

    private void addWhiteListByHttpMethod(String prefix, ServicePath servicePath, ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {

        if (servicePath.getSwaggerUrl() != null) {
            if (servicePath.getSwaggerUrl().length == 1 &&
                    servicePath.getSwaggerUrl()[0].equals("default")) {
                expressionInterceptUrlRegistry
                        .antMatchers(HttpMethod.GET, jointPrefix(prefix, Swagger2Constants.swaggerPattern)).permitAll();
            } else {
                expressionInterceptUrlRegistry
                        .antMatchers(HttpMethod.GET, jointPrefix(prefix, servicePath.getSwaggerUrl())).permitAll();
            }
        }
        if (servicePath.getHttpGet() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.GET, jointPrefix(prefix, servicePath.getHttpGet())).permitAll();
        }
        if (servicePath.getHttpPost() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.POST, jointPrefix(prefix, servicePath.getHttpPost())).permitAll();
        }
        if (servicePath.getHttpPut() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.PUT, jointPrefix(prefix, servicePath.getHttpPut())).permitAll();
        }
        if (servicePath.getHttpDelete() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.DELETE, jointPrefix(prefix, servicePath.getHttpDelete())).permitAll();
        }
        if (servicePath.getHttpAllMethod() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(jointPrefix(prefix, servicePath.getHttpAllMethod())).permitAll();
        }
    }

    private String[] jointPrefix(String prefix, String[] methodUrl) {
        String[] finalMethodUrl = new String[methodUrl.length];
        for (int i = 0; i < methodUrl.length; i++) {
            finalMethodUrl[i] = prefix + methodUrl[i];
        }
        return finalMethodUrl;
    }
}
