package cn.ce.framework.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GGs
 **/
@Data
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2Properties {

    private String basePackage = "cn.ce";
    private String title;
    private String description;
    private String version;
    private String apiName;
    private String apiKeyName;
    private String termsOfServiceUrl;
    private LinkedHashMap<String, String> serviceSources;
    private boolean gateway;

}
