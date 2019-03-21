package cn.ce.service.openapi.base.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 
 * @ClassName: PropertiesFileUtil
 * @Description: for read config files.
 * @author dingjia@300.cn
 *
 */
public class PropertiesFileUtil {

	public static Properties getPropertiesByAbsPath(String fileAbsPath) throws IOException {
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			inputStream = new BufferedInputStream(
					new FileInputStream(java.net.URLDecoder.decode(fileAbsPath, "UTF-8")));
			properties.load(inputStream);
			return properties;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}		
		}
	}

	public static Properties getPropertiesByClassPath(String fileClassPath) throws IOException {
		return getPropertiesByAbsPath(getFileAbsPath(fileClassPath));
	}

	public static String getFileAbsPath(String fileClassPath) throws IOException {
		Resource resource = new ClassPathResource(fileClassPath);
		URL url = resource.getURL();
		return url.getFile();
	}

}
