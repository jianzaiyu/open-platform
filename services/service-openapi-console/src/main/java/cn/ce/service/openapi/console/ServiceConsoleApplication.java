package cn.ce.service.openapi.console;

import cn.ce.framework.base.annotation.BusinessApplicationBase;
import org.springframework.boot.SpringApplication;

@BusinessApplicationBase(scanBasePackages = {"cn.ce"})
public class ServiceConsoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsoleApplication.class, args);
    }
}
