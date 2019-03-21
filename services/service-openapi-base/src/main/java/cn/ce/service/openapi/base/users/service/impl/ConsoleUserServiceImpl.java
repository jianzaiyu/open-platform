package cn.ce.service.openapi.base.users.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.Constants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.users.dao.IMysqlUserDao;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.users.service.IConsoleUserService;
import cn.ce.service.openapi.base.util.RandomUtil;
import cn.ce.service.openapi.base.util.Util;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月11日
*/
@Service(value="consoleUserService")
@Transactional(propagation=Propagation.REQUIRED)
public class ConsoleUserServiceImpl implements IConsoleUserService{

	private static final Logger _LOGGER = LoggerFactory.getLogger(ConsoleUserServiceImpl.class);
	
//    @Resource
//    private INewUserDao newUserDao;
    @Resource
    private IMysqlUserDao mysqlUserDao;
    
    
	@Override
	public Result<String> userRegister(User user) {
		
		_LOGGER.info("consoleUserRegister begin user:" + JSON.toJSONString(user));
		
		Result<String> result = new Result<String>();
		
		//校验用户名
//		User user1= newUserDao.findUserByUserName(user.getUserName());
		int uNum = mysqlUserDao.checkUserName(user.getUserName());
		if(uNum > 0){
			result.setErrorMessage("当前用户名已经存在", ErrorCodeNo.SYS009);
			return result;
		}
		//校验邮箱
		//User user2 = newUserDao.findUserByEmail(user.getEmail());
		int eNum = mysqlUserDao.checkEmail(user.getEmail());
		if(eNum > 0){
			result.setErrorMessage("当前邮箱已经被注册", ErrorCodeNo.SYS009);
			return result;
		}
//		//校验手机号
//		User user3 = newUserDao.findUserByTelNumber(user.getTelNumber());
//		if(user3 != null){
//			result.setErrorMessage("当前手机号已经被使用", ErrorCodeNo.SYS009);
//			return result;
//		}
		
		try {
			// TODO appSecret使用Util生成的，这里有什么作用？
			// TODO userType为int类型 ；/** 用户类型  0:管理员，1:普通用户，2:提供者 */
			// state 保留字段
			user.setState(1);
			user.setRegTime(new Date());
			user.setUserType(AuditConstants.USER_DEVELOPER); // TODO 默认设置为普通用户
			user.setCheckState(AuditConstants.USER__UNCHECKED);//默认启用
			user.setAppSecret(Util.getRandomStrs(Constants.SECRET_LENGTH));
			//newUserDao.save(user);
			mysqlUserDao.save(user);
			
			_LOGGER.info("consoleUserRegister success! user:" + JSON.toJSONString(user));
			
			result.setSuccessMessage("添加成功");
		} catch (Exception e) {
			_LOGGER.error("error happens when execute save user to db",e);
			result.setErrorMessage("添加失败!");
		}
		return result;
	}


	@Override
	public Result<?> authenticate(String userId, String enterpriseName, String idCard, String userRealName, HttpSession session) {
		Result<String> result = new Result<String>();
//		User user = newUserDao.findUserById(userId);
		User user = mysqlUserDao.findById(userId);
		
		if(user == null){
			result.setErrorMessage("当前用户不存在", ErrorCodeNo.SYS006);
			return result;
		}
		
		// 判断当前的用户状态
		if(AuditConstants.USER__CHECKED_FAILED != user.getCheckState() &&
				AuditConstants.USER__UNCHECKED != user.getCheckState()){
			return Result.errorResult("状态不可用", ErrorCodeNo.SYS012, null, Status.FAILED);
		}
		
		//判断当前身份证号码是否已经被占用
//		User userTemp = newUserDao.findUserByIdCard(idCard,AuditConstants.USER__CHECKED_SUCCESS);
		int iNum = mysqlUserDao.checkIdCard(idCard,AuditConstants.USER__CHECKED_SUCCESS);
		if(StringUtils.isBlank(idCard) || iNum > 0){
			return Result.errorResult("当前身份证号已经存在", ErrorCodeNo.SYS009, null, Status.FAILED);
		}
		
		user.setEnterpriseName(enterpriseName);
		user.setIdCard(idCard);
		user.setUserRealName(userRealName);
		user.setCheckState(1);
		
		_LOGGER.info("consoleUserAuthenticate begin user:" + JSON.toJSONString(user));
		
//		newUserDao.save(user);
		mysqlUserDao.update(user);
		
		_LOGGER.info("consoleUserAuthenticate success!");
		
		result.setSuccessMessage("保存成功!");
		
		session.setAttribute(Constants.SES_LOGIN_USER, user);
		
		return result;
	}
	
	
	@Override
	public Result<User> login(HttpSession session,String userName, String password) {

		Result<User> result = new Result<User>();
		
//		User user = newUserDao.findUserByName(userName);
		User user = mysqlUserDao.findByUserName(userName);
		
		if (user == null) {
			result.setErrorMessage("您输入的账号不存在", ErrorCodeNo.SYS020);
			return result;
		}else if(!password.equals(user.getPassword())){
			result.setErrorMessage("密码错误", ErrorCodeNo.SYS021);
			return result;
		}
		
		if(user.getState() == 0){
			result.setErrorMessage("当前账号已经被禁用", ErrorCodeNo.SYS023);
			return result;
		}
		
		_LOGGER.info("账号密码正确");
		user.setPassword("");
		session.setAttribute(Constants.SES_LOGIN_USER, user);
		result.setSuccessData(user);
		return result;
	}

	
	@Override
	public Result<?> sendRegistEmail(String email, HttpSession session) {
		
		_LOGGER.info("consoleUserSendRegisterEmail begin,email:" + email);
		
//		User user = newUserDao.findUserByEmail(email);
		User user = mysqlUserDao.findByEmail(email);
		
		Result<String> result = new Result<String>();
		
		if(user != null) {
			result.setErrorMessage("当前邮箱已经被注册");
		}
		
		Integer checkCode = RandomUtil.random6Number();
		
//		MailInfo mailinfo = new MailInfo();
//		mailinfo.setToOne(email);
//		mailinfo.setSubject("开放平台注册验证码");
//		mailinfo.setContent("验证码为：" + checkCode + ";有效期为3分钟");
//
//		// TODO 发送邮箱验证 123123123
//		MailUtil.send(mailinfo, false);
		
		session.setAttribute(email, checkCode);
		
		session.setAttribute(email+"TransTime", System.currentTimeMillis()); //验证码发送时间
		
		session.setMaxInactiveInterval(180); //设置过期时间为3分钟
		
		_LOGGER.info("consoleUserSendRegisterEmail end,code:" + session.getAttribute(email));
		
		result.setSuccessMessage("发送成功");
		
		return result;
	}

	
	@Override
	public Result<?> sendRePwdEmail(String email, HttpSession session) {
		
		_LOGGER.info("consoleUserSendRePwdEmail begin,email:" + email);
		
		Result<String> result = new Result<String>();
		
//		User user = newUserDao.findUserByEmail(email);
		User user = mysqlUserDao.findByEmail(email);
		
		if(null == user){
			result.setErrorMessage("当前用户不存在",ErrorCodeNo.SYS006);
			return result;
		}
		
		// TODO 发送邮箱验证 123123123
		Integer checkCode = RandomUtil.random6Number();
		
//		MailInfo mailinfo = new MailInfo();
//		mailinfo.setToOne(email);
//		mailinfo.setSubject("开放平台重置密码验证码");
//		mailinfo.setContent("验证码为：" + checkCode + ";有效期为3分钟");
//
//		MailUtil.send(mailinfo, false);
		
		session.setAttribute(email, checkCode);
		
		session.setAttribute(email+"TransTime", System.currentTimeMillis()); //验证码发送时间
		
		session.setMaxInactiveInterval(180); //设置过期时间为3分钟
		
		_LOGGER.info("consoleUserSendRePwdEmail end,code:" + session.getAttribute(email));
		
		result.setSuccessMessage("发送成功");
		
		return result;
	}

	
	@Override
	public Result<?> checkTelNumber(String telNumber) {
//		User user = newUserDao.findUserByTelNumber(telNumber);
		int tNum = mysqlUserDao.checkTelNumber(telNumber);
		Result<String> result = new Result<String>();
		if(tNum > 0) {
			result.setErrorMessage("当前手机号已经被注册");
		}else{
			result.setSuccessMessage("");
		}
		return result;
	}
	

	@Override
	public Result<?> checkEmail(String email) {
//		User user = newUserDao.findUserByEmail(email);
		int uNum = mysqlUserDao.checkEmail(email);
		Result<String> result = new Result<String>();
		if(uNum > 0) {
			result.setErrorMessage("当前邮箱已经被注册");
		}else{
			result.setSuccessMessage("");
		}
		return result;
	}
	

	@Override
	public Result<?> checkUserName(String userName) {
//		User user = newUserDao.findUserByUserName(userName);
		int uNum = mysqlUserDao.checkUserName(userName);
		Result<String> result = new Result<String>();
		if(uNum > 0){
			result.setErrorMessage("当前用户名已存在，请重新输入");
		}else{
			result.setSuccessMessage("当前用户名可以使用");
		}
		return result;
	}

	
	@Override
	public Result<?> modifyPassword(String email, String newPassword) {
		
		_LOGGER.info("consoleUserModifyPassword start:email" + email + ",newPassword:" + newPassword);
		
		Result<String> result = new Result<String>();
		
//		User user = newUserDao.findUserByEmail(email);
		User user = mysqlUserDao.findByEmail(email);
		
		if(user == null){
			result.setErrorMessage("当前用户不存在",ErrorCodeNo.SYS006);
			return result;
		}
		
		user.setPassword(newPassword);
		
//		newUserDao.save(user);
		mysqlUserDao.update(user);
		
		_LOGGER.info("consoleUserModifyPassword success!");
		
		result.setSuccessMessage("修改成功");
		
		return result;
	}


	@Override
	public User findUserById(String userId) {
//		return newUserDao.findUserById(userId);
		return mysqlUserDao.findById(userId);
	}


	@Override
	public Result<?> checkIdCard(String idCard) {
		//根据chenckState来查询。只能检查checkState为2的身份证信息
//		User user = newUserDao.findUserByIdCard(idCard,AuditConstants.USER__CHECKED_SUCCESS);
		int iNum = mysqlUserDao.checkIdCard1(idCard);
		
		if(iNum > 0){
			return Result.errorResult("当前身份证号码已经被注册", ErrorCodeNo.SYS009, null, Status.FAILED);
		}
		return Result.errorResult("当前身份证号可以使用", ErrorCodeNo.SYS000, null, Status.SUCCESS);
	}
	
	


//	@Override
//	public CloudResult<String> migraUser() {
//		
//		//删除mysql数据库中的user
//		int i = 0;
//		List<User> userList = newUserDao.findAll();
//		mysqlUserDao.deleteAll();
//		for (User user : userList) {
//			i+=mysqlUserDao.save(user);
//		}
//		CloudResult<String> result = new CloudResult<String>();
//		result.setSuccessMessage("一共"+userList.size()+"条数据，成功插入mysql数据库"+i+"条");
//		return result;
//	}
	
}
