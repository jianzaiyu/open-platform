package cn.ce.framework.base.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: ggs
 * @date: 2018-08-15 09:55
 * 注意写法 静态属性无法注入！
 **/
@Component
@ConfigurationProperties(prefix = "pcp")
public class PropertySupport {

    private static final String uploadFolder_win = "c:/uploadFiles/";
    private static String uploadFolder;
    private static String staticAccessPath;
    private static String passport;


    public  void setUploadFolder(String uploadFolder) {
        PropertySupport.uploadFolder = uploadFolder;
    }

    public static String getStaticAccessPath() {
        return staticAccessPath;
    }

    public  void setStaticAccessPath(String staticAccessPath) {
        PropertySupport.staticAccessPath = staticAccessPath;
    }

    public static String getPassport() {
        return passport;
    }

    public  void setPassport(String passport) {
        PropertySupport.passport = passport;
    }

    public static String getUploadFolder() {
        String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Windows")) {
            return uploadFolder_win;
        }
        return uploadFolder;
    }


}
