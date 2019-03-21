package cn.ce.service.openapi.base.util;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {

    @Autowired
    private Environment environment;


    public String getValue(String key) {
        return environment.getProperty(key);
    }


    public String getSourceConfigValue(String sourceConfig, String key) {
        if (StringUtils.isNotBlank(sourceConfig)) {
            return environment.getProperty(sourceConfig + "." + key);
        } else {
            return environment.getProperty(key);
        }
    }
}