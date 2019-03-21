package cn.ce.service.openapi.base.common.gateway;


import java.io.InputStream;
import java.util.List;

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
import cn.ce.service.openapi.base.gateway.dao.IMysqlGwNodeDao;
import cn.ce.service.openapi.base.gateway.entity.GatewayColonyEntity;
import cn.ce.service.openapi.base.gateway.entity.GatewayNodeEntity;
import io.netty.handler.codec.http.HttpMethod;



/**
 * 
 * @author makangwei
 * 2017-7-31
 *
 */
@Slf4j
@Component
public class GatewayUtils {


//	@Resource
//	private IGatewayManageDao gatewayManageDao;
//	@Resource
//	private IGatewayNodeManageDao gatewayNodeManageDao;
//	@Resource
//	private IGatewayColonyManageDao gatewayColonyManageDao;
	@Resource
	private IMysqlGwColonyDao gwColonyDao;
	@Resource
	private IMysqlGwNodeDao gwNodeDao;
	
	
	private static GatewayUtils utils;
	
	@PostConstruct
	public void init(){
		utils = this;
//		utils.gatewayManageDao = this.gatewayManageDao;
//		utils.gatewayNodeManageDao = this.gatewayNodeManageDao;
//		utils.gatewayColonyManageDao = this.gatewayColonyManageDao;
		utils.gwColonyDao = this.gwColonyDao;
		utils.gwNodeDao = this.gwNodeDao;
	}
	
	
//	/**
//	 * @Title: postApiToGateway
//	 * @Description: 添加api到网关，多版本加oauth
//	 * @author: makangwei
//	 * @date:   2017年10月13日 下午8:58:23 
//	 */
//	public static CloudResult<String> postApiToGateway(String apiName, String apiId, String listenPath,Map<String,String> versionUrl, boolean notVersioned){
//		
//		CloudResult<String> result = new CloudResult<String>();
//		
//		// TODO 这里添加api到节点。现在只有一个网关。所以忽略了根据网关来查询节点。如果后期有多个网关集群，则需要考虑多个集群多个节点上传api
//		List<GatewayNodeEntity> nodeList = getAllGatwayNodes();
//		
//		List<String> urlList = new ArrayList<String>();
//		
//		//添加api到每一个网关节点，如果发生异常，立即撤销本次所有的添加
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl()+Constants.NETWORK_ADD_PATH;
//			
//			boolean bool = ApiCallUtils.postGwJson(uri, params);
//			
//			if(!bool){
//				//回滚
//				deleteApiFromGatewayNodes(urlList,apiId);
//				result.setMessage("uri:"+gatewayNodeEntity.getNodeUrl()+"添加api失败,已回滚所有api");
//				return result;
//			}
//			
//			urlList.add(gatewayNodeEntity.getNodeUrl());
//		}
//		
//		//reload所有节点
//		List<String> urlList2 = new ArrayList<String>();
//		
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl()+Constants.NETWORK_RELOAD_PATH;
//			
//			if(!ApiCallUtils.getGwReload(uri)){
//				//回滚
//				deleteApiFromGatewayNodes(urlList2,apiId);
//				result.setMessage("uri:"+gatewayNodeEntity.getNodeUrl()+"reload失败，已回滚所有api");
//				return result;
//			}
//			urlList2.add(gatewayNodeEntity.getNodeUrl());
//		}
//
//		result.setStatus(Status.SUCCESS);
//		
//		return result;
//	}
	
//	/**
//	 * 根据id删除api
//	 * @param apiId
//	 * @return
//	 */
//	public static CloudResult<String> deleteApiFromGateway(String apiId){
//		
//		CloudResult<String> result = new CloudResult<String>();
//		
//		List<GatewayNodeEntity> nodeList = getAllGatwayNodes();
//		
//		//已经删除api的节点集合
//		List<String> deledList = new ArrayList<String>();
//		
//		// TODO 先拿到当前集群下旧的节点数据,现在是写死的，只拿到数据库中第一个集群中的旧数据
//		String oldApiStr = getOldApiEntity(apiId);
//		
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl();
//			
//			boolean flag = ApiCallUtils.deleteGwJson(uri+Constants.NETWORK_DEL_PATH+"/"+apiId);
//			
//			if(!flag){
//				
//				//发生异常时候，将原来已经删除的数据再恢复到节点
//				recoverApiFromGatewayNodes(deledList,apiId,oldApiStr);
//				
//				result.setMessage("节点"+gatewayNodeEntity.getNodeId()+"，url为："+gatewayNodeEntity.getNodeUrl()+"删除api发生异常");
//				
//				return result;
//			}
//			
//			deledList.add(gatewayNodeEntity.getNodeUrl());
//		}
//		
//		result.setMessage("api在所有集群中删除成功");
//		
//		return result;
//		
//	}
	
//	/**
//	 * 根据apiId修改网关，如果当前apiId在网关中不存在则在网关注册新的api
//	 * @return
//	 */
//	// TODO 目前暂不支持跟新操作。在网关上只能添加或者删除api
//	public static CloudResult<String> modifyApiFromGateway(String apiName,String apiId,String userId,String listenPath,String targetUrl,boolean status){
//		
//		CloudResult<String> result = new CloudResult<String>();
//		
//		ApiGatewayEntity apiGatewayEntity = new ApiGatewayEntity();
//		
//		
//		//先从网关查询就的api，防止发生异常时候能够回滚
//		//ApiGatewayEntity oldEntity = getOldApiEntity(apiId);
//		
//		
//		//API参数校验
//		if(apiName == null || apiName.trim() == ""){
//			result.setMessage("api名称不能为空");
//			return result;
//		}else if(userId == null || userId.trim() == ""){
//			result.setMessage("userId不能为空");
//			return result;
//		}else if(listenPath == null || listenPath.trim() == ""){
//			result.setMessage("listenPath不能为空");
//			return result;
//		}else if(targetUrl == null || targetUrl.trim() ==""){
//			result.setMessage("targetUrl不能为空");
//			return result;
//		}else if(apiId == null || apiId.trim() == ""){
//			apiGatewayEntity.setApi_id(RandomUtil.random16Number());
//		}
//		
//		apiGatewayEntity.setName(apiName);
//		apiGatewayEntity.setApi_id(apiId);
//		apiGatewayEntity.setOrg_id(userId);
//		
//		apiGatewayEntity.getProxy().setListen_path(listenPath);
//		apiGatewayEntity.getProxy().setTarget_url(targetUrl);
//		
//		ObjectMapper mapper = new ObjectMapper(); 
//		
//		JSONObject params = null;
//		try {
//			String str = mapper.writeValueAsString(apiGatewayEntity);
//			
//			params = new JSONObject(str);
//			
//			//将default属性转化为Default
//			log.info("params:"+params.toString());
//			JSONObject Default = params.getJSONObject("version_data").getJSONObject("versions").getJSONObject("default");
//			params.getJSONObject("version_data").getJSONObject("versions").remove("default");
//			params.getJSONObject("version_data").getJSONObject("versions").put("Default", Default);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			return result;
//		}
//		
//		// TODO 这里修改节点api。现在只有一个网关。所以忽略了根据网关来查询节点。如果后期有多个网关集群，则需要考虑多个集群多个节点上传api
//		List<GatewayNodeEntity> nodeList = getAllGatwayNodes();
//		
//		//修改每个网关上的api，如果发生异常，立即撤销本次所有的添加
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl()+Constants.NETWORK_UPDATE_PATH;
//			
//			boolean bool = ApiCallUtils.putGwJson(uri+"/"+apiId, params);
//			
//			if(!bool){
//				// TODO 如果修改失败返回未修改状态
////				deleteApiFromGatewayNodes(nodeList,apiId);
//				result.setMessage("uri:"+gatewayNodeEntity.getNodeUrl()+"修改api失败,已回滚所有api");
//			}
//			
//		}
//		
//		//reload所有节点
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl()+Constants.NETWORK_RELOAD_PATH;
//			
//			if(!ApiCallUtils.getGwReload(uri)){
//				// TODO 如果reload修改失败返回未修改状态
////				deleteApiFromGatewayNodes(nodeList,apiId);
//				result.setMessage("uri:"+gatewayNodeEntity.getNodeUrl()+"reload失败，已回滚所有api");
//				return result;
//			}
//		}
//
//		result.setStatus(Status.SUCCESS);
//		
//		return result;
//	}
	
	//添加key到网关
//	public static CloudResult<String> addKeyToGateway(Integer expires,Integer rate,Integer per,Integer quota_max,Integer quota_renewal_rate,
//			String apiName,String apiId,String secretKey,String version){
//		CloudResult<String> result = new CloudResult<String>();
//		
//		//拼接参数
//		GatewayKeyEntity gkEntity = new GatewayKeyEntity();
//		
//		//expires默认为-1，永不过期
//		gkEntity.setRate(rate);
//		
//		gkEntity.setPer(per);
//		
//		gkEntity.setQuota_max(quota_max);
//		
//		gkEntity.setQuota_renewal_rate(quota_renewal_rate);
//		
//		gkEntity.setQuota_remaining(quota_max);
//		
//		JSONObject api = new JSONObject();
//		
//		api.put("api_name", apiName);
//		
//		api.put("api_id", apiId);
//		
//		List<String> versions = new ArrayList<String>(); 
//		
//		versions.add(version);
//		
//		api.put("versions", versions);
//		
//		JSONObject job = new JSONObject();
//		
//		job.put(apiId, api);
//		
//		log.info(job.toString());
//		
//		gkEntity.setAccess_rights(job);
//		
//		//实体参数校验
//		log.info("----------添加key到网关的参数:"+gkEntity.toString());
//		
//		
//		
//		ObjectMapper mapper = new ObjectMapper(); 
//		
//		JSONObject params = null;
//		try {
//			//String str = mapper.writeValueAsString(gkEntity);
//			
//			params = new JSONObject(gkEntity);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return result;
//		}
//		//------------------------------以上是参数校验----------------------------------------------------------------------
//		
//		// TODO 这里添加api到节点。现在只有一个网关。所以忽略了根据网关来查询节点。如果后期有多个网关集群，则需要考虑多个集群多个节点上传api
//		List<GatewayNodeEntity> nodeList = getAllGatwayNodes();
//		
//		List<String> urlList = new ArrayList<String>();
//		
//		//添加api到每一个网关节点，如果发生异常，立即撤销本次所有的添加
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl()+Constants.NETWORK_ADD_KEY_PATH+"/"+secretKey;
//			
//			boolean bool = ApiCallUtils.postGwJson(uri, params);
//			
//			if(!bool){
//				//回滚
//				deleteKeyFromGatewayNodes(urlList,secretKey);
//				result.setMessage("uri:"+gatewayNodeEntity.getNodeUrl()+"添加api失败,已回滚所有api");
//				return result;
//			}
//			
//			urlList.add(gatewayNodeEntity.getNodeUrl());
//		}
//		
//		//reload所有节点
//		List<String> urlList2 = new ArrayList<String>();
//		
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl()+Constants.NETWORK_RELOAD_PATH;
//			
//			if(!ApiCallUtils.getGwReload(uri)){
//				//回滚
//				deleteKeyFromGatewayNodes(urlList2,secretKey);
//				result.setMessage("uri:"+gatewayNodeEntity.getNodeUrl()+"reload失败，已回滚所有api");
//				return result;
//			}
//			urlList2.add(gatewayNodeEntity.getNodeUrl());
//		}
//
//		result.setStatus(Status.SUCCESS);
//		
//		return result;
//	}
	
	/**
	 * 删除key到网关
	 * @return
	 */
//	public static CloudResult<String> deleteKeyFromGateway(String secretKey){
//		
//		CloudResult<String> result = new CloudResult<String>();
//		
//		List<GatewayNodeEntity> nodeList = getAllGatwayNodes();
//		
//		//已经删除api的节点集合
//		List<String> deledList = new ArrayList<String>();
//		
//		// TODO 先拿到当前集群下旧的节点数据,现在是写死的，只拿到数据库中第一个集群中的旧数据
//		String oldApiStr = getOldKeyEntity(secretKey);
//		
//		if(oldApiStr == Status.FAILED){
//			log.info("-------------该key在网关中不存在----------------");
//			result.setStatus(Status.SUCCESS);
//			return result;
//		}else if(oldApiStr == null){
//			return result;
//		}
//		
//		for (GatewayNodeEntity gatewayNodeEntity : nodeList) {
//			
//			String uri = gatewayNodeEntity.getNodeUrl();
//			
//			boolean flag = ApiCallUtils.deleteGwJson(uri+Constants.NETWORK_DEL_KEY_PATH+"/"+secretKey);
//			
//			if(!flag){
//				
//				//发生异常时候，将原来已经删除的数据再恢复到节点
//				recoverApiFromGatewayNodes(deledList,secretKey,oldApiStr);
//				
//				log.error("节点"+gatewayNodeEntity.getNodeId()+"，url为："+gatewayNodeEntity.getNodeUrl()+"删除secretKey发生异常");
//				result.setMessage("系统网关异常");
//				
//				return result;
//			}
//			
//			deledList.add(gatewayNodeEntity.getNodeUrl());
//		}
//		
//		result.setStatus(Status.SUCCESS);
//		result.setMessage("密钥在所有集群中删除成功");
//		
//		return result;
//		
//	}


	//查询所有集群
	public static List<GatewayColonyEntity> getAllGatewayColony(){
		
//		List<GatewayColonyEntity> colonyList = utils.gatewayManageDao.getAll(GatewayColonyEntity.class);
		List<GatewayColonyEntity> colonyList = utils.gwColonyDao.getAll();
		
		return colonyList;
	}
	
	//根据集群id查询当前集群下的所有节点
	public static List<GatewayNodeEntity> getGatwayNodesByColonyId(String colId){
		
//		List<GatewayNodeEntity> nodeList = utils.gatewayNodeManageDao.findByField("colId", colId, GatewayNodeEntity.class);
		List<GatewayNodeEntity> nodeList = utils.gwNodeDao.findByColId(colId);
		
		return nodeList;
		
	}
	
	//查询所有集群节点
	public static List<GatewayNodeEntity> getAllGatwayNodes(){
		
//		List<GatewayNodeEntity> nodeList = utils.gatewayNodeManageDao.getAll(GatewayNodeEntity.class);
		List<GatewayNodeEntity> nodeList = utils.gwNodeDao.getAll();
		
		return nodeList;
		
	}
	
	//批量删除网关节点上的api
//	private static void deleteApiFromGatewayNodes(List<String> urlList, String apiId) {
//		
//		for (String uri : urlList) {
//			ApiCallUtils.deleteGwJson(uri+Constants.NETWORK_DEL_PATH+"/"+apiId);
//		}
//		
//	}
	
	//网关删除密钥
//	private static void deleteKeyFromGatewayNodes(List<String> urlList, String keyId) {
//		
//		for(String uri : urlList){
//			ApiCallUtils.deleteGwJson(uri+Constants.NETWORK_DEL_KEY_PATH+"/"+keyId);
//		}
//		
//	}
	
	//批量恢复网关节点的api
//	private static void recoverApiFromGatewayNodes(List<String> urlList,String apiId,String str){
//		
//		for (String uri : urlList) {
//			
//			JSONObject job = new JSONObject(str);
//			
//			boolean flag1 = ApiCallUtils.putGwJson(uri+Constants.NETWORK_UPDATE_PATH+"/"+apiId, job);
//			
//			if(!flag1){
//				// TODO 如果api恢复回退的过程中也发生异常需要处理
//			}
//		}
//	}
	
	//根据apiId查询旧的ApiGatewayEntity
//	private static String getOldApiEntity(String apiId) {
//		
//		List<GatewayColonyEntity> colList = getAllGatewayColony();
//		
//		if(colList.size() < 1){
//			log.error("当前数据库中集群为空！");
//			return null;
//		}
//		
//		// TODO　目前只有一个集群，只从第一个集群里拿数据。后期需要考虑多个集群中拿数据
//		String jsonStr = null;
//		try {
//			jsonStr = ApiCallUtils.getGwJsonApi(colList.get(0).getColUrl()+Constants.NETWORK_GET_PATH+"/"+apiId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			jsonStr = null;
//		} 
//		
//		if(jsonStr == null){
//			log.error("--------根据apiId从当前集群中查询不到旧的api，集群可能有异常");
//		}
//		log.info("-----"+jsonStr+"-----");
//		
//		return jsonStr;
//		
//	}
	//根据密钥查询旧的密钥实体
//	private static String getOldKeyEntity(String secretKey) {
//		
//		List<GatewayColonyEntity> colList = getAllGatewayColony();
//		
//		if(colList.size() < 1){
//			log.error("当前数据库中集群为空！");
//			return null;
//		}
//		
//		// TODO　目前只有一个集群，只从第一个集群里拿数据。后期需要考虑多个集群中拿数据
//		String jsonStr = null;
//		try {
//			jsonStr = ApiCallUtils.getGwJsonApi(colList.get(0).getColUrl()+Constants.NETWORK_DEL_KEY_PATH+"/"+secretKey);
//		} catch (Exception e) {
//			e.printStackTrace();
//			jsonStr = null;
//			return jsonStr;
//		} 
//		
//		if(jsonStr == null){
//			log.error("--------根据secretKey从当前集群中查询不到旧的secretKey，集群可能有异常");
//			return Status.FAILED;
//		}
//		
//		log.info("-----"+jsonStr+"-----");
//		
//		return jsonStr;
//	}
	
	/** >>>>>>>>>>>>>>>>>>>>>>>>>>> 网关路由操作开始  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	
	public static String getRouteBySaasId(String saasId,String method){
		GatewayColonyEntity colonySingle = utils.gwColonyDao.getAll().get(0);
		Result<String> result = new Result<>();
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		String jsonStr = null;
		try {
			jsonStr = getOrDelGwJsonApi(colonySingle.getColUrl()+Constants.NETWORK_ROUTE_URL+"/"+saasId,method);
		}catch(Exception e){
			log.error("调用网关时出现错误,信息为:" + jsonStr + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			jsonStr = result.toString();
		}
		
		return jsonStr;
		
	}
	
	public static String saveRoute(String saasId,String targetUrl,String method){
		GatewayColonyEntity colonySingle = utils.gwColonyDao.getAll().get(0);
		Result<String> result = new Result<>();
		String gwJsonApi = "";
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		try {
			JSONObject params = new JSONObject();
			params.put("target_url", targetUrl);
			gwJsonApi = putOrPostGwJson(colonySingle.getColUrl()+Constants.NETWORK_ROUTE_URL+"/"+saasId, params,method);
		}catch(Exception e){
			log.error("调用网关时出现错误,信息为:" + gwJsonApi + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			gwJsonApi = result.toString();
		}
		return gwJsonApi;
	}
	
	public static String deleteRoute(String saasId,String method){
		GatewayColonyEntity colonySingle = utils.gwColonyDao.getAll().get(0);
		Result<String> result = new Result<>();
		String gwJsonApi = "";
		if(null == colonySingle){
			log.error("网关集群信息为空!");
			result.setErrorMessage("网关集群信息为空！");
			return result.toString();
		}
		
		String jsonStr = null;
		try {
			jsonStr = getOrDelGwJsonApi(colonySingle.getColUrl()+Constants.NETWORK_ROUTE_URL+"/"+saasId,method);
		}catch(Exception e){
			log.error("调用网关时出现错误,地址为:" + jsonStr + ",异常:" + e.toString());
			result.setErrorMessage("服务异常!");
			gwJsonApi = result.toString();
		}
		return gwJsonApi;
	}
	
	
	
	/** >>>>>>>>>>>>>>>>>>>>>>>>>>> 网关路由操作结束  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	
	
	
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

