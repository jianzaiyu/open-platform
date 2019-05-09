package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.dao.UserDao;
import cn.ce.services.auth.entity.User;
import cn.ce.services.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ggs
 * @date: 2019-03-01 14:58
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }


    @Override
    public User selectByUserName(String name) {
        return userDao.selectByUserName(name);
    }

}
