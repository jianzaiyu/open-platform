package cn.ce.service.openapi.base.users.dao.impl;//package cn.ce.service.openapi.base.users.dao.impl;
//
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import cn.ce.service.openapi.base.common.DBFieldsConstants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.mongo.BaseMongoDaoImpl;
//import cn.ce.service.openapi.base.users.dao.INewUserDao;
//import cn.ce.service.openapi.base.users.entity.User;
//
///**
// * @Description : 新的userDao实现
// * @Author : makangwei
// * @Date : 2017年10月11日
// */
//@Service(value = "newUserDao")
//public class NewUserDaoImpl extends BaseMongoDaoImpl<User> implements INewUserDao {
//
//	public User save(User user) {
//		if(StringUtils.isNotBlank(user.getId())){
//			super.update(user);
//		} else {
//			super.save(user);
//		}
//		return user;
//	}
//
//	@Override
//	public User findUserByUsernameAndPwd(String userName, String password) {
//		Criteria c = new Criteria();
//		Criteria c1 = new Criteria();
//		c1.and("userName").is(userName);
//		c1.and("password").is(password);
//		Criteria c2 = new Criteria();
//		c2.and("telNumber").is(userName);
//		c2.and("password").is(password);
//		Criteria c3 = new Criteria();
//		c3.and("email").is(userName);
//		c3.and("password").is(password);
//		
//		Query query = new Query().addCriteria(c.orOperator(c1,c2,c3));
//		return super.findOne(query);
//	}
//
//	@Override
//	public User findUserByEmail(String email) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("email").is(email));
//		return super.findOne(query);
//	}
//
//	@Override
//	public User findUserByUserName(String userName) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("userName").is(userName));
//		return super.findOne(query);
//	}
//	
//	public User findUserByName(String name){
//		Criteria c = new Criteria();
//		Criteria c1 = new Criteria();
//		c1.and("userName").is(name);
//		Criteria c2 = new Criteria();
//		c2.and("telNumber").is(name);
//		Criteria c3 = new Criteria();
//		c3.and("email").is(name);
//		
//		Query query = new Query().addCriteria(c.orOperator(c1,c2,c3));
//		return super.findOne(query);
//	}
//
//	@Override
//	public User findUserByTelNumber(String telNumber) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("telNumber").is(telNumber));
//		return super.findOne(query);
//	}
//
//	@Override
//	public User findUserById(String userId) {
//
//		return super.findById(userId);
//	}
//
//	@Override
//	@Deprecated
//	/**
//	 * 
//	 * @Title: getUserList
//	 * @Description: 迁移完mysql，该方法将被弃用
//	 * @author: makangwei 
//	 * @date:   2018年1月8日 下午3:15:18 
//	 * @throws
//	 */
//	public Page<User> getUserList(Integer userType, String userName, String email, String telNumber,
//			String enterpriseName, Integer checkState, Integer state, int currentPage, int pageSize) {
//		//构建查询对象
//		Criteria c = new Criteria();
//		
//		if(null != userType && 0 != userType){
//			c.and(DBFieldsConstants.USER_USERTYPE).is(userType);
//		}
//		if(null != checkState){
//			c.and(DBFieldsConstants.USER_CHECKSTATE).is(Integer.valueOf(checkState));
//		}
//		if(null != state){
//			c.and(DBFieldsConstants.USER_STATE).is(Integer.valueOf(state));
//		}
//		if(StringUtils.isNotBlank(userName)){
//			c.and(DBFieldsConstants.USER_USERNAME).regex(userName,"i");
//		}
//		if(StringUtils.isNotBlank(email)){
//			c.and(DBFieldsConstants.USER_EMAIL).regex(email,"i");
//		}
//		if(StringUtils.isNotBlank(telNumber)){
//			c.and(DBFieldsConstants.USER_TELNUMBER).regex(telNumber);
//		}
//		if(StringUtils.isNotBlank(enterpriseName)){
//			c.and(DBFieldsConstants.USER_ENTERPRISE_NAME).regex(enterpriseName,"i");
//		}
//        Query query = new Query(c).with(new Sort(new Order(Direction.DESC,DBFieldsConstants.USER_REGTIME)));
//        
//        return super.findPage(new Page<User>(currentPage, 0,pageSize),query);
//	}
//
//	@Override
//	public Page<User> getUserList1(User user, int currentPage, int pageSize) {
//		//构建查询对象
//		Criteria c = new Criteria();
//		
//		if(null != user.getUserType()){
//			c.and(DBFieldsConstants.USER_USERTYPE).is(user.getUserType());
//		}
//		if(null != user.getCheckState()){
//			c.and(DBFieldsConstants.USER_CHECKSTATE).is(user.getCheckState());
//		}
//		if(null != user.getState()){
//			c.and(DBFieldsConstants.USER_STATE).is(user.getState());
//		}
//		if(StringUtils.isNotBlank(user.getUserName())){
//			c.and(DBFieldsConstants.USER_USERNAME).regex(user.getUserName(),"i");
//		}
//		if(StringUtils.isNotBlank(user.getEmail())){
//			c.and(DBFieldsConstants.USER_EMAIL).regex(user.getEmail(),"i");
//		}
//		if(StringUtils.isNotBlank(user.getTelNumber())){
//			c.and(DBFieldsConstants.USER_TELNUMBER).regex(user.getTelNumber());
//		}
//		if(StringUtils.isNotBlank(user.getEnterpriseName())){
//			c.and(DBFieldsConstants.USER_ENTERPRISE_NAME).regex(user.getEnterpriseName(),"i");
//		}
//        Query query = new Query(c).with(new Sort(new Order(Direction.DESC,DBFieldsConstants.USER_REGTIME)));
//        
//		query = query == null ? new Query(Criteria.where("_id").exists(true)) : query;
//		
//		Page<User> page = new Page<User>(currentPage, 0, pageSize);
//		long count = super.count(query);
//		// 总数
//		page.setTotalNumber((int) count);
//		query.skip((currentPage - 1) * pageSize).limit(pageSize);
//		List<User> rows = super.find(query);
//		page.build(rows);
//		return page;
//        
//	}
//	
//	@Override
//	public User findUserByIdCard(String idCard, Integer checkState) {
//		Criteria c = new Criteria();
//		c.and(DBFieldsConstants.USER_ID_CARD).is(idCard);
//		c.and(DBFieldsConstants.USER_CHECKSTATE).is(checkState);
//		Query query = new Query(c);
//		return super.findOne(query);
//	}
//
//}
