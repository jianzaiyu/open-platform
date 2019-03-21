package cn.ce.service.openapi.base.oauth.service;//package cn.ce.service.openapi.base.oauth.service;
//
//import java.util.Map;
//
//import cn.ce.service.openapi.base.common.CloudResult;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年8月17日
//*/
//public interface IOauthService {
//
//	Object getClientIdAndSecret(String apiId, String redirectUri);
//
//	/***
//	 * 根据参数访问tyk网关生成code
//	 * @param request 
//	 * @param clientId
//	 * @param response_type
//	 * @param redirect_uri
//	 * @throws Exception
//	 */
//	CloudResult<String> generalAuthCode(String requestUri, String clientId,String response_type,String redirect_uri) throws Exception;
//	int findByFields(Map<String, Object> queryMap);
//
//}
