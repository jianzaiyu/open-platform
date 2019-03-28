package cn.ce.framework.security.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: ggs
 * @date: 2019-03-25 18:08
 **/
@Data
@ConfigurationProperties(prefix = "security.oauth2.resource.white-list")
public class SecurityWhiteListProperty {
    private String[] swaggerUrl;
    private String[] httpGet;
    private String[] httpPost;
    private String[] httpPut;
    private String[] httpDelete;
}
