package cn.ce.service.openapi.base.newgateway.service;

import cn.ce.service.openapi.base.newgateway.entity.GatewayApiDefine;

import java.util.List;

/**
*@auther ggs
*@date 2019-04-29 10:10:29.932
*/
public interface GatewayApiDefineService {
    int deleteByPrimaryKey(int id);

    int insert(GatewayApiDefine record);

    int insertSelective(GatewayApiDefine record);

    GatewayApiDefine selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(GatewayApiDefine record);

    int updateByPathSelective(GatewayApiDefine record);

    int updateByPrimaryKey(GatewayApiDefine record);

    List<GatewayApiDefine> selectRouteMapByClientId(String clientId);

    int deleteByPath(String path);
}