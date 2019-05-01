package cn.ce.framework.security.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: ggs
 * @date: 2019-03-25 18:08
 **/
@Data
@ConfigurationProperties(prefix = "security.oauth2.resource")
public class SecurityWhiteListProperty {
    private Map<String, ServicePath> whiteList = new LinkedHashMap<>();
}
