package cn.ce.service.openapi.base.util;

import java.util.UUID;

/**
* @Description : 用于生成唯一的主键
* @Author : makangwei
* @Date : 2018年1月16日
*/
public class UUIDUtil {

	private UUIDUtil(){}
	
	/**
	 * @Title: general32Key
	 * @Description: 生成32位的唯一字符串
	 * @author: makangwei 
	 * @date:   2018年1月16日 下午1:58:01 
	 */
	public static String general32Key(){
		
		StringBuffer sb = new StringBuffer(35); //uuid长度为32，这里指定长度为35位
		String ran = UUID.randomUUID().toString();
		sb.append(ran.substring(0, 8)).append(ran.substring(9,13))
			.append(ran.substring(14,18)).append(ran.substring(19,23)).append(ran.substring(24));
		
		return sb.toString();
	}
	
}
