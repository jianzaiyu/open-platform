package cn.ce.service.openapi.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
* @Description : 读取本读文件工具类
* @Author : makangwei
* @Date : 2017年10月13日
*/
public class LocalFileReadUtil {

	private static Logger _LOGGER = LoggerFactory.getLogger(LocalFileReadUtil.class);
	
	public static JSONObject readLocalClassPathJson(String localUrl){
		
		_LOGGER.info("读取本地json文件，dir:"+localUrl);
		
		File file = null;
		try {
			file = new ClassPathResource(localUrl).getFile();
		} catch (IOException e1) {
			_LOGGER.info("file read error when reading local json file");
			return null;
		}
		
		if(file == null || !file.exists()){
			_LOGGER.info("file does not exits when reading local json file");
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		JSONObject job = null;
		try{
			Reader is = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader bis = new BufferedReader(is);
			String temp = null;
			while((temp = bis.readLine()) != null){
				sb.append(temp);
			}
			_LOGGER.info("本地json文件内容"+sb);
			job = new JSONObject(sb.toString());
		}catch(Exception e){
			_LOGGER.error("error happens when execute local json read",e);
			return null;
		}
		
		return job;
	}
	
	public static JSONObject readFile(File file){
		
		
		if(file == null || !file.exists()){
			_LOGGER.info("file does not exits when reading local json file");
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		JSONObject job = null;
		Reader is = null;
		BufferedReader bis = null;
		
		try{
			is = new FileReader(file);
			bis = new BufferedReader(is);
			String temp = null;
			while((temp = bis.readLine()) != null){
				sb.append(temp);
			}
			_LOGGER.info("本地json文件内容"+sb);
			job = new JSONObject(sb.toString());
		}catch(Exception e){
			_LOGGER.error("error happens when execute local json read",e);
			return null;
		}finally{
			try{
				if(is != null){
					is.close();
				}
				if(bis != null){
					bis.close();
				}
			}catch(Exception e){}

		}
		
		return job;
	}
	
	public static JSONObject readInputStream(InputStream is){
		
		StringBuffer sb = new StringBuffer();
		JSONObject job = null;
		BufferedInputStream bis = null;
		String tempStr = null;
		try{
			bis = new BufferedInputStream(is);
			int temp = 0;
			byte[] b = new byte[1024];
			while((temp = bis.read(b)) != -1){
				tempStr = new String(b,0,temp);
				sb.append(tempStr);
			}
			_LOGGER.info("本地json文件内容"+sb);
			job = new JSONObject(sb.toString());
		}catch(Exception e){
			_LOGGER.error("error happens when execute local json read",e);
			return null;
		}finally{
			try{
				if(is != null){
					is.close();
				}
				if(bis != null){
					bis.close();
				}
			}catch(Exception e){}

		}
		
		return job;
	}
	
}
