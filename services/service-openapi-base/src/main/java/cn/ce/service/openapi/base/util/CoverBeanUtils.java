package cn.ce.service.openapi.base.util;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* @Description : BeanUtils默认会将基本数据类型的包装类的null赋值为0，该类能够解决将null赋值给null
* @Author : makangwei
* @Date : 2018年2月8日
*/
public class CoverBeanUtils {

	static {
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		}

	private static final Logger _LOGGER = LoggerFactory.getLogger(CoverBeanUtils.class);
	
	public static boolean copyProperties(Object dest, Object orig) {
		
			try {
				BeanUtils.copyProperties(dest, orig);
				return true;
			} catch (IllegalAccessException | InvocationTargetException e) {
				_LOGGER.error("bean复制异常:/n");
				e.printStackTrace();
				return false;
			}

	}
	
	public static boolean copyProperty(Object bean, String name, Object value){
		try {
			BeanUtils.copyProperty(bean, name, value);
			return true;
		} catch (IllegalAccessException | InvocationTargetException e) {
			_LOGGER.error("复制参数发生异常：");
			_LOGGER.error("bean："+bean);
			_LOGGER.error("param key:"+name);
			_LOGGER.error("param value:"+value);
			e.printStackTrace();
			return false;
		}
	}
}
