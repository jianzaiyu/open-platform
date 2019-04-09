package cn.ce.framework.security.config;

import cn.ce.framework.security.common.SecurityWhiteListProperty;
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
        initWhiteList(expressionInterceptUrlRegistry);
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

    public void initWhiteList(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        if (securityWhiteListProperty.getSwaggerUrl() != null) {
            if (securityWhiteListProperty.getSwaggerUrl().length == 1 &&
                    securityWhiteListProperty.getSwaggerUrl()[0].equals("default")) {
                expressionInterceptUrlRegistry
                        .antMatchers(HttpMethod.GET, Swagger2Constants.swaggerPattern).permitAll();
            } else {
                expressionInterceptUrlRegistry
                        .antMatchers(HttpMethod.GET, securityWhiteListProperty.getSwaggerUrl()).permitAll();
            }
        }
        if (securityWhiteListProperty.getHttpGet() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.GET, securityWhiteListProperty.getHttpGet()).permitAll();
        }
        if (securityWhiteListProperty.getHttpPost() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.POST, securityWhiteListProperty.getHttpPost()).permitAll();
        }
        if (securityWhiteListProperty.getHttpPut() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.PUT, securityWhiteListProperty.getHttpPut()).permitAll();
        }
        if (securityWhiteListProperty.getHttpDelete() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.DELETE, securityWhiteListProperty.getHttpDelete()).permitAll();
        }
    }
}
