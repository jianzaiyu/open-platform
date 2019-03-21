package cn.ce.service.openapi.base.gateway.service;

/**
* @Description : 提供给中台。对外的saas service
* @Author : makangwei
* @Date : 2018年3月22日
*/
public interface ISaasService {

//	int save(SaasEntity saas);

	void clearAll();

	String getSaas(String saasId, String resourceType, String method);
	
	String saveSaas(String saasId, String resourceType, String targetUrl, String method);

	String deleteSaas(String saasId, String resourceType, String method);
	
}

