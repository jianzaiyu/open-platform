package cn.ce.service.openapi.base.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.ce.service.openapi.base.annotation.InterfaceDescription;
import cn.ce.service.openapi.base.annotation.InterfaceDescriptionFullEnty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 返回自定义标签接口描述的公共方法
 * 
 * @author lenovo
 *
 */
public class CustomAnnotationUtils {

	private static Log logger = LogFactory.getLog(CustomAnnotationUtils.class);

	public static Map<String, InterfaceDescriptionFullEnty> initJsonServiceMap(Class clazz) throws ClassNotFoundException {
		Map<String, InterfaceDescriptionFullEnty> ServiceMap = new HashMap<>();
		Method[] method = clazz.getDeclaredMethods();
		for (int i = 0; i < method.length; i++) {
			method[i].getDeclaredAnnotation(InterfaceDescription.class);
			InterfaceDescription service = (InterfaceDescription) method[i].getAnnotation(InterfaceDescription.class);
			InterfaceDescriptionFullEnty ide = new InterfaceDescriptionFullEnty();
			if (service != null) {
				ide.setName(service.name());
				ide.setDes(service.des());
				ide.setVersion(service.version());
				ide.setClassname(method[i].getDeclaringClass().getName());
				ide.setMethod(method[i].getName());
				ide.setClassname(method[i].getDeclaringClass().getName());
				ide.setMethod(method[i].getName());
				int index = method[i].getDeclaringClass().getName().lastIndexOf(".");
				ide.setPackagename(method[i].getDeclaringClass().getName().substring(0, index));
				ServiceMap.put(method[i].getDeclaringClass().getName() + "." + method[i].getName(), ide);
				logger.info(service.name() + ">>>" + service.des() + ">>>" + service.version() + "----->"
						+ method[i].getDeclaringClass().getName() + "." + method[i].getName());
				System.out.println(service.name() + ">>>" + service.des() + ">>>" + service.version() + "----->"
						+ method[i].getDeclaringClass().getName() + "." + method[i].getName());
			}
		}
		return ServiceMap;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Class clzz = ClassLoader.getSystemClassLoader()
				.loadClass("cn.ce.service.openapi.base.apis.service.IApiTransportService");
		CustomAnnotationUtils.initJsonServiceMap(clzz);
	}
}
