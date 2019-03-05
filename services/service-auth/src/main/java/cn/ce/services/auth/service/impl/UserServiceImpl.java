package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.entity.User;
import cn.ce.services.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: ggs
 * @date: 2019-03-01 14:58
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
