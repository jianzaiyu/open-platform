package cn.ce.service.openapi.base.admin.service;

import javax.servlet.http.HttpSession;

import cn.ce.service.openapi.base.common.Result;

/**
 * 
 * 
 * @ClassName:  IAdminService   
 * @Description:后台管理员相关接口   
 * @author: makangwei 
 * @date:   2017年9月29日 上午9:45:37   
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 *
 */
public interface IAdminService {
	
	//后天管理员登录
	Result<?> login(HttpSession session, String userName, String password);
}
