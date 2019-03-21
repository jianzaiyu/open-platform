package cn.ce.service.openapi.base.oauth.service.impl;//package cn.ce.service.openapi.base.oauth.service.impl;
//
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//import cn.ce.service.openapi.base.apis.entity.ApiAuditEntity;
//import cn.ce.service.openapi.base.apis.service.IApiOauthService;
//import cn.ce.service.openapi.base.common.Constants;
//import cn.ce.service.openapi.base.common.CloudResult;
//import cn.ce.service.openapi.base.common.gateway.GatewayUtils;
//import cn.ce.service.openapi.base.oauth.dao.IOauthDao;
//import cn.ce.service.openapi.base.oauth.entity.AuthorizeClientEntity;
//import cn.ce.service.openapi.base.oauth.service.IOauthService;
//import cn.ce.service.openapi.base.openApply.dao.IOpenApplyDao;
//import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
//import cn.ce.service.openapi.base.util.HttpUtils;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年8月17日
//*/
//@Service
//public class OauthServiceImpl implements IOauthService{
//
//	private Logger logger = LoggerFactory.getLogger(OauthServiceImpl.class);
//	@Autowired
//	private IOauthDao oauthDao;
//	
////	@Autowired 
////	private IManageOpenApplyService appService;
//	@Resource
//	private IOpenApplyDao openApplyDao;
//    
////    @Autowired 
////    private IApiOauthService apiAuditService;
//    
//	@Override
//	public Object getClientIdAndSecret(String apiId, String redirectUri) {
//		
//		JSONObject job = new JSONObject();
//		
//		job.put("api_id", apiId);
//		job.put("redirect_uri", redirectUri);
//		
//		String params = job.toString();
//		Map<String,String> headers = new HashMap<String,String>();
//		headers.put(Constants.HEADER_KEY, Constants.HEADER_VALUE);
//		
//		String str = HttpUtils.postJson(GatewayUtils.getAllGatewayColony().get(0).getColUrl()+Constants.NETWORK_CREATE_CLIENT, params, headers);
//		
//		if(str == null){
//			return "system error,check params if it's correct";
//		}
//		
//		return str;
//	}
//	@Override
//	public CloudResult<String> generalAuthCode(String requestUri, String clientId, String response_type, String redirect_uri) throws Exception {
////		String str = "";
//		CloudResult<String> result = new CloudResult<String>();
//		//根据clientId加载api信息
//		ApiAuditEntity findApiByClientId = apiAuditService.findApiByClientId(clientId);
//		if(null != findApiByClientId){
//			//获取api信息中相关配额 频次等限定
//			AuthorizeClientEntity ace = new AuthorizeClientEntity();
//			ace.setPer(findApiByClientId.getPer());
//			ace.setQuotaMax(findApiByClientId.getQuotaMax());
//			ace.setQuotaRenewalRate(findApiByClientId.getQuotaRenewalRate());
//			ace.setRate(findApiByClientId.getRate());
//			
//			ace.setAllowance(ace.getRate());//等同于rate
//			ace.setExpires(-1);//默认值
//			ace.setQuotaRenews(0);//默认值
//			ace.setQuotaRemaining(ace.getQuotaMax());//等同与quota_max
//			
//			//根据clientId,responseType,redirectURI,api等信息生成json
//			JSONObject json = generalRequestJson(clientId,response_type,URLEncoder.encode(redirect_uri, "UTF-8"),findApiByClientId,ace);
//			
//			logger.info("发送申请code接口的参数json:" + json.toJSONString());
//			
//			//转换json为请求body请求对象
//			StringBuffer sb = new StringBuffer();
//			for (Entry<String, Object> entry : json.entrySet()) {
//				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//			}
//			
//			OpenApplyEntity appEntity = openApplyDao.findById(findApiByClientId.getAppId());
//			
//			// TODO 监听路径是否需要加上appKey
//			String listenPath = appEntity.getApplyKey()+"/"+findApiByClientId.getApiEnName();/*+"/"+findApiByClientId.getVersion();*/
//			
//			//请求tyk参数
//			String params = sb.substring(0, sb.length()-1);
//			Map<String,String> headers = new HashMap<String,String>();
//			headers.put(Constants.HEADER_KEY, Constants.HEADER_VALUE);
//			headers.put(Constants.HEADER_CONTENT_TYPE, Constants.HEADER_FORM_REQUEST);
//			
//			CloudResult<String> httpResult = HttpUtils.postJsonWithStateCode(GatewayUtils.getAllGatewayColony().get(0).getColUrl()+Constants.NETWORK_AUTHORIZE_CLIENT.replace("${listen_path}", listenPath), params, headers);
//			
//			if(httpResult == null){
//				result.setMessage("system error");
//			}else{
//				//往请求过来的地址回推code
////				if("200".equals(httpResult.getStatus())){
////					try{
////						org.json.JSONObject job = new org.json.JSONObject(httpResult.getData());
////						String code = job.getString("code");
////						HttpUtils.resentCode(requestUri,code); //这里的get请求默认已经在请求地址后面拼接了问号
////					}catch(Exception e){
////						e.printStackTrace();
////					}
////				}
//				return httpResult;
//			}
//		}else{
//			result.setMessage("clientId does not exists,please try again!");
//		}
//		logger.info(result.getData());
//		return result;
//	}
//	
//	/***
//	 * 根据参数构建访问网关生成code得JSON
//	 * @param clientId
//	 * @param response_type
//	 * @param redirect_uri
//	 * @param ace
//	 * @return
//	 */
//	private JSONObject generalRequestJson(String clientId,String response_type,String redirect_uri,ApiAuditEntity aae,AuthorizeClientEntity ace){
//		JSONObject json = new JSONObject();
//		json.put("client_id", clientId);
//		json.put("response_type", response_type);
//		json.put("redirect_uri", redirect_uri);
//		json.put("scope", "");
//		json.put("state", "");
//		
//		String[] versionArray = new String[1];
//		versionArray[0] = aae.getVersion();
//		
//		JSONObject apiVersionJson = new JSONObject();
//		apiVersionJson.put("api_name", aae.getApiEnName());
//		apiVersionJson.put("api_id", aae.getApiId());
//		apiVersionJson.put("versions", versionArray);
//		
//		
//		JSONObject accessRightsJson = new JSONObject();
//		accessRightsJson.put(aae.getApiId(), apiVersionJson);
//		
//		JSONObject keyRulesJson = JSONObject.parseObject(JSON.toJSONString(ace));
//		keyRulesJson.put("access_rights",accessRightsJson);
//		keyRulesJson.put("org_id", "default");
//		try {
//			json.put("key_rules", URLEncoder.encode(keyRulesJson.toJSONString(),"UTF-8"));
//		} catch (Exception e) {
//			logger.error("转换key_rules出现错误!e:" + e.toString());
//		}
//
//		return json;
//	}
//
//	@Override
//	public int findByFields(Map<String, Object> queryMap) {
//
//		return oauthDao.findByFields(queryMap);
//	}
//
//}
