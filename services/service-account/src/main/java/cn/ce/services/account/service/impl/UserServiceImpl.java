package cn.ce.services.account.service.impl;

import cn.ce.services.account.dao.UserDao;
import cn.ce.services.account.entity.User;
import cn.ce.services.account.entity.UserDetail;
import cn.ce.services.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ggs
 * @date: 2019-03-01 14:58
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public User selectByUserName(String name) {
        return userDao.selectByUserName(name);
    }

    @Override
    public List<User> selectByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    @Override
    public User selectByTelNumber(String telNumber) {
        return userDao.selectByTelNumber(telNumber);
    }

    @Override
    public User selectByUserNameAndEmail(String userName, String email) {
        return userDao.selectByUserNameAndEmail(userName, email);
    }

    @Override
    public int updateByUserNameSelective(User record) {
        return userDao.updateByUserNameSelective(record);
    }

    @Override
    public UserDetail selectUserDetailByUserName(String userName) {
        return userDao.selectUserDetailByUserName(userName);
    }
}
