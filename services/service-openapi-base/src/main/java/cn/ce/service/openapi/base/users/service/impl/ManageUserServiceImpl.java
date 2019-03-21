package cn.ce.service.openapi.base.users.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.mail.MailInfo;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.users.dao.IMysqlUserDao;
import cn.ce.service.openapi.base.users.entity.QueryUserEntity;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.users.service.IManageUserService;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月12日
*/
@Service(value="manageUserService")
@Transactional(propagation=Propagation.REQUIRED)
public class ManageUserServiceImpl implements IManageUserService{

	private static final Logger _LOGGER = LoggerFactory.getLogger(ManageUserServiceImpl.class);
	
//	@Resource 
//	private INewUserDao newUserDao;
	@Resource
	private IMysqlUserDao mysqlUserDao;
	
	
	@Override
	public Result<Page<User>> userList(QueryUserEntity userEntity) {
		
		// TODO 20180108 mkw 修改User实体类的设置参数的方式，然后简化创建User的创建过程
		Result<Page<User>> result = new Result<Page<User>>();
		//Page<User> userList = newUserDao.getUserList(userType, userName, email,telNumber,enterpriseName, checkState,state,currentPage, pageSize);
		//List<User> items = (List<User>) userList.getItems();
		
//		Page<User> page = newUserDao.getUserList1(paramUser,currentPage,pageSize);
//		
//		List<User> items = page.getItems();
//		
//		for (User user : items) {
//			user.setPassword("");
//		}
		int totalNum = mysqlUserDao.findListSize(userEntity);
		List<User> list = mysqlUserDao.getPagedList(userEntity);
		Page<User> page = new Page<User>(userEntity.getCurrentPage(),totalNum,userEntity.getPageSize(),list);
		result.setSuccessData(page);
		return result;
	}


	@Override
	public Result<String> auditUsers(List<String> userIdArray, String checkMem, Integer checkState) {
		
		_LOGGER.info("manageAuditUsers start:userIdArray" + JSON.toJSONString(userIdArray) + ",checkState" + checkState + ",checkMem:" + checkMem);
		
		Result<String> result = new Result<String>();
	
		for (String userId : userIdArray) {
			
			//User user = newUserDao.findUserById(userId);
			User user = mysqlUserDao.findById(userId);
			
			user.setCheckState(checkState);
			if(StringUtils.isNotBlank(checkMem)){
				user.setCheckMem(checkMem);
			}
			
			//newUserDao.save(user);
			mysqlUserDao.update(user);
			
			_LOGGER.info("------->邮件通知用户，帐号审核结果");
			
			if(AuditConstants.USER__CHECKED_SUCCESS == checkState) {
				MailInfo mailInfo = new MailInfo();
				mailInfo.setContent("您申请注册的开放平台帐号已经审核通过。欢迎使用中企云Pass开放平台！");
				mailInfo.setSubject("中企云Paas开放平台账户审核结果通知");
				mailInfo.setToOne(user.getEmail());
//				MailUtil.send(mailInfo, false);
			}else if(AuditConstants.USER__CHECKED_FAILED == checkState){
				MailInfo mailInfo = new MailInfo();
				mailInfo.setContent("很抱歉通知您，您申请的开放平台账户未审核通过，原因是："+checkMem+",请重新申请");
				mailInfo.setSubject("中企云Paas开放平台账户审核结果通知");
				mailInfo.setToOne(user.getEmail());
//				MailUtil.send(mailInfo, false);
			}
		} 
		
		_LOGGER.info("manageAuditUsers success!");
		result.setSuccessMessage("审核成功!");
		return result;
	}

	
	@Override
	public Result<String> activeOrForbidUsers(String userId, Integer state) {

		_LOGGER.info("manageActiveOrForbidUsers start:userId" + userId + ",state" + state);

		Result<String> result = new Result<String>();

		// User user = newUserDao.findUserById(userId);
		User user = mysqlUserDao.findById(userId);

		if (null == user) {
			result.setErrorMessage("用户不存在!",ErrorCodeNo.SYS015);
			return result;
		}
		user.setState(state);
		mysqlUserDao.update(user);
		result.setSuccessMessage("保存成功!");
		return result;
	}

}
