package cn.ce.services.auth.dao;


import cn.ce.services.auth.entity.User;


/**
 * @auther ggs
 * @date 2019-03-05 11:20:48.439
 */
public interface UserDao {
    int insertSelective(User record);

    User selectByUserName(String userName);
}