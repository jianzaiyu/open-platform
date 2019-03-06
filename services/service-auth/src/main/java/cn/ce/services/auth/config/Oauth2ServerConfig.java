package cn.ce.services.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * @author ggs
 * @date 2019/3/3 23:53
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("browser").secret("{bcrypt}$2a$10$EYdy3ks6rzIj5yRav/4O5OV0VIBNcA7iAA/rsghW4wD9wYbLE5gZS")
                .authorizedGrantTypes("refresh_token", "password", "implicit")
                .scopes("read", "write").authorities("ROLE_BROWSER")
                .and()
                .withClient("service-account").secret("{bcrypt}$2a$10$EYdy3ks6rzIj5yRav/4O5OV0VIBNcA7iAA/rsghW4wD9wYbLE5gZS")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("read", "write").authorities("ROLE_CLIENT");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

}
