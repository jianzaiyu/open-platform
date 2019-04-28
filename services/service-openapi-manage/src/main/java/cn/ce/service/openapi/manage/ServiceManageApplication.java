package cn.ce.service.openapi.manage;

import cn.ce.framework.base.annotation.BusinessApplicationBase;
import org.springframework.boot.SpringApplication;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@BusinessApplicationBase(componentPackages = {"cn.ce.service"},
        feignBasePackages = {"cn.ce.service"})
public class ServiceManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManageApplication.class, args);
    }
}
