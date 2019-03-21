package cn.ce.service.openapi.manage.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName:  BaseController   
 * @Description: TODO 这个类是否可以弃用？
 * @author: makangwei 
 * @date:   2018年2月5日 下午2:19:46   
 * @Copyright: 2018 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 *
 */
public class BaseController {
	 /** 请求对象 */
	    @Autowired  
	    public   HttpServletRequest request; 
	
	    /** 会话对象 */
	    @Autowired
	    public  HttpSession session ;
}

