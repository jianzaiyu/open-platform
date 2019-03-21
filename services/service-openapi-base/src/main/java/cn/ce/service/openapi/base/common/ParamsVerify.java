package cn.ce.service.openapi.base.common;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
* @Description : 参数校验工具类
* @Author : makangwei
* @Date : 2017年9月22日
*/
public class ParamsVerify {

	/**
	 * 
	 * @Title: checkNullValue
	 * @Description: 如果有空参数返回异常结果
	 * @param : @return
	 * @return: CloudResult
	 * @throws
	 */
	public Result<?> checkNullValue( Map<String,String> map){
		
		Result<String> result = new Result<String>();
		
		for (String key : map.keySet()) {
			if(StringUtils.isBlank(map.get(key))){
				result.setErrorMessage("parameter "+key+" cann't be empty", ErrorCodeNo.SYS005);
				return result;
			}
		}
		return null;
	}
}
