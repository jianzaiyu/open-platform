package cn.ce.framework.base.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: ggs
 * @date: 2018-08-15 09:55
 * 注意写法 静态属性无法注入！
 **/
@Data
@ConfigurationProperties(prefix = "server")
public class UploadPathProperty {

    private static final String uploadFolder_win = "c:/uploadFiles/";
    private String uploadFolder;
    private String staticAccessPath;
    private String passport;

    public String getUploadFolder() {
        String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Windows")) {
            return uploadFolder_win;
        }
        return uploadFolder;
    }


}
