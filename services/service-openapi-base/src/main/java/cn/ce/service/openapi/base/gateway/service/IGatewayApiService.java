package cn.ce.service.openapi.base.gateway.service;

import java.util.List;
import java.util.Map;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月10日
*/
public interface IGatewayApiService {


	String pushPolicy(String policyId, Integer rate, Integer per, Integer quotaMax,
                      Integer quotaRenewRate, Map<String, List<String>> apiInfos /**key是versionId,value是多个version的名称*/);
	
	String pushClient(String clientId, String secret, StringBuffer versionIdsBuf, String policyId);
	
	
}
