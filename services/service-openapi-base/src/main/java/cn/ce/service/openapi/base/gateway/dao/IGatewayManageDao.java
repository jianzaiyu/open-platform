package cn.ce.service.openapi.base.gateway.dao;//package cn.ce.service.openapi.base.gateway.dao;
//
//import java.util.List;
//
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.gateway.entity.GatewayColonyEntity;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年10月10日
//*/
//public interface IGatewayManageDao {
//
//	boolean addGatewayCol(GatewayColonyEntity colEntity);
//	
//	Page<GatewayColonyEntity> getAllGatewayCol(Integer currentPage,Integer pageSize);
//	
//	boolean deleteGatewayColonyById(Integer colId);
//	
//	void updateById(String colId, GatewayColonyEntity entity);
//	
//	List<GatewayColonyEntity> findByField(String field, Object value, Class<?> entityClass);
//	
//	GatewayColonyEntity findById(String id, Class<?> entityclass);
//
//	List<GatewayColonyEntity> getAll(Class<?> class1);
//
//	List<GatewayColonyEntity> checkUrl(String colUrl, String colId);
//
//	GatewayColonyEntity findById(String colId);
//}
