package cn.ce.service.openapi.base.gateway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ce.service.openapi.base.gateway.entity.GatewayNodeEntity;
import cn.ce.service.openapi.base.gateway.entity.QueryGwNodeEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月30日
*/
public interface IMysqlGwNodeDao {
	
	int checkByUrl(String nodeUrl);

	int checkByUrlExp(@Param("nodeUrl") String nodeUrl, @Param("nodeId") String nodeId);
	
	int addGatewayNode(GatewayNodeEntity nodeEntity);

	GatewayNodeEntity findById(String nodeId);

	int update(GatewayNodeEntity nodeEntity);
	
	int findListSize(QueryGwNodeEntity nodeEntity);

	List<GatewayNodeEntity> getPagedList(QueryGwNodeEntity queryEntity);

	int deleteById(String nodeId);

	List<GatewayNodeEntity> findByColId(String colId);

	List<GatewayNodeEntity> getAll();

	int deleteAll();
}
