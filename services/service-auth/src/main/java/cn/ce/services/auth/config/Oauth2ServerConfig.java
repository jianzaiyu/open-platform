package cn.ce.services.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;


/**
 * @author ggs
 * @date 2019/3/3 23:53
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

//    @Bean
//    @Primary
//    public CustomExceptionTranslationFilter exceptionTranslationFilter(){
//        CustomExceptionTranslationFilter exceptionTranslationFilter
//                = new CustomExceptionTranslationFilter(new CustomAuthenticationEntryPoint());
//        exceptionTranslationFilter.setAccessDeniedHandler(new CustomAccessDeniedHandler());
//        return exceptionTranslationFilter;
//    }

    /**
     * client_id,client_secret不正确时
     * Basic认证不通过时，返回异常信息没有重新定义
     * 感觉没有必要实现这个  没有通过这个认证的 不在服务范围之列
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
        //                .addTokenEndpointAuthenticationFilter(new CustomExceptionTranslationFilter(new CustomAuthenticationEntryPoint()));
//                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
//        defaultAccessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
//        new JdbcTokenStore(dataSource)
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .accessTokenConverter(defaultAccessTokenConverter)
                .exceptionTranslator(new CustomWebResponseExceptionTranslator())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .authenticationManager(authenticationManager);
//                .userDetailsService(userDetailsService);
    }

}
