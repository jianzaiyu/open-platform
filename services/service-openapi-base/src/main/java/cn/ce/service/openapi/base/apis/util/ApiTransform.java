package cn.ce.service.openapi.base.apis.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import cn.ce.service.openapi.base.apis.entity.ApiArgEntity;
import cn.ce.service.openapi.base.apis.entity.ApiCodeEntity;
import cn.ce.service.openapi.base.apis.entity.ApiEntity;
import cn.ce.service.openapi.base.apis.entity.ApiHeaderEntity;
import cn.ce.service.openapi.base.apis.entity.ApiResultEntity;
import cn.ce.service.openapi.base.apis.entity.ApiResultExampleEntity;
import cn.ce.service.openapi.base.apis.entity.ApiVersion;
import cn.ce.service.openapi.base.apis.entity.ErrorCodeEntity;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.entity.RetEntity;
import cn.ce.service.openapi.base.apis.entity.RetExamEntity;
import cn.ce.service.openapi.base.apis.entity.SubArgEntity;
import cn.ce.service.openapi.base.util.CoverBeanUtils;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年2月6日
*/
public class ApiTransform {

	public static NewApiEntity transToTotalNewApi(ApiEntity entity){
		if(null == entity){
			return null;
		}
		
		List<SubArgEntity> ohs = entity.getHeaders();
		List<SubArgEntity> oas = entity.getArgs();
		List<RetEntity> ors = entity.getResult();
		RetExamEntity ore = entity.getRetExample();
		List<ErrorCodeEntity> oecs = entity.getErrCodes();
		List<SubArgEntity> oqas = entity.getQueryArgs();
		
		entity.setHeaders(null);
		entity.setArgs(null);
		entity.setQueryArgs(null);
		entity.setResult(null);
		entity.setRetExample(null);
		entity.setErrCodes(null);
		NewApiEntity newEntity = new NewApiEntity();
	
		if(!CoverBeanUtils.copyProperties(newEntity, entity)){
			return null;
		}

		newEntity.setHeaders(parserApiHeaders(ohs));
		newEntity.setArgs(parserApiArgs(oas));
		newEntity.setResult(parserApiResult(ors));
		newEntity.setRetExample(parserApiRExample(ore));
		newEntity.setErrCodes(parserApiErrorCode(oecs));
		newEntity.setQueryArgs(parserApiQueryArgs(oqas));
		
		if(null != entity.getApiVersion()){
			newEntity.setVersion(entity.getApiVersion().getVersion());
			newEntity.setVersionId(entity.getApiVersion().getVersionId());
			newEntity.setVersionRemark(entity.getApiVersion().getVersionRemark());
			newEntity.setNewVersion(entity.getApiVersion().isNewVersion());
		}
		return newEntity;
	}


	public static ApiEntity transToApi(NewApiEntity entity) {
		if(null == entity){
			return null;
		}
		
		List<ApiHeaderEntity> hs = entity.getHeaders();
		List<ApiArgEntity> as = entity.getArgs();
		List<ApiArgEntity> qas = entity.getQueryArgs();
		List<ApiResultEntity> rs = entity.getResult();
		ApiResultExampleEntity re = entity.getRetExample();
		List<ApiCodeEntity> ecs = entity.getErrCodes();
		
		entity.setHeaders(null);
		entity.setArgs(null);
		entity.setResult(null);
		entity.setRetExample(null);
		entity.setErrCodes(null);
		entity.setQueryArgs(null);
		
		ApiEntity oa = new ApiEntity();

		if(!CoverBeanUtils.copyProperties(oa, entity)){
			return null;
		}
		
		oa.setHeaders(parserOApiHeaders(hs));
		oa.setArgs(parserOApiArgs(as));
		oa.setQueryArgs(parserOApiQueryArgs(qas));
		oa.setResult(parserOApiResult(rs));
		oa.setRetExample(parserOApiRExample(re));
		oa.setErrCodes(parserOApiErrorCode(ecs));
		
		ApiVersion av = new ApiVersion();
		av.setVersion(entity.getVersion());
		av.setVersionId(entity.getVersionId());
		av.setVersionRemark(entity.getVersionRemark());
		av.setNewVersion(entity.isNewVersion());
		oa.setApiVersion(av);
		
		return oa;
	}

	public static List<NewApiEntity> transToNewApis(List<ApiEntity> apiList){
		List<NewApiEntity> newList = new ArrayList<NewApiEntity>();
		for (ApiEntity oldEntity : apiList) {
			NewApiEntity newEntity = transToTotalNewApi(oldEntity);
			if(null != newEntity){
				newList.add(newEntity);
			}
		}
		return newList;
	}
	
	public static List<ApiEntity> transToApis(List<NewApiEntity> apiList) {
		
		List<ApiEntity> oList = new ArrayList<ApiEntity>();
		for (NewApiEntity newEntity : apiList) {
			ApiEntity oEntity = transToApi(newEntity);
			if(null != oEntity){
				oList.add(oEntity);
			}
		}
		return oList;
	}
	
	private static List<ErrorCodeEntity> parserOApiErrorCode(List<ApiCodeEntity> ecs) {
		if(null == ecs){
			return null;
		}
		
		List<ErrorCodeEntity> oecs = new ArrayList<ErrorCodeEntity>();
		for (ApiCodeEntity ec : ecs) {
			ErrorCodeEntity oec = new ErrorCodeEntity();
			oec.setErrname(ec.getCodeName());
			oec.setDesc(ec.getCodeDesc());
			oecs.add(oec);
		}
		return oecs;
	}

	private static RetExamEntity parserOApiRExample(ApiResultExampleEntity re) {
		if(null == re){
			return null;
		}
		
		RetExamEntity ore = new RetExamEntity();
		ore.setExName(re.getRexName());
		ore.setExType(re.getRexType());
		ore.setExValue(re.getRexValue());
		ore.setStateCode(re.getStateCode());
		return ore;
	}

	private static List<RetEntity> parserOApiResult(List<ApiResultEntity> rs) {
		if(null == rs || rs.isEmpty()){
			return null;
		}
		
		List<RetEntity> ors = new ArrayList<RetEntity>();
		for (ApiResultEntity r : rs) {
			RetEntity or = new RetEntity();
			or.setRetName(r.getRetName());
			or.setRetType(r.getRetType());
			or.setExample(r.getExample());
			or.setDesc(r.getDesc());
			ors.add(or);
		}
		return ors;
	}

	private static List<SubArgEntity> parserOApiArgs(List<ApiArgEntity> as) {
		if(null == as){
			return null;
		}
		
		List<SubArgEntity> oas = new ArrayList<SubArgEntity>();
		for (ApiArgEntity a : as) {
			SubArgEntity oa = new SubArgEntity();
			oa.setArgName(a.getArgName());
			oa.setArgType(a.getArgType());
			oa.setExample(a.getExample());
			oa.setRequired(a.isRequired());
			oa.setDesc(a.getArgDesc());
			oas.add(oa);
		}
		return oas;
	}
	
	private static List<SubArgEntity> parserOApiQueryArgs(List<ApiArgEntity> qas) {
		if(null == qas){
			return null;
		}
		
		List<SubArgEntity> oas = new ArrayList<SubArgEntity>();
		for (ApiArgEntity a : qas) {
			SubArgEntity qoa = new SubArgEntity();
			qoa.setArgName(a.getArgName());
			qoa.setArgType(a.getArgType());
			qoa.setExample(a.getExample());
			qoa.setRequired(a.isRequired());
			qoa.setDesc(a.getArgDesc());
			oas.add(qoa);
		}
		return oas;
	}

	private static List<SubArgEntity> parserOApiHeaders(List<ApiHeaderEntity> hs) {
		if(null == hs || hs.isEmpty()){
			return null;
		}
		
		List<SubArgEntity> ohs = new ArrayList<SubArgEntity>();
		for (ApiHeaderEntity h : hs) {
			SubArgEntity oh = new SubArgEntity();
			oh.setArgName(h.getHeaderName());
			oh.setArgType(h.getHeaderType());
			oh.setExample(h.getExample());
			oh.setRequired(h.isRequired());
			oh.setDesc(h.getHeaderDesc());
			ohs.add(oh);
		}
		
		return ohs;
	}

	private static List<ApiCodeEntity> parserApiErrorCode(List<ErrorCodeEntity> errCodes) {
		if(null == errCodes || errCodes.size() < 1){
			return null;
		}
		List<ApiCodeEntity> newErrorCodes = new ArrayList<ApiCodeEntity>();
		for (ErrorCodeEntity errorCodeEntity : errCodes) {
			ApiCodeEntity newCode = new ApiCodeEntity();
			newCode.setCodeName(errorCodeEntity.getErrname());
			newCode.setCodeDesc(errorCodeEntity.getDesc());
			newErrorCodes.add(newCode);
		}
		return newErrorCodes;
	}

	private static ApiResultExampleEntity parserApiRExample(RetExamEntity ore) {
		if(null == ore){
			return null;
		}
		ApiResultExampleEntity re= new ApiResultExampleEntity();
		re.setRexName(ore.getExName());
		re.setRexType(ore.getExType());
		re.setRexValue(ore.getExValue());
		re.setStateCode(ore.getStateCode());
		return re;
	}

	private static List<ApiResultEntity> parserApiResult(List<RetEntity> ors) {
		if(null == ors || ors.size() < 1){
			return null;
		}
		List<ApiResultEntity> rs = new ArrayList<ApiResultEntity>();
		for (RetEntity or : ors) {
			ApiResultEntity r = new ApiResultEntity();
			r.setRetName(or.getRetName());
			r.setRetType(or.getRetType());
			r.setExample(or.getExample());
			r.setDesc(or.getDesc());
			rs.add(r);
		}
		return rs;
	}

	private static List<ApiArgEntity> parserApiArgs(List<SubArgEntity> oas) {
		if(null == oas || oas.size() < 1){
			return null;
		}
		
		List<ApiArgEntity> as = new ArrayList<ApiArgEntity>();
		for (SubArgEntity oa : oas) {
			ApiArgEntity a = new ApiArgEntity();
			a.setArgName(oa.getArgName());
			a.setArgType(oa.getArgType());
			a.setExample(oa.getExample());
			a.setRequired(oa.isRequired());
			a.setArgDesc(oa.getDesc());
			as.add(a);
		}
		return as;
	}
	
	private static List<ApiArgEntity> parserApiQueryArgs(List<SubArgEntity> oqas) {
		if(null == oqas || oqas.size() < 1){
			return null;
		}
		
		List<ApiArgEntity> as = new ArrayList<ApiArgEntity>();
		for (SubArgEntity oa : oqas) {
			ApiArgEntity qa = new ApiArgEntity();
			qa.setArgName(oa.getArgName());
			qa.setArgType(oa.getArgType());
			qa.setExample(oa.getExample());
			qa.setRequired(oa.isRequired());
			qa.setArgDesc(oa.getDesc());
			as.add(qa);
		}
		return as;
	}

	private static List<ApiHeaderEntity> parserApiHeaders(List<SubArgEntity> ohs) {
		if(null == ohs || ohs.isEmpty()){
			return null;
		}
		
		List<ApiHeaderEntity> hs = new ArrayList<ApiHeaderEntity>();
		for (SubArgEntity oh : ohs) {
			ApiHeaderEntity h = new ApiHeaderEntity();
			h.setHeaderName(oh.getArgName());
			h.setHeaderType(oh.getArgType());
			h.setExample(oh.getExample());
			h.setRequired(oh.isRequired());
			h.setHeaderDesc(oh.getDesc());
			hs.add(h);
		}
		return hs;
	}
	
}
