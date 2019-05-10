package cn.ce.framework.security.config;

import cn.ce.framework.security.common.ResourceAccessProperties;
import cn.ce.framework.security.common.AccessPatternProperties;
import cn.ce.framework.security.common.Swagger2Constants;
import cn.ce.framework.security.exception.CustomAccessDeniedHandler;
import cn.ce.framework.security.exception.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

import java.util.Map;

/**
 * @author ggs
 * @date 2019/3/9 3:47
 */
@Order(1)
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(ResourceAccessProperties.class)
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceAccessProperties resourceAccessProperties;

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired(required = false)
    private LoadBalancerClient loadBalancerClient;

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
        BalancedRemoteTokenServices balancedRemoteTokenServices = new BalancedRemoteTokenServices(loadBalancerClient);
        balancedRemoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        balancedRemoteTokenServices.setClientId(resourceServerProperties.getClientId());
        balancedRemoteTokenServices.setClientSecret(resourceServerProperties.getClientSecret());
        resources.tokenServices(balancedRemoteTokenServices);
        resources.accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    private void initWhiteLists(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        if (resourceAccessProperties.getWhiteList() != null
                && resourceAccessProperties.getWhiteList().size() != 0) {
            for (Map.Entry<String, AccessPatternProperties> entry : this.resourceAccessProperties.getWhiteList().entrySet()) {
                if (entry.getKey().equals("emptyPrefix")) {
                    addWhiteListByHttpMethod("", entry.getValue(), expressionInterceptUrlRegistry);
                } else {
                    addWhiteListByHttpMethod("/" + entry.getKey(), entry.getValue(), expressionInterceptUrlRegistry);
                }
            }
        }

    }

    private void addWhiteListByHttpMethod(String prefix, AccessPatternProperties accessPatternProperties, ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {

        if (accessPatternProperties.getSwaggerUrl() != null) {
            if (accessPatternProperties.getSwaggerUrl().length == 1 &&
                    accessPatternProperties.getSwaggerUrl()[0].equals("default")) {
                expressionInterceptUrlRegistry
                        .antMatchers(HttpMethod.GET, jointPrefix(prefix, Swagger2Constants.swaggerPattern)).permitAll();
            } else {
                expressionInterceptUrlRegistry
                        .antMatchers(HttpMethod.GET, jointPrefix(prefix, accessPatternProperties.getSwaggerUrl())).permitAll();
            }
        }
        if (accessPatternProperties.getHttpGet() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.GET, jointPrefix(prefix, accessPatternProperties.getHttpGet())).permitAll();
        }
        if (accessPatternProperties.getHttpPost() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.POST, jointPrefix(prefix, accessPatternProperties.getHttpPost())).permitAll();
        }
        if (accessPatternProperties.getHttpPut() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.PUT, jointPrefix(prefix, accessPatternProperties.getHttpPut())).permitAll();
        }
        if (accessPatternProperties.getHttpDelete() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(HttpMethod.DELETE, jointPrefix(prefix, accessPatternProperties.getHttpDelete())).permitAll();
        }
        if (accessPatternProperties.getHttpAllMethod() != null) {
            expressionInterceptUrlRegistry
                    .antMatchers(jointPrefix(prefix, accessPatternProperties.getHttpAllMethod())).permitAll();
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
