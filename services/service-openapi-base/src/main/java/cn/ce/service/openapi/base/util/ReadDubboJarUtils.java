package cn.ce.service.openapi.base.util;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import cn.ce.service.openapi.base.dubbapply.entity.DubboApplyEntity;

public class ReadDubboJarUtils {

	/**
	 * 读取dubbo jar文件获取DubboApplyEntity
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public List<DubboApplyEntity> getDubboApplyEntity(List<String> filePath) throws IOException {
		// 1 load jar to jvm
		// 2 get JarURLConnection
		// 3 assembly List
		List<DubboApplyEntity> listdubboapplyenty = new ArrayList<>();
		ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
		for (int i = 0; i < filePath.size(); i++) {
			URL url = new URL(filePath.get(i));
			moduleClassLoader.loadJar(url);

			JarURLConnection connection = (JarURLConnection) url.openConnection();
			DubboApplyEntity de = new DubboApplyEntity();
			JarFile jarFile = connection.getJarFile();
		}

		return null;
	}

}
