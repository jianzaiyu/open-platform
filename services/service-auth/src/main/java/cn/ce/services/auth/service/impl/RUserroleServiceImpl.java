package cn.ce.services.auth.service.impl;

import cn.ce.services.auth.dao.RUserroleDao;
import cn.ce.services.auth.entity.RUserrole;
import cn.ce.services.auth.entity.UserRoleDetail;
import cn.ce.services.auth.service.RUserroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ggs
 * @date 2019/3/6 22:32
 */
@Service
public class RUserroleServiceImpl implements RUserroleService {
    @Autowired
    private RUserroleDao rUserroleDao;

    @Override
    public int deleteByPrimaryKey(Integer urId) {
        return rUserroleDao.deleteByPrimaryKey(urId);
    }

    @Override
    public int insert(RUserrole record) {
        return rUserroleDao.insert(record);
    }

    @Override
    public int insertSelective(RUserrole record) {
        return rUserroleDao.insertSelective(record);
    }

    @Override
    public RUserrole selectByPrimaryKey(Integer urId) {
        return rUserroleDao.selectByPrimaryKey(urId);
    }

    @Override
    public List<UserRoleDetail> selectByUId(Integer uid) {
        return rUserroleDao.selectByUId(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(RUserrole record) {
        return rUserroleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RUserrole record) {
        return rUserroleDao.updateByPrimaryKey(record);
    }
}
