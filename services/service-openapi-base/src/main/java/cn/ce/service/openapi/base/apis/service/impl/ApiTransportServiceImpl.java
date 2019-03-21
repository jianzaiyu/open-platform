package cn.ce.service.openapi.base.apis.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.ce.service.openapi.base.apis.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.entity.ApiArgEntity;
import cn.ce.service.openapi.base.apis.entity.ApiCodeEntity;
import cn.ce.service.openapi.base.apis.entity.ApiEntity;
import cn.ce.service.openapi.base.apis.entity.ApiExportParamEntity;
import cn.ce.service.openapi.base.apis.entity.ApiHeaderEntity;
import cn.ce.service.openapi.base.apis.entity.ApiResultEntity;
import cn.ce.service.openapi.base.apis.entity.ApiResultExampleEntity;
import cn.ce.service.openapi.base.apis.entity.DApiRecordEntity;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.entity.UApiRecordEntity;
import cn.ce.service.openapi.base.apis.entity.UApiRecordList;
import cn.ce.service.openapi.base.apis.service.IApiTransportService;
import cn.ce.service.openapi.base.apis.util.ApiTransform;
import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.common.gateway.ApiCallUtils;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.AppList;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.util.PropertiesUtil;
import cn.ce.service.openapi.base.util.RandomUtil;
import io.netty.handler.codec.http.HttpMethod;

/**
* @Description : api导入导出service
* @Author : makangwei
* @Date : 2017年12月14日
*/
@Slf4j
@Service(value="apiTransportService")
@Transactional(propagation=Propagation.REQUIRED)
public class ApiTransportServiceImpl implements IApiTransportService{

	@Autowired
	private PropertiesUtil propertiesUtil;
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
//	@Resource
//	private INewApiDao newApiDao;
	@Resource
	private IMysqlApiDao mysqlApiDao;
//	@Resource
//	private IDApiRecordDao dApiRecordDao;
	@Resource
	private IMysqlDApiRecordDao mysqlDapiRecord;
//	@Resource
//	private IUApiRecordDao uApiRecordDao;
	@Resource
	private IMysqlUApiRecordDao mysqlUApiRecord;
	
	
	@Override
	public Result<?> generalExportList(ApiExportParamEntity exportParam, User user) {

//		List<ApiEntity> apiList = null;
		List<String> exApiIds = null;
		if(exportParam.getAllFlag() != null && exportParam.getAllFlag() == 1){//导出全部
//			apiList = newApiDao.findApiByCheckState(AuditConstants.API_CHECK_STATE_SUCCESS);
			exApiIds = mysqlApiDao.findIdByCheckState(AuditConstants.API_CHECK_STATE_SUCCESS);
		}else{
//			apiList = newApiDao.findByIdsOrAppIds(exportParam.getApiIds(), exportParam.getAppIds());
			exApiIds = mysqlApiDao.findIdByIdsOrOpenApplys(exportParam.getApiIds(), exportParam.getAppIds()
					,AuditConstants.API_CHECK_STATE_SUCCESS);
		}

//		List<String> apiIds = new ArrayList<String>();
		if (null !=exApiIds && exApiIds.size() > 0) {
//			// 往数据库中保存记录
//			for (ApiEntity apiEntity : apiList) {
//				apiIds.add(apiEntity.getId());
//			}

			DApiRecordEntity recordEntity = new DApiRecordEntity(new Date(), exApiIds.size(), user.getUserName(),
					user.getId());
			
			recordEntity.setId(RandomUtil.random32UUID());
			mysqlDapiRecord.save(recordEntity);
			mysqlDapiRecord.saveBoundApis(recordEntity.getId(), exApiIds);

			return Result.errorResult("", ErrorCodeNo.SYS000, recordEntity.getId(), Status.SUCCESS);

		} else {
			return Result.errorResult("", ErrorCodeNo.SYS008, "", Status.FAILED);
		}
	}

	@Override
	public String exportApis(String recordId, HttpServletResponse response) {

		if (StringUtils.isBlank(recordId)) {
			return returnErrorJson("", ErrorCodeNo.SYS005, response);
		}

		List<NewApiEntity> successApiList = new ArrayList<NewApiEntity>();
		String url = propertiesUtil.getValue("findAppsByIds");
		DApiRecordEntity recordEntity = mysqlDapiRecord.findTotalOneById(recordId);

		if (recordEntity == null) {
			return returnErrorJson("", ErrorCodeNo.SYS006, response);
		}

		List<String> apiIds = recordEntity.getApiIds();
		List<NewApiEntity> apiList = mysqlApiDao.findTotalOnesByIdsAndCheckState(apiIds, AuditConstants.API_CHECK_STATE_SUCCESS);
		for (NewApiEntity apiEntity : apiList) {
			// 获取产品中心applist
			String tempUrl = null;
			try {
				tempUrl = url + "?appIds=" + URLEncoder.encode("[" + apiEntity.getOpenApplyId() + "]", "utf-8");
			} catch (UnsupportedEncodingException e1) {
			}
			String resultStr = ApiCallUtils.getOrDelMethod(tempUrl, null, HttpMethod.GET);

			AppList appList = null;
			try {
				com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseObject(resultStr)
						.getJSONArray("data");

				appList = jsonArray.getJSONObject(0).toJavaObject(AppList.class);
			} catch (Exception e) {// 如果从产品中心拿不到开放应用
				log.info("获取开放应用失败：" + apiEntity.getOpenApplyId());
				return returnErrorJson("开放应用(id:" + apiEntity.getOpenApplyId() + ")不存在", ErrorCodeNo.DOWNLOAD002,
						response);
			}

			// 封装：文档参数和api记录列表
			apiEntity.setAppCode(appList.getAppCode());
			apiEntity.setId(null);
			apiEntity.setUserId(null);
			apiEntity.setUserName(null);
			successApiList.add(apiEntity);
			
		}
		// TODO 20171211 mkw 这里的操作人是admin写死的。将来用户模块抽离出来的时候，这里再修改绑定用户id和用户名等

		return returnSuccessFile(ApiTransform.transToApis(successApiList), response);
	}
	
	@Override
	public Result<?> importApis(String upStr, User user) {

		List<NewApiEntity> apiEntityList = null;
		try{
			List<ApiEntity> oldList = com.alibaba.fastjson.JSONArray.parseArray(upStr, ApiEntity.class);// 将文档中的api集合导出
			apiEntityList = ApiTransform.transToNewApis(oldList);
		}catch(Exception e){
			log.info("文件导入时json数据解析错误。");
			return Result.errorResult("文件内容读取错误", ErrorCodeNo.UPLOAD001, null, Status.FAILED);
		}
		List<String> successApiIds = new ArrayList<String>(); // 用于发生错误时回滚数据
		List<UApiRecordList> records = new ArrayList<UApiRecordList>(); // 用户记录操作日志
		int successNum = 0;
		for (NewApiEntity entity : apiEntityList) {
			String importLog = null; //用于记录操作详情，记录到操作日志中
			boolean flag = true;
			AppList appList = validateApiAndApp(entity, user);// 校验并获取appList
			
			if (null==appList || null == appList.getAppCode() || null == appList.getAppId() ) {
				// roback
				importLog= "文档解析错误或所属应用错误";
				flag = false;
			}else{
				/**校验listenPath*/
//				List<NewApiEntity> checkApiList = mysqlApiDao.findByListenPath(entity.getListenPath());
//				if(null != checkApiList && checkApiList.size() > 0){
//					if(entity.getVersionId().equals(checkApiList.get(0).getVersionId())){
//						for (NewApiEntity apiEntity : checkApiList) {
//							String version = apiEntity.getVersion();
//							if(version.equals(entity.getVersion())){
//								flag=false;
//								importLog = "版本号重复";
//								break;

				
//							}
//						}
//					}else{
//						flag = false;
//						importLog="listenPath重复.";
//					}
//				}
				NewApiEntity newEntity = mysqlApiDao.findByListenPathAndVersion(entity.getListenPath(),entity.getVersion());
				if(null != newEntity){
					flag = false;
					importLog="listenPath以及版本号重复.";
				}else{
					//构建versionId
					List<NewApiEntity> checkApiList = mysqlApiDao.findByListenPath(entity.getListenPath());
					if(null != checkApiList && checkApiList.size() > 0){ //有旧的versionId用旧的
						entity.setVersionId(checkApiList.get(0).getVersionId());
					}else if(StringUtils.isBlank(entity.getVersionId())){ //如果没有旧的。并且传过来的文件中也没有versionId。创建新的
						entity.setVersionId(RandomUtil.random32UUID());
					}
				}

				//获取versionId
				List<NewApiEntity> checkApiList = mysqlApiDao.findByListenPath(entity.getListenPath());
				if (flag) {// 当前监听路径可以使用
					entity.setId(null);
					entity.setCheckState(1);
					entity.setCreateTime(new Date());
					entity.setAppCode(appList.getAppCode());
					entity.setOpenApplyId(appList.getAppId());
					entity.setUserName(user.getUserName());
					entity.setUserId(user.getId());
					entity.setApiSource(1);
					entity.setEnterpriseName(user.getEnterpriseName());
					entity.setId(RandomUtil.random32UUID());
					saveMysqlEntity(entity);
					successApiIds.add(entity.getId());
					successNum++;
				} 
			}
			
			records.add(new UApiRecordList(entity.getId(), 
					entity.getApiChName(), 
					entity.getListenPath(),
					entity.getApiType(), 
					entity.getOpenApplyId(), 
					entity.getAppCode(), 
					null == appList? null : appList.getAppName(),
					entity.getVersionId(), 
					entity.getVersion(), 
					flag ,
					importLog));
			
		}

		// 全部导出成功 后记录到日志中
		UApiRecordEntity recordEntity = new UApiRecordEntity(records, new Date(), apiEntityList.size(), successNum,
				user.getUserName(), user.getId());
		
//		uApiRecordDao.save(recordEntity);
		recordEntity.setId(RandomUtil.random32UUID());
		mysqlUApiRecord.save(recordEntity);
		mysqlUApiRecord.saveBoundApi(recordEntity.getId(),records);
		return Result.errorResult("导入成功", ErrorCodeNo.SYS000, recordEntity, Status.SUCCESS);
	}
	
	private AppList validateApiAndApp(NewApiEntity apiEntity, User user) {

		if (StringUtils.isBlank(apiEntity.getApiChName())) {// 接口名称不能为空
			log.info("接口名称不能为空");
			return null;
		}
		if (StringUtils.isBlank(apiEntity.getAppCode())) {
			log.info("接口所属开放应用分组不能为空");
			return null;
		}
		if (StringUtils.isBlank(apiEntity.getListenPath())) {
			log.info("监听路径不能为空");
			return null;
		}
		if (StringUtils.isBlank(apiEntity.getVersion())) {
			log.info("版本信息中的版本号不能为空");
			return null;
		}
		if (StringUtils.isBlank(apiEntity.getVersionId())) {
			log.info("版本信息中的版本id不能为空");
			return null;
		}
		if (apiEntity.getApiType() == null) {
			log.info("apiType不能为空");
			return null;
		}

		String url = propertiesUtil.getValue("findAppsByCodes");
		String tempUrl = null;
		try {
			tempUrl = url + "?codes=" + URLEncoder.encode("[\"" + apiEntity.getAppCode() + "\"]", "utf-8");
		} catch (UnsupportedEncodingException e1) {
		}
		String resultStr = ApiCallUtils.getOrDelMethod(tempUrl, null, HttpMethod.GET);

		AppList appList = null;
		try {
			com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseObject(resultStr)
					.getJSONArray("data");

			appList = jsonArray.getJSONObject(0).toJavaObject(AppList.class);
		} catch (Exception e) {// 如果从产品中心拿不到开放应用
			log.info("获取开放应用失败：" + apiEntity.getOpenApplyId());
			return null;
		}
		if (appList != null) {
			log.info("获取appList成功：" + appList);
			return appList;
		}
		log.info("获取appList失败");
		return null;
	}
	
	private String returnSuccessFile(List<?> successApiList, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String fileName = "copy_" + dateStr + ".json";
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		try {
			response.getOutputStream()
					.write(com.alibaba.fastjson.JSONObject.toJSONString(successApiList).getBytes("utf-8"));
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			log.info("返回下载文件时发生异常", e);
		}
		return null;
	}

	private String returnErrorJson(String errorMessage, ErrorCodeNo errorCode, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			response.getOutputStream().write(com.alibaba.fastjson.JSONObject
					.toJSONString(Result.errorResult(errorMessage, errorCode, null, Status.FAILED)).getBytes("utf-8"));
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			log.info("回显错误信息发生异常", e);
		}
		return null;
	}

	@Override
	public Result<?> getExportRecords(Integer checkCurrentPage, Integer checkPageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> getExportRecordById(String recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> getImportRecords(Integer checkCurrentPage, Integer checkPageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> getImportRecordById(String recordId) {
		// TODO Auto-generated method stub
		return null;
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
		for (ApiArgEntity qag: queryArgs) {
			qag.setApiId(apiEntity.getId());
			qag.setId(RandomUtil.random32UUID());
			apiQueryArgDao.save(qag);
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
		if(null != codes && codes.size() > 0){
			for (ApiCodeEntity code : codes) {
				code.setApiId(apiEntity.getId());
				code.setId(RandomUtil.random32UUID());
				apiCodeDao.save(code);
			}
		}

		return true;
	}
}
