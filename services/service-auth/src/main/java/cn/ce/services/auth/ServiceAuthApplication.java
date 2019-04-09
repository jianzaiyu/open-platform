package cn.ce.services.auth;

import cn.ce.framework.base.annotation.BusinessApplicationBase;
import org.springframework.boot.SpringApplication;

@BusinessApplicationBase(scanBasePackages = {"cn.ce"})
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
