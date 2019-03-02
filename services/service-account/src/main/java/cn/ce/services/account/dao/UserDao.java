package cn.ce.services.account.dao;

import cn.ce.services.account.entity.User;

/**
*@auther ggs
*@date 2019-03-01 13:39:28.959
*/
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}