package cn.ce.service.openapi.base.util;

import cn.ce.service.openapi.base.common.Constants;

/**
 * @ClassName:  PageValidateUtil   
 * @Description:页参数校验   
 * @author: makangwei 
 * @date:   2017年10月20日 上午10:55:36   
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 */
public class PageValidateUtil {

	public static Integer checkCurrentPage(Integer currentPage){
		if(currentPage == null || currentPage < 1){
			currentPage = 1;
		}
		return currentPage;
	}
	
	public static Integer checkPageSize(Integer pageSize){
		if(pageSize == null || pageSize < 1 ){
			pageSize = Constants.PAGE_COMMON_SIZE;
		}
		if(pageSize > Constants.PAGE_MAX_SIZE){
			pageSize = Constants.PAGE_MAX_SIZE;
		}
		return pageSize;
	}
}
