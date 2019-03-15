package cn.ce.services.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.*;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.sql.DataSource;


/**
 * @author ggs
 * @date 2019/3/3 23:53
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

//    @Bean
//    @Primary
//    public CustomExceptionTranslationFilter exceptionTranslationFilter(){
//        CustomExceptionTranslationFilter exceptionTranslationFilter
//                = new CustomExceptionTranslationFilter(new CustomAuthenticationEntryPoint());
//        exceptionTranslationFilter.setAccessDeniedHandler(new CustomAccessDeniedHandler());
//        return exceptionTranslationFilter;
//    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .addTokenEndpointAuthenticationFilter(new CustomExceptionTranslationFilter(new CustomAuthenticationEntryPoint()));
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                .accessDeniedHandler(new CustomAccessDeniedHandler());
//                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(new JdbcTokenStore(dataSource))
                .exceptionTranslator(new CustomWebResponseExceptionTranslator())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .authenticationManager(authenticationManager);
    }

}
