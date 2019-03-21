package cn.ce.service.openapi.base.common;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
* @Description : 用于不用环境之间数据的相互传递
* @Author : makangwei
* @Date : 2017年12月6日
*/
@Slf4j
public class MongoConstantsUtils {

	//测试1mongo地址
	private static final String test1 = "10.12.40.82:27017";
	//测试环境mongo地址
	private static final String test = "10.12.40.82:27018";
	//预发布环境mongo地址
	private static final String pre = "10.20.1.87:27017";
	//正式环境mongo地址
	private static final String prod = "10.20.0.234:23000";
	
	private static final Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
	
	private MongoConstantsUtils(){
		Map<String,String> testMap = new HashMap<String,String>();
		testMap.put("test1", test1);
		Map<String,String> test1Map = new HashMap<String,String>();
		test1Map.put("test", test);
		Map<String,String> preMap = new HashMap<String,String>();
		preMap.put("prod", prod);
		Map<String,String> prodMap = new HashMap<String,String>();
		prodMap.put("pre", pre);
	}
	
	private static MongoConstantsUtils constants = new MongoConstantsUtils();
	
	public static MongoConstantsUtils getInstance(){
		return constants;
	}
	
	/**
	 * 
	 * @Title: getDBUrl
	 * @Description: 根据环境参数和目标地址参数获取目标参数地址
	 * @author: makangwei 
	 * @date:   2017年12月6日 下午1:50:53 
	 * @param : @param environment 环境参数 如生产环境:prod，预发布环境:pre，测试环境:test，测试环境1:test1
	 * @param : @param destination
	 * @return: void
	 * @throws
	 */
	public String getDBUrl(String environment, String destination){
		
		if(StringUtils.isBlank(environment) || StringUtils.isBlank(destination)){
			return null;
		}
		
		Map<String,String> desMap = map.get(environment);
		if(desMap == null || desMap.size() < 1){
			log.error("get environment parameters error ");
			return null;
		}
		
		return desMap.get(destination);
	}
}
