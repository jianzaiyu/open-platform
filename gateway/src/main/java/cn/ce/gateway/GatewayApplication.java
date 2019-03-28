package cn.ce.gateway;

import cn.ce.gateway.filter.GatewayErrorFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GatewayApplication {

//    @Bean
//    public TokenFilter tokenFilter() {
//        return new TokenFilter();
//    }
//
//    @Bean
//    public GatewayErrorFilter gatewayErrorFilter() {
//        return new GatewayErrorFilter();
//    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
