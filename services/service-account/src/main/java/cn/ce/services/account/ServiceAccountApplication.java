package cn.ce.services.account;

import cn.ce.framework.base.annotation.BusinessApplicationBase;
import org.springframework.boot.SpringApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@BusinessApplicationBase
public class ServiceAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAccountApplication.class, args);
    }
}
