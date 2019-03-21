package cn.ce.service.openapi.base.gateway.service;


import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.gateway.entity.QuerySaasEntity;
import cn.ce.service.openapi.base.gateway.entity.SaasEntity;

/**
* @Description : 开放平台内部使用的saas service
* @Author : makangwei
* @Date : 2018年3月31日
*/
public interface ISaasService1 {

	Result<?> saveOrUpdateBoxSaas(SaasEntity saasEntity);

	Result<?> deleteBoxSaas(String routeId);

	Result<?> getBoxSaas(String routeId);

	Result<?> boxSaasList(QuerySaasEntity saas);

	Result<?> migraSaas(SaasEntity saas);

//	SaasEntity getBoxSaas(String saasId, String resourceType, String boxId, String method);
//	
//	int saveBoxSaas(String saasId, String resourceType, String targetUrl, String boxId, String method);
//	
//	int deleteBoxRoute(String saasId, String resourceType, String boxId, String method);
//	
//	List<SandBox> getSendBoxSaasList(SaasEntity saas);
//
//	Page<SaasEntity> routeList(QuerySaasEntity saas);
	
}

