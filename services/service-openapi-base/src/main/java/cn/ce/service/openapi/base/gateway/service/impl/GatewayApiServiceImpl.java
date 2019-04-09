package cn.ce.service.openapi.base.gateway.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.Constants;
import cn.ce.service.openapi.base.common.DBFieldsConstants;
import cn.ce.service.openapi.base.common.gateway.ApiCallUtils;
import cn.ce.service.openapi.base.common.gateway.GatewayUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.gateway.service.IGatewayApiService;
import io.netty.handler.codec.http.HttpMethod;

/**
* @Description : 网关api和密钥的管理
* @Author : makangwei
* @Date : 2017年8月14日
*/
@Service("gatewayApiService")
@Transactional(propagation=Propagation.REQUIRED)
public class GatewayApiServiceImpl implements IGatewayApiService{

//	@Autowired @Qualifier("diyApplyDao")
//	private IDiyApplyDao diyApplyDao;
	
	//private static Logger logger = LoggerFactory.getLogger(GatewayApiServiceImpl.class);

	
	public String pushPolicy(
			String policyId,
			Integer rate,
			Integer per,
			Integer quotaMax,
			Integer quotaRenewRate,
			Map<String,List<String>> apiInfos /**key是versionId,value是多个version的名称*/
			){
		
		JSONObject params = generatePolicyJson(policyId,rate,per,quotaMax,quotaRenewRate,apiInfos);
		
		String url = GatewayUtils.getAllGatewayColony().get(0).getColUrl()+
				Constants.NETWORK_ADD_POLICY+"/"+policyId;
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(Constants.HEADER_KEY, Constants.HEADER_VALUE);
		
		String str = ApiCallUtils.putOrPostMethod(url, params, headers, HttpMethod.POST);
		
		return str;
	}
	
	public String pushClient(String clientId, String secret, StringBuffer versionIdsBuf, String policyId){
		
		JSONObject params = new JSONObject();
		params.put(DBFieldsConstants.GW_CLIENT_ID, clientId);
		params.put(DBFieldsConstants.GW_SECRET, secret);
		params.put(DBFieldsConstants.GW_POLICY_ID_FULL, policyId);
		params.put(DBFieldsConstants.GW_API_ID, versionIdsBuf);
		params.put(DBFieldsConstants.GW_REDIRECT_URI, "/");
		
		String url = GatewayUtils.getAllGatewayColony().get(0).getColUrl()+
				Constants.NETWORK_ADD_CLIENT_ID;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(Constants.HEADER_KEY, Constants.HEADER_VALUE);
		String str = ApiCallUtils.putOrPostMethod(url, params, headers, HttpMethod.POST);
		return str;
	}

	private JSONObject generatePolicyJson(String policyId, Integer rate, Integer per, Integer quotaMax, Integer quotaRenewRate,
			Map<String, List<String>> apiInfos) {//键是一组versionId相同版本不同的api的versionId，值是所有的version名称
		
		JSONObject policyJson = new JSONObject();
		policyJson.put(DBFieldsConstants.GW_POLICY_ORG_ID, AuditConstants.GATEWAT_DEFAULT);
		policyJson.put(DBFieldsConstants.GW_POLICY_ID, policyId);
		policyJson.put(DBFieldsConstants.GW_POLICY_RATE, rate);
		policyJson.put(DBFieldsConstants.GW_POLICY_PER, per);
		policyJson.put(DBFieldsConstants.GW_POLICY_QUOTA_MAX, quotaMax);
		policyJson.put(DBFieldsConstants.GW_POLICY_QUOTA_RENEWAL_RATE, quotaRenewRate);
		
		JSONObject accessRights = new JSONObject();
		for (String key : apiInfos.keySet()) {
			JSONObject apiJson = new JSONObject();
			apiJson.put(DBFieldsConstants.GW_POLICY_ACCESS_RIGHTS_APINAME, "");
			apiJson.put(DBFieldsConstants.GW_POLICY_ACCESS_RIGHTS_APIID, key);
			apiJson.put(DBFieldsConstants.GW_POLICY_ACCESS_RIGHTS_VERSIONS, apiInfos.get(key));
			accessRights.put(key, apiJson);
		}	
		policyJson.put(DBFieldsConstants.GW_POLICY_ACCESS_RIGHTS, accessRights);
		
		return policyJson;
	}
	
}
