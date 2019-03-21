package cn.ce.service.openapi.base.common.gateway;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import cn.ce.service.openapi.base.common.Constants;
import cn.ce.service.openapi.base.common.IOUtils;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.gateway.dao.IMysqlGwColonyDao;
import cn.ce.service.openapi.base.gateway.entity.GatewayColonyEntity;
import io.netty.handler.codec.http.HttpMethod;

@Slf4j
@Component
public class GatewayRouteUtils {


//	@Resource
//	private IGatewayColonyManageDao gatewayColonyManageDao;
	@Resource
	private IMysqlGwColonyDao mysqlGwColonyDao;
	
	
	private static GatewayRouteUtils utils;

	@PostConstruct
	public void init(){
		utils = this;
//		utils.gatewayColonyManageDao = this.gatewayColonyManageDao;
		utils.mysqlGwColonyDao = this.mysqlGwColonyDao;
	}

	public static String getRouteBySaasId(String saasId,String resourceType,String method){
//		GatewayColonyEntity colonySingle = utils.gatewayColonyManageDao.getColonySingle();
		GatewayColonyEntity colonySingle = utils.mysqlGwColonyDao.getAll().get(0);
		Result<String> result = new Result<>();
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		String jsonStr = null;
		try {
			jsonStr = getOrDelGwJsonApi(colonySingle.getColUrl() + 
					Constants.NETWORK_ROUTE_URL+"/"+saasId + "/" + resourceType,method);
		}catch(Exception e){
			log.error("调用网关时出现错误,信息为:" + jsonStr + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			jsonStr = result.toString();
		}
		
		return jsonStr;
		
	}
	
	public static String saveAppkey(String appkey,String tenantId,String method){
		
//		GatewayColonyEntity colonySingle = utils.gatewayColonyManageDao.getColonySingle();
		GatewayColonyEntity colonySingle = utils.mysqlGwColonyDao.getAll().get(0);
		
		Result<String> result = new Result<>();
		
		String gwJsonApi = "";
		
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		try {
			JSONObject params = new JSONObject();
			params.put("saas_id", tenantId);
			params.put("app_key", appkey);
			gwJsonApi = putOrPostGwJson(colonySingle.getColUrl()+Constants.NETWORK_APPKEY_URL+"/"+appkey, params,method);
		}catch(Exception e){
			log.error("调用网关时出现错误,信息为:" + gwJsonApi + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			gwJsonApi = result.toString();
		}
		return gwJsonApi;
	}
	
	public static String saveRoute(String saasId,String targetUrl,String resourceType,String method){
//		GatewayColonyEntity colonySingle = utils.gatewayColonyManageDao.getColonySingle();
		GatewayColonyEntity colonySingle = utils.mysqlGwColonyDao.getAll().get(0);
		Result<String> result = new Result<>();
		String gwJsonApi = "";
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		try {
			JSONObject params = new JSONObject();
			params.put("saas_id", saasId);
			params.put("target_url", targetUrl);
			params.put("resource_type",resourceType);
			gwJsonApi = putOrPostGwJson(colonySingle.getColUrl()+Constants.NETWORK_ROUTE_URL+"/"+saasId, params,method);
		}catch(Exception e){
			log.error("调用网关时出现错误,信息为:" + gwJsonApi + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			gwJsonApi = result.toString();
		}
		return gwJsonApi;
	}
	
	public static String deleteRoute(String saasId,String resourceType,String method){
//		GatewayColonyEntity colonySingle = utils.gatewayColonyManageDao.getColonySingle();
		GatewayColonyEntity colonySingle = utils.mysqlGwColonyDao.getAll().get(0);
		Result<String> result = new Result<>();
		String gwJsonApi = "";
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		try {
			gwJsonApi = getOrDelGwJsonApi(new StringBuilder().append(colonySingle.getColUrl())
					.append(Constants.NETWORK_ROUTE_URL).append("/")
					.append(saasId).append("/").append(resourceType).toString(),method);
		}catch(Exception e){
			log.error("调用网关时出现错误,信息为:" + gwJsonApi + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			gwJsonApi = result.toString();
		}
		return gwJsonApi;
	}
	
	public static String getOrDelGwJsonApi(String url,String method) throws Exception {
		
		log.info("***********在java中调用http/get/json请求***********");
		log.info("url:"+url);
		
		HttpClient httpClient = null;
		
		httpClient = ApiCallUtils.getHttpClient();
		
		HttpRequestBase hrb;
		
		if(HttpMethod.DELETE.toString().equals(method)){
			hrb = new HttpDelete(url);
		}else{
			hrb = new HttpGet(url);
		}
		
		
		hrb.addHeader(Constants.HEADER_KEY,Constants.HEADER_VALUE);
		
		HttpResponse response = null;
		
		response = httpClient.execute(hrb);
		
		StatusLine statusLine = response.getStatusLine();
		
		log.info("get status:"+statusLine.getStatusCode());
		
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		String str = IOUtils.convertStreamToString(is);
		
		return str;
	}

	/**
	 * 向网关发送httpPut请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String putOrPostGwJson(String url, JSONObject params, String method) throws Exception{
		
		log.info("***********在java中调用http/" + method + "/json请求***********");
		log.info("url:"+url);
		log.info("params:"+params.toString());
		String str = "";
		HttpClient httpClient = null;
		
		try{
			httpClient = ApiCallUtils.getHttpClient();
		}catch(Exception e){
			log.info("create httpClient error");
			return null;
		}
		
		HttpEntityEnclosingRequestBase hrb;
		if(HttpMethod.POST.toString().equals(method)){
			hrb = new HttpPost(url);
		} else {
			hrb = new HttpPut(url);
		}
		
		hrb.setHeader(HTTP.CONTENT_TYPE,"application/json");
		hrb.setHeader(Constants.HEADER_KEY,Constants.HEADER_VALUE);
		
		StringEntity strEntity =  new StringEntity(params.toString(),ContentType.APPLICATION_JSON);
		strEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,"utf-8"));
		
		hrb.setEntity(strEntity);
		
		HttpResponse response = null;
		response = httpClient.execute(hrb);
		//解析返回实体
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		str = IOUtils.convertStreamToString(is);
		return str;
	}
}
