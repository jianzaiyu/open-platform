package cn.ce.service.openapi.base.apis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import cn.ce.service.openapi.base.apis.dao.*;
import cn.ce.service.openapi.base.apis.entity.*;
import cn.ce.service.openapi.base.apis.util.ApiTransform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.ce.service.openapi.base.apis.dao.IMysqlApiArgDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiCodeDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiHeaderDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiQueryArgDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiResultDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiRexampleDao;
import cn.ce.service.openapi.base.apis.entity.ApiArgEntity;
import cn.ce.service.openapi.base.apis.entity.ApiCodeEntity;
import cn.ce.service.openapi.base.apis.entity.ApiEntity;
import cn.ce.service.openapi.base.apis.entity.ApiHeaderEntity;
import cn.ce.service.openapi.base.apis.entity.ApiResultEntity;
import cn.ce.service.openapi.base.apis.entity.ApiResultExampleEntity;
import cn.ce.service.openapi.base.apis.entity.DiyApplyBound;
import cn.ce.service.openapi.base.apis.entity.ErrorCodeEntity;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.entity.OpenApplyBound;
import cn.ce.service.openapi.base.apis.entity.QueryApiEntity;
import cn.ce.service.openapi.base.apis.entity.RetEntity;
import cn.ce.service.openapi.base.apis.entity.RetExamEntity;
import cn.ce.service.openapi.base.apis.entity.SubArgEntity;
import cn.ce.service.openapi.base.apis.service.IConsoleApiService;
import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.DBFieldsConstants;
import cn.ce.service.openapi.base.common.EnvironmentConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.HttpClientUtil;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.common.gateway.GatewayUtils;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.dao.IMysqlDiyApplyDao;
import cn.ce.service.openapi.base.gateway.entity.GatewayColonyEntity;
import cn.ce.service.openapi.base.gateway.service.IGatewayApiService;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.util.PropertiesUtil;
import cn.ce.service.openapi.base.util.RandomUtil;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月12日
*/
@Slf4j
@Service(value="consoleApiService")
@Transactional(propagation=Propagation.REQUIRED)
public class ConsoleApiServiceImpl implements IConsoleApiService{

	@Autowired
	private PropertiesUtil propertiesUtil;
//	@Resource
//	private INewApiDao newApiDao;
	@Resource
	private IMysqlApiDao mysqlApiDao;
	@Resource
	private IMysqlDiyApplyDao mysqlDiyApplyDao;
	@Resource
	private IMysqlApiHeaderDao apiHeaderDao;
	@Resource
	private IMysqlApiArgDao apiArgDao;
	@Resource
	private IMysqlApiQueryArgDao apiQueryArgDao;
	@Resource
	private IMysqlApiResultDao apiResultDao;
	@Resource
	private IMysqlApiRexampleDao apiRexampleDao;
	@Resource 
	private IMysqlApiCodeDao apiCodeDao;
	@Resource
	private IGatewayApiService gatewayApiService;
	
	
	/**
	 * 
	 * @Title: publishApi
	 * @Description: api发布
	 * @author: makangwei 
	 * @date:   2017年10月12日 上午10:58:11 
	 */
	@Override
	public Result<?> publishApi(String sourceConfig, User user, NewApiEntity apiEntity) {
		
		try{ //资源类型校验
			if(!getResourceType(sourceConfig).getData().toString().contains(apiEntity.getResourceType())){
				return new Result<String>("resourceType不正确", ErrorCodeNo.SYS008, null, Status.FAILED);
			}
		}catch(Exception e ){
			log.error("network error, cannot get resource type from zhong tai", e);
		}
		
		if(StringUtils.isBlank(apiEntity.getListenPath())){
			return new Result<String>("listenPath不能为空", ErrorCodeNo.SYS005,null,Status.FAILED);
		}
		
		apiEntity.setListenPath(checkListenPathFormat(apiEntity.getListenPath()));
		
		log.info("************************** listenPath = " + apiEntity.getListenPath());
		
		//设置默认值，否则会后面审核api会报错
		if(null == apiEntity.getCheckState() 
				|| apiEntity.getCheckState() > AuditConstants.DIY_APPLY_CHECKED_COMMITED){
			apiEntity.setCheckState(0);
		}
		
		//如果apiId存在，则修改api
		if(apiEntity.getId() != null ){
			boolean bool = updateMysqlEntity(apiEntity);
			if(bool){
				return new Result<String>("修改成功",ErrorCodeNo.SYS000,null,Status.SUCCESS);
			}else{
				return new Result<String>("修改失败，请检查当前id是否存在",ErrorCodeNo.SYS006,null,Status.FAILED);
			}
		}
		
		
		if(StringUtils.isBlank(apiEntity.getVersion())){
			return new Result<String>("版本名称不能为空", ErrorCodeNo.SYS005,null,Status.FAILED);
		}
		
		int versionNum = mysqlApiDao.findVersionNum(apiEntity.getVersionId(),apiEntity.getVersion());
		
		if(versionNum > 0){
			return new Result<String>("当前版本已经存在", ErrorCodeNo.SYS025, null, Status.FAILED);
		}

			// 开启版本控制
			if(StringUtils.isBlank(user.getId().toString())
					|| StringUtils.isBlank(user.getUsername())
					|| StringUtils.isBlank(user.getEnterpriseName())){
				log.info("用户信息不完整："+user.toString());
				return Result.errorResult("用户信息错误", ErrorCodeNo.SYS028, null, Status.FAILED);
			}
			apiEntity.setUserId(user.getId().toString());
			apiEntity.setUserName(user.getUsername());
			apiEntity.setEnterpriseName(user.getEnterpriseName());
			apiEntity.setApiSource(AuditConstants.API_SOURCE_TYPEIN);
			apiEntity.setCreateTime(new Date());
			
			//int num = newApiDao.updApiVersionByApiId(apiEntity.getVersionId(),false);
			int num = mysqlApiDao.updateVersionByVersionId(apiEntity.getVersionId(),false);
			log.info("----->将原来其他版本的api的newVersion字段全部修改为false，一共修改了"+num+"条数据");
			
			// TODO 前端传入版本号和newVersion字段吗？
			apiEntity.setNewVersion(true);
			//如果前端没有传入版本的版本的apiId则重新生成版本versionId
			if(StringUtils.isBlank(apiEntity.getVersionId())){
				log.info("当前添加的api是新的api不存在旧的版本，生成新的versionId");
				String versionId = UUID.randomUUID().toString().replace("-", "");
				apiEntity.setVersionId(versionId);
			}
			
			apiEntity.setId(RandomUtil.random32UUID());
			saveMysqlEntity(apiEntity);
			
			log.info("------新添加的数据为："+apiEntity.toString());
//		}
		return new Result<String>("添加成功",ErrorCodeNo.SYS000,null,Status.SUCCESS);
	}
	

	/**
	 * @Title: apiVerify
	 * @Description: 从未审核状态改为待审核状态,批量提交审核
	 * @author: makangwei 
	 * @date:   2017年10月12日 上午11:05:13 
	 */
	@Override
	public Result<?> submitApi(List<String> apiIds) {
		
		Result<String> result = new Result<String>();
		for (String apiId : apiIds) {
			//ApiEntity api = newApiDao.findApiById(apiId);
			NewApiEntity api = mysqlApiDao.findById(apiId);
			if(null == api){
				log.info("当前api:"+apiId+"在数据库中不存在");
				continue;
			}
			api.setCheckState(AuditConstants.API_CHECK_STATE_UNAUDITED);
			//newApiDao.save(api);
			//int temp = mysqlApiDao.saveOrUpdateEntity(api);
			mysqlApiDao.updateCheckState(api.getId(),AuditConstants.API_CHECK_STATE_UNAUDITED);
		}
		result.setSuccessMessage("提交成功");
		return result;
	}

	
	/**
	 * 
	 * @Title: modifyApi
	 * @Description: api修改，审核成功后无法再修改
	 * @author: makangwei 
	 * @date:   2017年10月12日 上午11:18:39 
	 */
	@Override
	public Result<?> modifyApi(String sourceConfig,NewApiEntity apiEntity) {
		
		// TODO 修改之前做校验
		if(!getResourceType(sourceConfig).getData().toString().contains(apiEntity.getResourceType())){
			return new Result<String>("resourceType不正确", ErrorCodeNo.SYS008, null, Status.FAILED);
		}
		if(StringUtils.isBlank(apiEntity.getListenPath())){
			return new Result<String>("listenPath不能为空", ErrorCodeNo.SYS005,null,Status.FAILED);
		}
		
		apiEntity.setListenPath(checkListenPathFormat(apiEntity.getListenPath()));
		
		if(StringUtils.isBlank(apiEntity.getVersion())){
			return new Result<String>("版本名称不能为空", ErrorCodeNo.SYS005,null,Status.FAILED);
		}
		
		// 第一次添加接口,并且选择未开启版本控制
		
		NewApiEntity entity = mysqlApiDao.findById(apiEntity.getId());
		
		if(null == entity){
			return Result.errorResult("当前id不存在", ErrorCodeNo.SYS006, null, Status.FAILED);
		}
		
		if((!entity.getUserId().equals(apiEntity.getUserId()))){
			return Result.errorResult("用户信息错误", ErrorCodeNo.SYS028, null, Status.FAILED);
		}
		
		int versionNum = mysqlApiDao.findVersionNumExpId(apiEntity.getId(), apiEntity.getVersionId(), apiEntity.getVersion());
		
		if(versionNum > 0){
			return new Result<String>("当前版本已经存在", ErrorCodeNo.SYS025, null, Status.FAILED);
		}
			
		boolean bool;
		if(null != entity && AuditConstants.API_CHECK_STATE_SUCCESS == entity.getCheckState()){
			String env = propertiesUtil.getValue("environment");
			if(EnvironmentConstants.local.toString().equals(env) || 
					EnvironmentConstants.test.toString().equals(env)){
				bool = updateSuccessEntity(apiEntity); //修改审核成功的api
			}else{
				log.info("当前环境："+env+",不支持审核通过的api:"+apiEntity.getId()+"的修改");
				bool = false;
			}
		}else{
			bool = updateMysqlEntity(apiEntity);
		}
			
		if(bool){
			return new Result<String>("修改成功",ErrorCodeNo.SYS000,null,Status.SUCCESS);
		}else{
			return new Result<String>("部分参数错误", ErrorCodeNo.SYS008,null,Status.FAILED);
		}
	}
	


	

	/**
	 * @Title: showApi
	 * @Description: 单个api信息显示
	 * @author: makangwei 
	 * @date:   2017年10月12日 下午1:04:19 
	 */
	@Override
	public Result<?> showApi(String apiId) {
		
		Result<JSONObject> result = new Result<JSONObject>();
		
		//NewApiEntity api = findOneById(apiId);
		NewApiEntity api = mysqlApiDao.findTotalOneById(apiId);
		if (null == api) {
			result.setErrorMessage("当前id不存在",ErrorCodeNo.SYS006);
			return result;
		}

		// 添加网关访问地址
		List<GatewayColonyEntity> colList = GatewayUtils.getAllGatewayColony();
		List<String> gatewayUrlList = new ArrayList<String>();
		List<String> wGatewayUrlList = new ArrayList<String>();
		for (GatewayColonyEntity gatewayColonyEntity : colList) {
			gatewayUrlList.add(gatewayColonyEntity.getColUrl()+api.getListenPath());
			wGatewayUrlList.add(gatewayColonyEntity.getwColUrl()+api.getListenPath());//外网访问地址
		}
		
//		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(api));
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(ApiTransform.transToApi(api)));
		jsonObject.put("gatewayUrls", gatewayUrlList);
		jsonObject.put("wGatewayUrls", wGatewayUrlList);
		result.setSuccessData(jsonObject);
		return result;
	}



	/**
	 * @Title: showApiList
	 * @Description: 提供者查看api列表
	 * @author: makangwei 
	 * @date:   2017年10月12日 下午2:10:36 
	 */
	@Override
	public Result<?> showApiList(QueryApiEntity entity) {
		
		Result<Page<ApiEntity>> result = new Result<Page<ApiEntity>>();

		int totalNum =  mysqlApiDao.findListSize(entity);
		List<NewApiEntity> apiList = mysqlApiDao.getPagedList(entity);
		
		Page<ApiEntity> page = new Page<ApiEntity>(entity.getCurrentPage(),totalNum,entity.getPageSize());
		page.setItems(ApiTransform.transToApis(apiList));
		
		result.setSuccessData(page);
		return result;
	}
	

	@Override
	public Result<?> showDocApiList(QueryApiEntity entity) {
		
		Result<Page<ApiEntity>> result = new Result<Page<ApiEntity>>();

		int totalNum =  mysqlApiDao.findListSize(entity);
		List<NewApiEntity> apiList = mysqlApiDao.getPagedList(entity);
		for (NewApiEntity api : apiList) {
			api.setOrgPath(null); //文档中心回源地址不可见
		}
		Page<ApiEntity> page = new Page<ApiEntity>(entity.getCurrentPage(),totalNum,entity.getPageSize());
		page.setItems(ApiTransform.transToApis(apiList));
		
		result.setSuccessData(page);
		return result;
	}

	@Override
	public Result<?> getOpenApplyBound() {
		// warn 返回绑定的apiId是versionId以便和网关统一
		List<OpenApplyBound> boundList = mysqlApiDao.getOpenApplyBound();
		log.info("获取openApplyBound大小:{}",boundList.size());
		return new Result<List<OpenApplyBound>>("", ErrorCodeNo.SYS000, boundList, Status.SUCCESS);
	}

	@Override
	public Result<?> getDiyApplyBound() {
		// warn 返回绑定的apiId是versionId以便和网关统一
		// warn 返回绑定的diyApplyId是diyApply的clientId和bi保持统一
		List<DiyApplyBound> boundList = mysqlApiDao.getDiyApplyBound();
		log.info("获取diyApplyBound大小:{}",boundList.size());
		return new Result<List<DiyApplyBound>>("", ErrorCodeNo.SYS000, boundList, Status.SUCCESS);
	}

	@Override
	public int getTotalAmount() {

		return mysqlApiDao.findTotalSuccessApi();
	}

	@Override
	public int getTotalOpenApply() {
		return mysqlApiDao.getTotalOpenApply(); //获取审核通多的api，并且api类型为开放的开放应用总量
	}


	/**
	 * @Title: checkApiChName
	 * @Description: 校验当前开放应用中是否存在这个中文名称
	 * @author: makangwei 
	 * @date:   2017年10月12日 下午2:49:55 
	 */
	@Override
	public Result<String> checkApiChName(String apiChName, String openApplyId) {
		
		Result<String> result = new Result<String>();
		if(StringUtils.isBlank(apiChName)){
			result.setErrorMessage("apiEnName不能为空",ErrorCodeNo.SYS005);
			return result;
		}
//		Map<String,Object> map =new HashMap<String,Object>();
//		map.put(DBFieldsConstants.APIS_OPENAPPLY_ID, openApplyId);
//		map.put(DBFieldsConstants.APIS_APICHNAME, apiChName);
//		ApiEntity entity = newApiDao.findOneByFields(map);
		int chNum = mysqlApiDao.checkApiChName(apiChName, openApplyId);
		
		if(chNum > 0){
			result.setErrorMessage("当前名称已经被占用",ErrorCodeNo.SYS010);
		}else{
			result.setSuccessMessage("当前名称可用");
		}
		return result;
	}

	/**
	 * @Title: checkVersion
	 * @Description: 查看当前api是否存在该版本
	 * @author: makangwei 
	 * @date:   2017年10月12日 下午2:50:36 
	 */
	@Override
	public Result<String> checkVersion(String versionId, String version) {
		Result<String> result = new Result<String>();
		
//		ApiEntity entity = newApiDao.findByVersion(versionId, version);
		int verNum = mysqlApiDao.checkVersion(versionId, version);

		if(verNum > 0){
			result.setErrorMessage("当前version已经存在",ErrorCodeNo.SYS010);
		}else{
			result.setSuccessMessage("当前version不存在，可以使用");
		}
		return result;
	}
	
	/**
	 * @Description: 将定制应用和api发生绑定，将绑定关系推送到网关 。
	 * @author: makangwei 
	 * @date:   2017年10月17日 下午5:55:34 
	 */
	public boolean boundDiyApplyWithApi(
			String policyId,
			String clientId,
			String secret,
			Integer rate,
			Integer per,
			Integer quotaMax,
			Integer quotaRenewRate,
			List<String> openApplyIds){
		
		//获取versionId集合以及用逗号隔开的长数组
//		List<ApiEntity> apiEntityList = newApiDao.findApiByApplyIdsAndCheckState(openApplyIds,AuditConstants.API_CHECK_STATE_SUCCESS);
		List<NewApiEntity> apiEntityList = mysqlApiDao.findApiByApplyIdsAndCheckState(openApplyIds,AuditConstants.API_CHECK_STATE_SUCCESS, DBFieldsConstants.API_TYPE_OPEN);
		log.info("根据开放应用Id查询的即将绑定的api数量"+apiEntityList.size());
		StringBuffer versionIdsBuf = new StringBuffer(); // versionId用逗号分隔的长字符串
		Set<String> versionIdList = new HashSet<String>(); //versionId的集合
		for (NewApiEntity apiEntity : apiEntityList) { //装参数
			if(StringUtils.isNotBlank(apiEntity.getVersionId())){
				if(!versionIdList.contains(apiEntity.getVersionId())){
					versionIdList.add(apiEntity.getVersionId());
					versionIdsBuf.append(","+apiEntity.getVersionId());
				}
			}
		}
		if(!(versionIdsBuf.length() > 0)){
			log.info("versionidBuf的长度为0,查询不到api");
			log.info("不推送网关，直接分配clientId和secret");
			// TODO 紧急
			return true;
		}
		
		versionIdsBuf.deleteCharAt(0); //versionId用逗号分隔的长字符串
		Map<String, List<String>> apiInfos = new HashMap<String,List<String>>();
		for (String versionId : versionIdList) {
			List<NewApiEntity> apiList = mysqlApiDao.findByVersionId(versionId);
			List<String> versionList = new ArrayList<String>();
			for (NewApiEntity apiEntity : apiList) {
				//只有审核通过的版本才能绑定关系
				if(apiEntity.getCheckState() == AuditConstants.API_CHECK_STATE_SUCCESS){
					versionList.add(apiEntity.getVersion());
				}
			}
			apiInfos.put(versionId, versionList);
		}
		//推送policy
		String policyResult =  gatewayApiService.pushPolicy(policyId, rate, per, quotaMax, quotaRenewRate, apiInfos);
		
		if(StringUtils.isBlank(policyResult)){ //推送policy失败
			log.error("____________>error happens when execute push policy to gateway");
			return false;
		}
		
		//推送clientId;
		String clientResult = gatewayApiService.pushClient(clientId, secret, versionIdsBuf, policyId);
		
		if(StringUtils.isBlank(clientResult)){
			log.error("____________>error happens when execute push client to gateway");
			return false;
		}
		
		return true;
	}

	@Override
	public Result<?> checkListenPath(String listenPath) {
		
		//List<ApiEntity> list = newApiDao.findByField(DBFieldsConstants.APIS_LISTEN_PATH, listenPath);
		int tempNum = mysqlApiDao.checkListenPath(listenPath);
		if(tempNum == 0){
			return new Result<String>("当前监听路径可用",ErrorCodeNo.SYS000,null,Status.SUCCESS);
		}else{
			return new Result<String>("当前监听路径不可用",ErrorCodeNo.SYS009,null,Status.FAILED);
		}
	}
	
	@Override
	public Result<?> getResourceType(String sourceConfig) {
		// TODO Auto-generated method stub
		String getProvidersUrl = propertiesUtil.getSourceConfigValue(sourceConfig,"getProviders");
		
		Result<String> result = new Result<>();
		
		try {

			StringBuffer sendGetRequest = HttpClientUtil.sendGetRequest(getProvidersUrl, "UTF-8");

			log.debug("getProviders return json:" + sendGetRequest);

			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(sendGetRequest.toString());

			if (null != jsonObject && jsonObject.has("status") && jsonObject.has("msg") && jsonObject.has("data")) {

				String status = jsonObject.get("status") == null ? "" : jsonObject.get("status").toString();

				switch (status) {
					case "200":
						result.setStatus(Status.SUCCESS);
						result.setSuccessData(jsonObject.get("data").toString());
						break;
					default:
						result.setStatus(Status.SYSTEMERROR);
						break;
				}

				result.setMessage(jsonObject.get("msg").toString());

			} else {
				log.error("获取资源池类型时,缺失返回值:" + jsonObject);

				result.setErrorMessage("获取资源池类型错误!");
			}

		} catch (Exception e) {
			log.error("send productMenuList error e:" + e.toString());
			result.setErrorMessage("获取资源池类型错误!");
		}
		
		return result;
	}

//	@Override
//	public CloudResult<String> migraApi() {
//		
//		List<ApiEntity> apiList = newApiDao.findAll();
//		mysqlApiDao.clearAll();
//		int i = 0;
//		for (ApiEntity apiEntity : apiList) {
//			List<SubArgEntity> headers = apiEntity.getHeaders(); //Header参数
//			saveHeaders(headers,apiEntity.getId());
//			List<SubArgEntity> args = apiEntity.getArgs(); //应用参数
//			saveArgs(args,apiEntity.getId());
//			List<RetEntity> results = apiEntity.getResult(); //返回参数
//			saveResults(results, apiEntity.getId());
//			RetExamEntity retExampleEntity = apiEntity.getRetExample(); //返回示例
//			saveRexample(retExampleEntity, apiEntity.getId());
//			List<ErrorCodeEntity> errorCodes =  apiEntity.getErrCodes(); //错误码
//			saveErrorCodes(errorCodes,apiEntity.getId());
//			i+=mysqlApiDao.save(apiEntity);
//		}
//		CloudResult<String> result = new CloudResult<String>();
//		result.setSuccessMessage("一共迁移了"+apiList.size()+"条数据，成功了"+i+"条");
//		return result;
//	}

	private String checkListenPathFormat(String listenPath) {
		if(!listenPath.startsWith("/")){
			listenPath = "/"+listenPath;
		}
		return listenPath;
	}
	
	
	private void saveErrorCodes(List<ErrorCodeEntity> errorCodes, String apiId) {
		for (ErrorCodeEntity errorCodeEntity : errorCodes) {
			ApiCodeEntity code = new ApiCodeEntity();
			code.setId(RandomUtil.random32UUID());
			code.setApiId(apiId);
			code.setCodeName(errorCodeEntity.getErrname());
			code.setCodeDesc(errorCodeEntity.getDesc());
			apiCodeDao.save(code);
		}
	}

	private void saveRexample(RetExamEntity retExampleEntity, String apiId) {
		ApiResultExampleEntity re = new ApiResultExampleEntity();
		re.setApiId(apiId);
		re.setId(RandomUtil.random32UUID());
		re.setRexName(retExampleEntity.getExName());
		re.setRexType(retExampleEntity.getExType());
		re.setRexValue(retExampleEntity.getExValue());
		re.setStateCode(retExampleEntity.getStateCode());
		apiRexampleDao.save(re);
		
	}

	private void saveResults(List<RetEntity> results, String apiId) {
		for (RetEntity retEntity : results) {
			ApiResultEntity result = new ApiResultEntity();
			result.setApiId(apiId);
			result.setId(RandomUtil.random32UUID());
			result.setRetName(retEntity.getRetName());
			result.setRetType(retEntity.getRetType());
			result.setExample(retEntity.getExample());
			result.setDesc(retEntity.getDesc());
			apiResultDao.save(result);
		}
		
	}

	private void saveArgs(List<SubArgEntity> args,String apiId) {
		for (SubArgEntity subArgEntity : args) {
			ApiArgEntity arg = new ApiArgEntity();
			arg.setApiId(apiId);
			arg.setId(RandomUtil.random32UUID());
			arg.setArgName(subArgEntity.getArgName());
			arg.setArgType(subArgEntity.getArgType());
			arg.setExample(subArgEntity.getExample());
			arg.setRequired(subArgEntity.isRequired());
			arg.setArgDesc(subArgEntity.getDesc());
			apiArgDao.save(arg);
		}
		
	}

	private void saveHeaders(List<SubArgEntity> headers,String apiId) {
		for (SubArgEntity subArgEntity : headers) {
			ApiHeaderEntity header = new ApiHeaderEntity();
			header.setApiId(apiId);
			header.setId(RandomUtil.random32UUID());
			header.setRequired(subArgEntity.isRequired());
			header.setHeaderName(subArgEntity.getArgName());
			header.setHeaderType(subArgEntity.getArgType());
			header.setExample(subArgEntity.getDesc());
			header.setHeaderDesc(subArgEntity.getDesc());
			apiHeaderDao.save(header);
		}
	}
	
	private boolean saveMysqlEntity(NewApiEntity apiEntity) {

		mysqlApiDao.save1(apiEntity);
		
		List<ApiHeaderEntity> headers = apiEntity.getHeaders();
		if(null != headers && headers.size() > 0){
			for (ApiHeaderEntity header : headers) {
				header.setApiId(apiEntity.getId());
				header.setId(RandomUtil.random32UUID());
				apiHeaderDao.save(header);
			}
		}
		
		List<ApiArgEntity> args = apiEntity.getArgs();
		if(null != args && args.size() > 0){
			for (ApiArgEntity arg : args) {
				arg.setApiId(apiEntity.getId());
				arg.setId(RandomUtil.random32UUID());
				apiArgDao.save(arg);
			}
		}

		List<ApiArgEntity> queryArgs = apiEntity.getQueryArgs();
		if(null != queryArgs && queryArgs.size() > 0){
			for (ApiArgEntity arg : queryArgs) {
				arg.setApiId(apiEntity.getId());
				arg.setId(RandomUtil.random32UUID());
				apiQueryArgDao.save(arg);
			}
		}
		
		List<ApiResultEntity> results = apiEntity.getResult();
		if(null != results && results.size() > 0){
			for (ApiResultEntity result : results) {
				result.setApiId(apiEntity.getId());
				result.setId(RandomUtil.random32UUID());
				apiResultDao.save(result);
			}
		}
		
		ApiResultExampleEntity rExample = apiEntity.getRetExample();
		if(null != rExample){
			rExample.setApiId(apiEntity.getId());
			rExample.setId(RandomUtil.random32UUID());
			apiRexampleDao.save(rExample);
		}
		
		List<ApiCodeEntity> codes = apiEntity.getErrCodes();
		if(null != codes && codes.size() > 0)
		for (ApiCodeEntity code : codes) {
			code.setApiId(apiEntity.getId());
			code.setId(RandomUtil.random32UUID());
			apiCodeDao.save(code);
		}

		return true;
	}
	
	
	@Deprecated
	@SuppressWarnings("unused")
	private NewApiEntity findOneById(String apiId) {
		
		NewApiEntity api = mysqlApiDao.findById(apiId);
		if(null  == api){
			return null;
		}
		
		List<ApiHeaderEntity> headers = apiHeaderDao.findByApiId(apiId);
		if(null != headers && headers.size() > 0 ){
			api.setHeaders(headers);
		}
		
		List<ApiArgEntity> args = apiArgDao.findByApiId(apiId);
		if(null != args && args.size() > 0){
			api.setArgs(args);
		}
		
		List<ApiResultEntity> results = apiResultDao.findByApiId(apiId);
		if(null != results && results.size() > 0){
			api.setResult(results);
		}
		
		ApiResultExampleEntity rExampleEntity = apiRexampleDao.findOneByApiId(apiId);
		if(null != rExampleEntity){
			api.setRetExample(apiRexampleDao.findOneByApiId(apiId));
		}
		
		
		List<ApiCodeEntity> codes = apiCodeDao.findByApiId(apiId);
		if(null != codes && codes.size() > 0){
			api.setErrCodes(apiCodeDao.findByApiId(apiId));
		}
		return api;
	}
	
	private boolean updateMysqlEntity(NewApiEntity apiEntity) {
		
		int num = mysqlApiDao.checkId(apiEntity.getId());
		if(num <= 0){
			return false;
		}
		mysqlApiDao.saveOrUpdateEntity(apiEntity);
		
		apiHeaderDao.deleteByApiId(apiEntity.getId());
		apiArgDao.deleteByApiId(apiEntity.getId());
		apiQueryArgDao.deleteByApiId(apiEntity.getId());
		apiResultDao.deleteByApiId(apiEntity.getId());
		apiRexampleDao.deleteByApiId(apiEntity.getId());
		apiCodeDao.deleteByApiId(apiEntity.getId());
		
		List<ApiHeaderEntity> headers = apiEntity.getHeaders();
		if(null != headers && headers.size() > 0){
			for (ApiHeaderEntity header : headers) {
				header.setId(RandomUtil.random32UUID());
				header.setApiId(apiEntity.getId());
				apiHeaderDao.save(header);
			}
		}
		
		List<ApiArgEntity> args = apiEntity.getArgs();
		if(null != args && args.size() > 0){
			for (ApiArgEntity arg : args) {
				arg.setId(RandomUtil.random32UUID());
				arg.setApiId(apiEntity.getId());
				apiArgDao.save(arg);
			}
		}
		
		List<ApiArgEntity> queryArgs = apiEntity.getQueryArgs();
		if(null != queryArgs && queryArgs.size() > 0){
			for (ApiArgEntity queryArg : queryArgs) {
				queryArg.setId(RandomUtil.random32UUID());
				queryArg.setApiId(apiEntity.getId());
				apiQueryArgDao.save(queryArg);
			}
		}
		
		List<ApiResultEntity> results = apiEntity.getResult();
		if(null != results && results.size() > 0){
			for (ApiResultEntity result : results) {
				result.setId(RandomUtil.random32UUID());
				result.setApiId(apiEntity.getId());
				apiResultDao.save(result);
			}
		}
		
		ApiResultExampleEntity rExample = apiEntity.getRetExample();
		if(null != rExample){
			rExample.setId(RandomUtil.random32UUID());
			rExample.setApiId(apiEntity.getId());
			apiRexampleDao.save(rExample);
		}
		
		List<ApiCodeEntity> codes = apiEntity.getErrCodes();
		if(null != codes && codes.size() > 0){
			for (ApiCodeEntity code : codes) {
				code.setId(RandomUtil.random32UUID());
				code.setApiId(apiEntity.getId());
				apiCodeDao.save(code);
			}
		}

		return true;
	}

	private boolean updateSuccessEntity(NewApiEntity apiEntity) {
		NewApiEntity entity = mysqlApiDao.findById(apiEntity.getId());
		if(null == entity || StringUtils.isBlank(entity.getId())){
			return false;
		}
		
		//校验不可修改参数
		if(AuditConstants.API_CHECK_STATE_SUCCESS != apiEntity.getCheckState()){
			log.info("修改审核通过的api：checkState != 2");
			return false;
		}
		if(StringUtils.isNotBlank(entity.getDefaultTargetUrl())) {
			if(!entity.getDefaultTargetUrl().equals(apiEntity.getDefaultTargetUrl())){
					log.info("修改审核通过的api：defaultTargetUrl与数据库不一致");
					return false;
			}
		}
		if(StringUtils.isNotBlank(entity.getOrgPath())) {
			if(!entity.getOrgPath().equals(apiEntity.getOrgPath())){
				log.info("修改审核通过的api：orgPath与数据库不一致");
				return false;
			}
		}
		if(!entity.getListenPath().equals(apiEntity.getListenPath())){
			log.info("修改审核通过的api：listenPath与数据库不一致");
			return false;
		}
		if(!entity.getHttpMethod().equals(apiEntity.getHttpMethod())){
			log.info("修改审核通过的api：httpMethod与数据库不一致");
			return false;
		}
		if(!entity.getVersionId().equals(apiEntity.getVersionId())){
			log.info("修改审核通过的api：versionId与数据库不一致");
			return false;
		}
		if(!entity.getVersion().equals(apiEntity.getVersion())){
			log.info("修改审核通过的api：version与数据库不一致");
			return false;
		}
		if(!entity.getResourceType().equals(apiEntity.getResourceType())){
			log.info("修改审核通过的api：resourceType与数据库不一致");
			return false;
		}
		if(!entity.getProtocol().equals(apiEntity.getProtocol())){
			log.info("修改审核通过的api：protocol与数据库不一致");
			return false;
		}
		
		mysqlApiDao.saveOrUpdateEntity(apiEntity);
		
		apiHeaderDao.deleteByApiId(apiEntity.getId());
		apiArgDao.deleteByApiId(apiEntity.getId());
		apiQueryArgDao.deleteByApiId(apiEntity.getId());
		apiResultDao.deleteByApiId(apiEntity.getId());
		apiRexampleDao.deleteByApiId(apiEntity.getId());
		apiCodeDao.deleteByApiId(apiEntity.getId());
		
		List<ApiHeaderEntity> headers = apiEntity.getHeaders();
		if(null != headers && headers.size() > 0){
			for (ApiHeaderEntity header : headers) {
				header.setId(RandomUtil.random32UUID());
				header.setApiId(apiEntity.getId());
				apiHeaderDao.save(header);
			}
		}
		
		List<ApiArgEntity> args = apiEntity.getArgs();
		if(null != args && args.size() > 0){
			for (ApiArgEntity arg : args) {
				arg.setId(RandomUtil.random32UUID());
				arg.setApiId(apiEntity.getId());
				apiArgDao.save(arg);
			}
		}
		
		List<ApiArgEntity> queryArgs = apiEntity.getQueryArgs();
		if(null != queryArgs && queryArgs.size() > 0){
			for (ApiArgEntity queryArg : queryArgs) {
				queryArg.setId(RandomUtil.random32UUID());
				queryArg.setApiId(apiEntity.getId());
				apiQueryArgDao.save(queryArg);
			}
		}
		
		List<ApiResultEntity> results = apiEntity.getResult();
		if(null != results && results.size() > 0){
			for (ApiResultEntity result : results) {
				result.setId(RandomUtil.random32UUID());
				result.setApiId(apiEntity.getId());
				apiResultDao.save(result);
			}
		}
		
		ApiResultExampleEntity rExample = apiEntity.getRetExample();
		if(null != rExample){
			rExample.setId(RandomUtil.random32UUID());
			rExample.setApiId(apiEntity.getId());
			apiRexampleDao.save(rExample);
		}
		
		List<ApiCodeEntity> codes = apiEntity.getErrCodes();
		if(null != codes && codes.size() > 0){
			for (ApiCodeEntity code : codes) {
				code.setId(RandomUtil.random32UUID());
				code.setApiId(apiEntity.getId());
				apiCodeDao.save(code);
			}
		}

		return true;
	}


//	@Override
//	public CloudResult<?> migraQueryArgs() {
//		//1、删除旧的query表中导入的数据
//		apiQueryArgDao.deleteByImport();
//		//2、查询param库
//		List<ApiArgEntity> args = apiArgDao.getAllGetParam();
//		
//		//3、转化后插入query库
//		for (ApiArgEntity a : args) {
//			a.setImported(true);
//			apiQueryArgDao.saveImport(a);
//			apiArgDao.updateImport(a.getId());
//		}
//		
//		apiArgDao.deleteImport();
//		return CloudResult.errorResult("一共迁移"+args.size()+"条数据", ErrorCodeNo.SYS000, null, Status.SUCCESS);
//	}

}
