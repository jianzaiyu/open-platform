package cn.ce.service.openapi.console;

import cn.ce.framework.base.annotation.BusinessApplicationBase;
import org.springframework.boot.SpringApplication;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@BusinessApplicationBase(scanBasePackages = {"cn.ce.service"})
public class ServiceConsoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsoleApplication.class, args);
    }
}
