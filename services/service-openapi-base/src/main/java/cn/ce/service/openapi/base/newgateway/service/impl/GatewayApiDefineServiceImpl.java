package cn.ce.service.openapi.base.newgateway.service.impl;

import cn.ce.service.openapi.base.newgateway.dao.GatewayApiDefineDao;
import cn.ce.service.openapi.base.newgateway.entity.GatewayApiDefine;
import cn.ce.service.openapi.base.newgateway.service.GatewayApiDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ggs
 * @date: 2019-04-29 10:15
 **/
@Service
public class GatewayApiDefineServiceImpl implements GatewayApiDefineService {
    @Autowired
    private GatewayApiDefineDao gatewayApiDefineDao;

    @Override
    public int deleteByPrimaryKey(int id) {
        return gatewayApiDefineDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GatewayApiDefine record) {
        return gatewayApiDefineDao.insert(record);
    }

    @Override
    public int insertSelective(GatewayApiDefine record) {
        return gatewayApiDefineDao.insertSelective(record);
    }

    @Override
    public GatewayApiDefine selectByPrimaryKey(int id) {
        return gatewayApiDefineDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GatewayApiDefine record) {
        return gatewayApiDefineDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPathSelective(GatewayApiDefine record) {
        return gatewayApiDefineDao.updateByPathSelective(record);
    }

    @Override
    public int updateByPrimaryKey(GatewayApiDefine record) {
        return gatewayApiDefineDao.updateByPrimaryKey(record);
    }

    @Override
    public List<GatewayApiDefine> selectRouteMapByClientId(String clientId) {
        return gatewayApiDefineDao.selectRouteMapByClientId(clientId);
    }

    @Override
    public int deleteByPath(String path) {
        return gatewayApiDefineDao.deleteByPath(path);
    }
}
