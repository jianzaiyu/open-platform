package cn.ce.service.openapi.base.users.service;

import javax.servlet.http.HttpSession;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.users.entity.User;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月11日
*/

public interface IConsoleUserService {

	Result<?> userRegister(User user);
	
	Result<?> login(HttpSession session, String userName, String password);
	
	Result<?> sendRegistEmail(String email, HttpSession session);

	Result<?> sendRePwdEmail(String email, HttpSession session);
	
	Result<?> checkTelNumber(String telNumber);

	Result<?> checkEmail(String email);
	
	Result<?> checkUserName(String userName);

	Result<?> modifyPassword(String email, String newPassword);
	//用户认证信息
	Result<?> authenticate(String userId, String enterpriseName, String idCard, String userRealName, HttpSession session);

	User findUserById(String userId);

	Result<?> checkIdCard(String idCard);

//	CloudResult<String> migraUser();
}