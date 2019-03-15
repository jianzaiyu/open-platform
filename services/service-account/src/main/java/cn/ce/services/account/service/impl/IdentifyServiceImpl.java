package cn.ce.services.account.service.impl;

import cn.ce.services.account.dao.IdentifyDao;
import cn.ce.services.account.entity.Identify;
import cn.ce.services.account.service.IdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ggs
 * @date 2019/3/6 22:58
 */
@Service
public class IdentifyServiceImpl implements IdentifyService {
    @Autowired
    private IdentifyDao identifyDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return identifyDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Identify record) {
        return identifyDao.insert(record);
    }

    @Override
    public int insertSelective(Identify record) {
        return identifyDao.insertSelective(record);
    }

    @Override
    public Identify selectByPrimaryKey(Integer id) {
        return identifyDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Identify record) {
        return identifyDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Identify record) {
        return identifyDao.updateByPrimaryKey(record);
    }
}
