package cn.ce.service.openapi.base.util;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author makangwei
 * 2017-8-1
 */
public class RandomUtil {

	private static final Random random = new Random();
	
	/**
	 * 生成16位随机数字符串
	 * @return 
	 */
	public static String random16Number(){
	
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<16; i++){
			int temp = random.nextInt(10);
			sb.append(temp);
		}
		
		return sb.toString();
	}
	
	public static Integer random9Number(){
		
		Integer rm = 0;
		
		for(int i=0; i<8; i++){
			int temp = random.nextInt(10);
			rm = rm*10+temp;
		}
		
		return rm;
	}
	
	public static Integer random6Number(){
		
		Integer rm = 0;
		for(int i=0; i<6; i++){
			int temp = random.nextInt(10);
			rm = rm*10+temp;
		}
		
		return rm;
	}
	
	public static String random32UUID(){
		String s = UUID.randomUUID().toString();
		StringBuilder sb = new StringBuilder(33);
		return sb.append(s.substring(0,8)).append(s.substring(9,13))
			.append(s.substring(14,18)).append(s.substring(19,23)).append(s.substring(24))
			.toString();
		
	}
	
}
