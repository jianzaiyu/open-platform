package cn.ce.services.auth.dao;

import cn.ce.services.auth.entity.User;

import java.util.List;

/**
*@auther ggs
*@date 2019-03-05 11:20:48.439
*/
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}