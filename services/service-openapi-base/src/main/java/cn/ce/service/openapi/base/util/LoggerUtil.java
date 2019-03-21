package cn.ce.service.openapi.base.util;

import org.slf4j.Logger;
/**
 * 
* @ClassName: LoggerUtil  
* @Description: 日志打印工具类，基于org.slf4j.Logger
* @create 2018年5月7日/makangwei  
* @update 2018年5月7日/makangwei/(说明。)....多次修改添加多个update   
*
 */
public class LoggerUtil {

	Logger logger;
	
	public LoggerUtil(Logger logger){
		this.logger = logger;
	}
	
	/**
	 * 
	* @Title: error  
	* @Description: 打印error级别信息 
	* @create 2018年5月7日/makangwei  
	* @update 2018年5月7日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param message 日志消息
	* @param @param bool    是否输出日志
	* @return void    返回类型  
	* @throws
	 */
	public void error(String message,boolean bool){
		if(bool){
			logger.error(message);
		}
	}

	/**
	 * 
	* @Title: error  
	* @Description: 打印info级别信息 
	* @create 2018年5月7日/makangwei  
	* @update 2018年5月7日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param message 日志消息
	* @param @param bool    是否输出日志
	* @return void    返回类型  
	* @throws
	 */
	public void info(String message, boolean bool){
		if(bool){
			logger.info(message);
		}
	}
	
	/**
	 * 
	* @Title: error  
	* @Description: 打印debug级别信息 
	* @create 2018年5月7日/makangwei  
	* @update 2018年5月7日/makangwei/(说明。)....多次修改添加多个update   
	* @param @param message 日志消息
	* @param @param bool    是否输出日志
	* @return void    返回类型  
	* @throws
	 */
	public void debug(String message, boolean bool){
		if(bool){
			logger.debug(message);
		}
	}
}
