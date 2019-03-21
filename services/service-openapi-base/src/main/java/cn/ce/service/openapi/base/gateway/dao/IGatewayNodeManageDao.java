package cn.ce.service.openapi.base.gateway.dao;//package cn.ce.service.openapi.base.gateway.dao;
//
//import java.util.List;
//
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.gateway.entity.GatewayNodeEntity;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年10月10日
//*/
//public interface IGatewayNodeManageDao {
//
//	boolean addGatewayNode(GatewayNodeEntity nodeEntity);
//	
//	Page<GatewayNodeEntity> getAllGatewayNode(Integer currentPage, Integer pageSize, String colId);
//	
//	boolean deleteGatewayNodeById(String nodeId);
//
//	List<GatewayNodeEntity> findByField(String string, String nodeId, Class<?> class1);
//
//	void updateById(String nodeId, GatewayNodeEntity nodeEntity);
//
//	List<GatewayNodeEntity> findByField(String string, Object colId, Class<?> class1);
//
//	List<GatewayNodeEntity> getAll(Class<?> class1);
//
//	List<GatewayNodeEntity> checkNodeUrl(String nodeUrl, String nodeId);
//
//	List<GatewayNodeEntity> getAll(String colId);
//}
