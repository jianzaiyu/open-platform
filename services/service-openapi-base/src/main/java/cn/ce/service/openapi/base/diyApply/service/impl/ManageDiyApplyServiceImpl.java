package cn.ce.service.openapi.base.diyApply.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.HttpClientUtil;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.dao.IMysqlDiyApplyDao;
import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.QueryDiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.inparameter.RegisterBathAppInParameterEntity;
import cn.ce.service.openapi.base.diyApply.entity.interfaceMessageInfo.InterfaMessageInfoString;
import cn.ce.service.openapi.base.diyApply.service.IManageDiyApplyService;
import cn.ce.service.openapi.base.util.PropertiesUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description : 说明
 * @Author : makangwei
 * @Date : 2017年10月16日
 */
@Slf4j
@Service("manageDiyApplyService")
@Transactional(propagation=Propagation.REQUIRED)
public class ManageDiyApplyServiceImpl implements IManageDiyApplyService {
	@Autowired
	private PropertiesUtil propertiesUtil;
	//	@Resource
//	private IDiyApplyDao diyApplyDao;
	@Resource
	private IMysqlDiyApplyDao mysqlDiyApplyDao;

	@Override
	public Result<Page<DiyApplyEntity>> findPagedApps(QueryDiyApplyEntity queryApply) {
		// TODO Auto-generated method stub
		Result<Page<DiyApplyEntity>> result = new Result<>();
		
//		Page<DiyApplyEntity> page = diyApplyDao.findPageByParam(productName, userName, checkState, applyName,  currentPage, pageSize);
		int totalNum = mysqlDiyApplyDao.findListSize(queryApply);
		List<DiyApplyEntity> list= mysqlDiyApplyDao.getPagedList(queryApply);
		Page<DiyApplyEntity> page= new Page<DiyApplyEntity>(queryApply.getCurrentPage(),totalNum,queryApply.getPageSize(),list);
		
		result.setSuccessData(page);
		
		return result;
	}

	@Override
	public Result<String> batchUpdate(String sourceConfig,List<String> ids, Integer checkState, String checkMem) {
		// TODO Auto-generated method stub
		Result<String> result = new Result<String>();
		/* 审核失败返回 */
		if (checkState == AuditConstants.DIY_APPLY_CHECKED_FAILED) {
			int num = mysqlDiyApplyDao.bathUpdateCheckState(ids, checkState, checkMem);
			log.info("bachUpdate diyApply message " + num + " count");
			result.setSuccessMessage("审核成功:" + num + "条");
			return result;
		}else if(AuditConstants.DIY_APPLY_CHECKED_FAILED != checkState &&
				AuditConstants.DIY_APPLY_CHECKED_SUCCESS != checkState){
			result.setErrorMessage("审核状态不正确", ErrorCodeNo.SYS012);
			return result;
		}
		
		List<DiyApplyEntity> diyApply = mysqlDiyApplyDao.findByIds(ids);
		if(null == diyApply || diyApply.size() == 0){
			log.info("diyApply List is Null,ids:" + JSON.toJSONString(ids));
			result.setErrorMessage("应用不存在!",ErrorCodeNo.SYS015);
			return result;
		}
		
		int totalNum=diyApply.size();
		int successNum = 0;
		for (DiyApplyEntity diy : diyApply) {
			RegisterBathAppInParameterEntity[] queryVO = new RegisterBathAppInParameterEntity[1];
			RegisterBathAppInParameterEntity rapentity = new RegisterBathAppInParameterEntity();
			rapentity.setAppName(diy.getApplyName());
			rapentity.setAppUrl(diy.getDomainUrl());
			rapentity.setAppDesc(diy.getApplyDesc());
			rapentity.setAppCode(diy.getId()); //将定制应用id作为code。如果更新应用会同时更新中台中相应的应用
			rapentity.setAppType("2");
			rapentity.setOwner(diy.getEnterpriseName());
			queryVO[0] = rapentity;
			log.info("registerBathApp to interface satar");
			InterfaMessageInfoString interfaMessageInfoJasonObjectResult = this
					.registerBathApp(sourceConfig, diy.getProductInstanceId(), JSONArray.fromObject(queryVO).toString())
					.getData();
			log.info("registerBathApp to interface states" + interfaMessageInfoJasonObjectResult.getStatus() + "");
			
			/* 开发者在开放平台发布应用审核 */
			
			String appId = JSONObject.fromString(interfaMessageInfoJasonObjectResult.getData())
				.getString(diy.getId());
//			Iterator<String> keys = jsonObjecttest.keys();
//			Map<String, Object> map = new HashMap<String, Object>();
//			String key = null;
//			Object value = null;
//			while (keys.hasNext()) {
//				key = keys.next();
//				value = jsonObjecttest.get(key).toString();
//				map.put(key, value);
//
//			}
			/* 审核成功 */
			if (StringUtils.isNotBlank(appId) && 
					interfaMessageInfoJasonObjectResult.getStatus() == AuditConstants.INTERFACE_RETURNSATAS_SUCCESS) {
				//String message = String.valueOf(diyApplyDao.bathUpdateByidAndPush(ids, map, checkState, checkMem));
				//int totalNum = mysqlDiyApplyDao.bathUpdateCheckState(ids, checkState, checkMem);
				int num = mysqlDiyApplyDao.auditSuccess(diy.getId(), appId, checkState, checkMem);
				if(num > 0){
					successNum++;
				}
			}	
		}
		log.info("bachUpdate diyApply message " + successNum + " count");
		result.setSuccessMessage("提交审核"+totalNum+"条,审核成功:" + successNum + "条");
		return result;
	}

	@Override
	public Result<InterfaMessageInfoString> registerBathApp(String sourceConfig, String tenantId, String app) {
		
		Result<InterfaMessageInfoString> result = new Result<>();
		String url = propertiesUtil.getValue("registerBathApp");
		String tId$ = Pattern.quote("#{tId}");
		String appList$ = Pattern.quote("#{appList}");
		String replacedurl = url.replaceAll(tId$, tenantId).replaceAll(appList$, app);
		//String replacedurl = url.replaceAll(tId$, tenantId);
		
		/* get请求方法 */
		InterfaMessageInfoString messageInfo = new InterfaMessageInfoString();
		log.info("batch update diyApply push to zhongtai parameter:"+replacedurl);
		JSONObject jsonObject = (JSONObject) HttpClientUtil.getUrlReturnJsonObject(replacedurl);
		log.info("batch update diyApply push to zongtai results:"+jsonObject);
			//ApiCallUtils.putOrPostMethod(replacedurl, params, headers, method);
		messageInfo.setData(jsonObject.getString("data"));
		messageInfo.setMsg(jsonObject.getString("msg"));
		messageInfo.setStatus(Integer.valueOf(jsonObject.getString("status")));

		if (messageInfo.getStatus() == 200 || messageInfo.getStatus() == 110) {
			result.setSuccessData(messageInfo);
			return result;
		} else {
			log.error("registerBathApp data http getfaile return code :" + messageInfo.getMsg() + " ");
			result.setErrorMessage("请求失败");
			result.setErrorCode(ErrorCodeNo.SYS006);
			return result;
		}

	}
	
	@Override
	public Result<DiyApplyEntity> findById(String applyId) {
		Result<DiyApplyEntity> result = new Result<>();
		//DiyApplyEntity findById = diyApplyDao.findById(applyId);
		DiyApplyEntity findById = mysqlDiyApplyDao.findById(applyId);
		if (null == findById) {
			result.setErrorMessage("该应用不存在!");
			result.setErrorCode(ErrorCodeNo.SYS009);
		} else {
			result.setSuccessData(findById);
		}
		return result;
	}
	
}
