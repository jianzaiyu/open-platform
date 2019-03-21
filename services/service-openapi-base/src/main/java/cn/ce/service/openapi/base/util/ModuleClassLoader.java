package cn.ce.service.openapi.base.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


import cn.ce.service.openapi.base.annotation.InterfaceDescriptionFullEnty;

public class ModuleClassLoader extends URLClassLoader {
	/** 日志对象 */
	private static ModuleClassLoader instance;
	private static URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	private static final Method ADD_URL = initAddMethod();

	static {
		ClassLoader.registerAsParallelCapable();
	}

	private ModuleClassLoader(URL[] urls) {
		super(urls);
	}

	public static ModuleClassLoader getInstance() {
		if (instance == null) {
			instance = new ModuleClassLoader(new URL[] {});
		}
		return instance;
	}

	private static Method initAddMethod() {
		try {
			Method addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			addUrl.setAccessible(true);
			return addUrl;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public void loadJar(URL url) {
		try {
			ADD_URL.invoke(classLoader, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 根椐jar路径解析注解信息 
     * @return key :jarName
     * @param jarFilePath
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public  Map<String, Map<String, InterfaceDescriptionFullEnty>>  parseJars(String[] jarFilePath) throws URISyntaxException, IOException, ClassNotFoundException {
		ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
		//key :jarName
		Map<String, Map<String, InterfaceDescriptionFullEnty>> resultMap = new HashMap<String, Map<String, InterfaceDescriptionFullEnty>>();
		for (String path : jarFilePath) {
			File f = new File(path);
			URL url = new URL("file:"+f.getPath());
			moduleClassLoader.setDefaultAssertionStatus(false);
			moduleClassLoader.loadJar(url.toURI().toURL());
		}
		       
		for (String path : jarFilePath) {
			Map<String, InterfaceDescriptionFullEnty> map = new HashMap<String, InterfaceDescriptionFullEnty>();
			JarFile localJarFile = new JarFile(path);
			Enumeration<JarEntry> entries = localJarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry jarEntry = entries.nextElement();
				if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
					continue;
				String clazz = jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace("/", ".");
				Class<?> spawnClass = Class.forName(clazz);
				Map<String, InterfaceDescriptionFullEnty> maptmp = CustomAnnotationUtils.initJsonServiceMap(spawnClass);
				map.putAll(maptmp);
			}
			resultMap.put(new File(path).getName(), map);
		}
		return resultMap;
	}


	public static void main(String[] args) throws Exception {
//		URL url1 = new URL("file:D:/lib/appservice-info-api-2.0.0-SNAPSHOT.jar");
//		URL url2 = new URL("file:D:/lib/service-info-api-2.0.0-SNAPSHOT.jar");
//		ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
//		moduleClassLoader.setDefaultAssertionStatus(false);
//		moduleClassLoader.loadJar(url1.toURI().toURL());
//		moduleClassLoader.loadJar(url2.toURI().toURL());
//		System.out.println("加载成功");  
//		Class<?> spawnClass = Class.forName("cn.ce.ebiz.info.service.AppInfoService"); // AppInfoCategoryService
//		spawnClass.getDeclaredMethods();
//		CustomAnnotationUtils.initJsonServiceMap(spawnClass);
		
		
		ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
		//String[] path = new String[]{"D:/lib/appservice-info-api-2.0.0-SNAPSHOT.jar","D:/lib/service-info-api-2.0.0-SNAPSHOT.jar"};
		String[] path = new String[]{"D:/lib/service-info-api-2.0.0-SNAPSHOT.jar","D:/lib/appservice-info-api-2.0.0-SNAPSHOT.jar"};
		moduleClassLoader.parseJars(path);
	}

}