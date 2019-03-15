package cn.ce.services.account.dao;

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

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}