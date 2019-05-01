package cn.ce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@RefreshScope
@EnableZuulProxy
//@EnableResourceServer
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GatewayApplication {

//    @Bean
//    public ThirdClientAccessFilter thirdClientFilter() {
//        return new ThirdClientAccessFilter();
//    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
