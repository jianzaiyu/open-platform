package cn.ce.services.account.dao;

import cn.ce.services.account.entity.UserDetail;
import cn.ce.services.account.entity.User;

/**
 * @auther ggs
 * @date 2019-03-05 11:20:48.439
 */
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String userName);

    User selectByEmail(String email);

    User selectByTelNumber(String email);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserNameAndEmail(String userName, String email);

    int updateByUserNameSelective(User record);

    UserDetail selectUserDetailByUserName(String userName);
}