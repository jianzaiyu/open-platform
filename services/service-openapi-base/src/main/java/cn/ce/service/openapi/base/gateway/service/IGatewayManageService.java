package cn.ce.service.openapi.base.gateway.service;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.gateway.entity.GatewayColonyEntity;
import cn.ce.service.openapi.base.gateway.entity.GatewayNodeEntity;
import cn.ce.service.openapi.base.gateway.entity.QueryGwColonyEntity;
import cn.ce.service.openapi.base.gateway.entity.QueryGwNodeEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月10日
*/
public interface IGatewayManageService {

	Result<GatewayColonyEntity> addGatewayCol(GatewayColonyEntity colEntity);
	
	Result<GatewayColonyEntity> modifyGatewayCol(GatewayColonyEntity colEntity);
	
	Result<Page<GatewayColonyEntity>> getAllGatewayCol(QueryGwColonyEntity queryEntity);
	
	Result<String> deleteGatewayColonyById(String colId);
	
	Result<String> addGatewayNode(GatewayNodeEntity nodeEntity);
	
	Result<Page<GatewayNodeEntity>> getAllGatewayNode(QueryGwNodeEntity queryEntity);
	
	Result<String> deleteGatewayNodeById(String nodeId);
	
	Result<String> modifyGatewayNodeById(GatewayNodeEntity nodeEntity);

//	CloudResult<?> migraGateway();
}
