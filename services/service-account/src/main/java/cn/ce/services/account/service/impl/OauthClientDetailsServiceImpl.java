package cn.ce.services.account.service.impl;

import cn.ce.services.account.dao.OauthClientDetailsDao;
import cn.ce.services.account.entity.OauthClientDetails;
import cn.ce.services.account.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ggs
 * @date 2019-04-26 16:11:04.884
 */
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {
    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;

    @Override
    public int deleteByPrimaryKey(String clientId) {
        return oauthClientDetailsDao.deleteByPrimaryKey(clientId);
    }

    @Override
    public int insert(OauthClientDetails record) {
        return oauthClientDetailsDao.insert(record);
    }

    @Override
    public int insertSelective(OauthClientDetails record) {
        return oauthClientDetailsDao.insertSelective(record);
    }

    @Override
    public OauthClientDetails selectByPrimaryKey(String clientId) {
        return oauthClientDetailsDao.selectByPrimaryKey(clientId);
    }

    @Override
    public int updateByPrimaryKeySelective(OauthClientDetails record) {
        return oauthClientDetailsDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OauthClientDetails record) {
        return oauthClientDetailsDao.updateByPrimaryKey(record);
    }

    @Override
    public List<OauthClientDetails> selectByClientType(String clientType) {
        return oauthClientDetailsDao.selectByClientType(clientType);
    }
}