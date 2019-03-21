package cn.ce.service.openapi.base.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description : io流工具类
 * @Author : makangwei
 * @Date : 2017-8-8
 */
@Slf4j
public class IOUtils {
	

	 public static String convertStreamToString(InputStream is) {    
		 
		 if(null == is){
			 return null;
		 }
		  
		 BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
		}      
	   
		 StringBuilder sb = new StringBuilder();      
	
		 String line = null;      
	
		 try {      
	
			 while ((line = reader.readLine()) != null) {      
	        
				 sb.append(line);      
			 }      
	     
		 } catch (IOException e) {      
			 e.printStackTrace();      
		 } finally {   
			 try {      
				 is.close();      
			 } catch (IOException e) {      
				 e.printStackTrace();      
	         }      
	     }      
		 return sb.toString();      
	 } 
	 
	 /**
	  * 
	  * @Title: deepCopyInputStream
	  * @Description: InputStream深度复制
	  * @param : @param input
	  * @param : @return
	  * @return: InputStream
	  * @throws
	  */
	 public static InputStream deepCopyInputStream(InputStream input){
		 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = input.read(buffer)) > -1 ) {
			    baos.write(buffer, 0, len);
			}
			baos.flush();
		} catch (IOException e) {
			
			log.error("_____________>input stream repeat read error:",e);
		}

		input = new ByteArrayInputStream(baos.toByteArray()); 
		InputStream input2 = new ByteArrayInputStream(baos.toByteArray()); 
		return input2;
	 }
	 
}
