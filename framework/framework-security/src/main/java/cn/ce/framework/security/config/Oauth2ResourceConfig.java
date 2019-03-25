package cn.ce.framework.security.config;

import cn.ce.framework.security.common.SecurityWhiteListProperty;
import cn.ce.framework.security.exception.CustomAccessDeniedHandler;
import cn.ce.framework.security.exception.CustomAuthenticationEntryPoint;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityWhiteListProperty.class)
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().authorizeRequests()// swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // swagger end
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/forget").permitAll()
                .antMatchers(HttpMethod.POST, "/mail/*").permitAll()
                .antMatchers(HttpMethod.GET, "/user/duplicate/username/*").permitAll()
                .antMatchers(HttpMethod.GET, "/user/duplicate/email/*").permitAll()
                .antMatchers(HttpMethod.GET, "/user/username/*").permitAll()
                .antMatchers(HttpMethod.GET, "/code").permitAll()
                .antMatchers(HttpMethod.POST, "/code").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
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

    public ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry whiteList(){
        return null;
    }
}
