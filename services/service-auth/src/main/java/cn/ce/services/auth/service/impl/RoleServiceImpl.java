package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.dao.RoleDao;
import cn.ce.services.auth.entity.Role;
import cn.ce.services.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ggs
 * @date: 2019-03-06 18:58
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return roleDao.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(Role record) {
        return roleDao.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleDao.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleDao.updateByPrimaryKey(record);
    }
}
