package cn.ce.services.auth.service;

import cn.ce.services.auth.entity.User;


/**
 * @author: ggs
 * @date: 2019-03-01 14:57
 **/
public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String name);
}
