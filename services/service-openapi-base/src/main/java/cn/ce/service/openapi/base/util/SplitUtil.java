package cn.ce.service.openapi.base.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
* @Description : 字符串切割工具类
* @Author : makangwei
* @Date : 2017年10月13日
*/
public class SplitUtil {

	/**
	 * @Title: splitStringWithComma
	 * @Description: 用逗号切割字符串
	 * @author: makangwei 
	 * @date:   2017年10月13日 下午7:36:23 
	 */
	public static List<String> splitStringWithComma(String str){
		
		String[] strArray = null;
		if(StringUtils.isNotBlank(str) && str.indexOf(",") >= 0){
			strArray = str.split(",");
		}else{
			strArray = (String[]) ArrayUtils.add(strArray, str);
		}
		
		List<String> strList = new ArrayList<String>();
		for (String string : strArray) {
			if(StringUtils.isNotBlank(string)){
				strList.add(string);
			}
		}
		return strList;
	}
	
}
