package cn.ce.service.openapi.base.users.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.users.entity.QueryUserEntity;
import cn.ce.service.openapi.base.users.entity.User;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月17日
*/
@Transactional
public interface IMysqlUserDao {



	User findById(String userId);
	
	User findByUserName(String userName);

	User findByEmail(String email);
	
	int save(User user);
	
	int update(User user);
	
	int checkUserName(String userName);

	int checkEmail(String email);

	int checkIdCard(@Param("idCard") String idCard, @Param("checkState") int checkState);

	int checkTelNumber(String telNumber);

	int checkIdCard1(String idCard);

	int findListSize(QueryUserEntity userEntity);

	List<User> getPagedList(QueryUserEntity userEntity);

	int deleteAll();

//	int checkUserName(@Param("userName")String userName,@Param("userId")String userId);
//
//	int checkEmail(@Param("email")String email,@Param("userId")String userId);
//
//	int checkIdCard(@Param("idCard")String idCard, @Param("checkState")int checkState,@Param("userId")String userId);
//
//	int checkTelNumber(@Param("telNumber")String telNumber,@Param("userId")String userId);
}
