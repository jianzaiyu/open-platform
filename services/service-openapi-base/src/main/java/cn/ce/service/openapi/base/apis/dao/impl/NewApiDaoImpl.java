package cn.ce.service.openapi.base.apis.dao.impl;//package cn.ce.service.openapi.base.apis.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Repository;
//
//import com.mongodb.WriteResult;
//
//import cn.ce.service.openapi.base.apis.dao.INewApiDao;
//import cn.ce.service.openapi.base.apis.entity.ApiEntity;
//import cn.ce.service.openapi.base.apis.entity.QueryApiEntity;
//import cn.ce.service.openapi.base.common.AuditConstants;
//import cn.ce.service.openapi.base.common.DBFieldsConstants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.mongo.BaseMongoDaoImpl;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年10月12日
//*/
//@Repository(value="newApiDao")
//public class NewApiDaoImpl extends BaseMongoDaoImpl<ApiEntity> implements INewApiDao{
//
//	@Override
//	public ApiEntity save(ApiEntity entity) {
//		if(entity.getId() != null){
//			WriteResult wr = super.update(entity);
//			if(wr.getN() > 0){
//				return entity;
//			}else{
//				return null;
//			}
//		}
//		return super.save(entity);
//	}
//
//	//修改旧的版本的newVersion字段为false
//	@Override 
//	public int updApiVersionByApiId(String apiId,boolean flag) {
//		Query query = new Query().addCriteria(Criteria.where(DBFieldsConstants.APIS_APIVERSION_VERSIONID).is(apiId));
//		Update update = Update.update(DBFieldsConstants.APIS_APIVERSION_NEWVERSION, flag);
//        WriteResult wr = super.update(query, update);
//        return wr.getN();
//	}
//
//	@Override
//	public ApiEntity findApiById(String apiId) {
//		
//		return super.findById(apiId);
//	}
//
//	@Override
//	public Page<ApiEntity> findSupplierApis(QueryApiEntity entity,int currentPage, int pageSize) {
//		
//		Criteria c = new Criteria();
//		
//		if(StringUtils.isNotBlank(entity.getOpenApplyId())){
//			c.and(DBFieldsConstants.APIS_OPENAPPLY_ID).is(entity.getOpenApplyId());
//		}
//		if(StringUtils.isNotBlank(entity.getUserId())){
//			c.and(DBFieldsConstants.APIS_USERID).is(entity.getUserId());
//		}
//		if(null != entity.getCheckState()){
//			c.and(DBFieldsConstants.APIS_CHECKSTATE).is(entity.getCheckState());
//		}
//		if(!StringUtils.isBlank(entity.getApiChName())){
//			c.and(DBFieldsConstants.APIS_APICHNAME).regex(entity.getApiChName(),"i");
//		}
//		if(entity.getApiType() != null && StringUtils.isNotBlank(entity.getApiType().toString())){
//			c.and(DBFieldsConstants.APIS_API_TYPE).is(entity.getApiType());
//		}
//		if(!StringUtils.isBlank(entity.getEnterpriseName())){
//			c.and(DBFieldsConstants.API_ENTERPRESE_NAME).is(entity.getEnterpriseName());
//		}
//		
//		if(AuditConstants.API_SOURCE_IMPORT.equals(entity.getApiSource())){
//			c.and(DBFieldsConstants.API_SOURCE).is(entity.getApiSource());
//		}else if(AuditConstants.API_SOURCE_TYPEIN.equals(entity.getApiSource())){ // DODO 20171214 将来将所有的api都加上source来源
//			c.and(DBFieldsConstants.API_SOURCE).ne(AuditConstants.API_SOURCE_IMPORT);
//		}else{
//			//查询所有来源
//		}
//		
//		Query query = new Query(c).with(new Sort(Direction.DESC, DBFieldsConstants.APIS_CREATE_TIME));
//		
//		Page<ApiEntity> page = new Page<ApiEntity>(currentPage,0,pageSize);
//		
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public Page<ApiEntity> findManagerList(QueryApiEntity entity,int currentPage, int pageSize) {
//		
//		Criteria c = new Criteria();
//		
//		if(StringUtils.isNotBlank(entity.getOpenApplyId())){
//			c.and(DBFieldsConstants.APIS_OPENAPPLY_ID).is(entity.getOpenApplyId());
//		}
//		if(StringUtils.isNotBlank(entity.getUserId())){
//			c.and(DBFieldsConstants.APIS_USERID).is(entity.getUserId());
//		}
//		if(!StringUtils.isBlank(entity.getApiChName())){
//			c.and(DBFieldsConstants.APIS_APICHNAME).regex(entity.getApiChName(),"i");
//		}
//		if(null != entity.getCheckState()){
//			c.and(DBFieldsConstants.APIS_CHECKSTATE).is(entity.getCheckState());
//		}
//		if(null != entity.getUserType()){
//			c.and(DBFieldsConstants.USER_USERTYPE).is(entity.getUserType());
//		}
//		if(entity.getApiType() != null && StringUtils.isNotBlank(entity.getApiType().toString())){
//			c.and(DBFieldsConstants.APIS_API_TYPE).is(entity.getApiType());
//		}
//		if(!StringUtils.isBlank(entity.getEnterpriseName())){
//			c.and(DBFieldsConstants.API_ENTERPRESE_NAME).is(entity.getEnterpriseName());
//		}
//		if(AuditConstants.API_SOURCE_IMPORT.equals(entity.getApiSource())){
//			c.and(DBFieldsConstants.API_SOURCE).is(entity.getApiSource());
//		}else if(AuditConstants.API_SOURCE_TYPEIN.equals(entity.getApiSource())){ // DODO 20171214 将来将所有的api都加上source来源
//			c.and(DBFieldsConstants.API_SOURCE).ne(AuditConstants.API_SOURCE_IMPORT);
//		}else{
//			//查询所有来源
//		}
//		Query query = new Query(c).with(new Sort(Direction.DESC, DBFieldsConstants.APIS_CREATE_TIME));
//		
//		Page<ApiEntity> page = new Page<ApiEntity>(currentPage,0,pageSize);
//		
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public Page<ApiEntity> findManagerListWithoutPage(QueryApiEntity entity) {
//		Criteria c = new Criteria();
//		
//		if(StringUtils.isNotBlank(entity.getOpenApplyId())){
//			c.and(DBFieldsConstants.APIS_OPENAPPLY_ID).is(entity.getOpenApplyId());
//		}
//		if(StringUtils.isNotBlank(entity.getUserId())){
//			c.and(DBFieldsConstants.APIS_USERID).is(entity.getUserId());
//		}
//		if(!StringUtils.isBlank(entity.getApiChName())){
//			c.and(DBFieldsConstants.APIS_APICHNAME).regex(entity.getApiChName(),"i");
//		}
//		if(null != entity.getCheckState()){
//			c.and(DBFieldsConstants.APIS_CHECKSTATE).is(entity.getCheckState());
//		}
//		if(null != entity.getUserType()){
//			c.and(DBFieldsConstants.USER_USERTYPE).is(entity.getUserType());
//		}
//		if(entity.getApiType() != null && StringUtils.isNotBlank(entity.getApiType().toString())){
//			c.and(DBFieldsConstants.APIS_API_TYPE).is(entity.getApiType());
//		}
//		if(!StringUtils.isBlank(entity.getEnterpriseName())){
//			c.and(DBFieldsConstants.API_ENTERPRESE_NAME).is(entity.getEnterpriseName());
//		}
//		if(AuditConstants.API_SOURCE_IMPORT.equals(entity.getApiSource())){
//			c.and(DBFieldsConstants.API_SOURCE).is(entity.getApiSource());
//		}else if(AuditConstants.API_SOURCE_TYPEIN.equals(entity.getApiSource())){ // DODO 20171214 将来将所有的api都加上source来源
//			c.and(DBFieldsConstants.API_SOURCE).ne(AuditConstants.API_SOURCE_IMPORT);
//		}else{
//			//查询所有来源
//		}
//		Query query = new Query(c).with(new Sort(Direction.DESC, DBFieldsConstants.APIS_CREATE_TIME));
//		
//		Page<ApiEntity> page = new Page<ApiEntity>(1,0,(int)super.count(query));
//		
//		return super.findPage(page, query);
//	}
//	
//	@Override
//	public ApiEntity findOneByFields(Map<String, Object> map) {
//		Criteria c = new Criteria();
//		for (String key : map.keySet()) {
//			c.and(key).is(map.get(key));
//		}
//		Query query = new Query(c);
//		return super.findOne(query);
//	}
//
//	@Override
//	public List<ApiEntity> findApiByIds(List<String> apiId) {
//		Query query = new Query().addCriteria(Criteria.where(DBFieldsConstants.APIS_ID).in(apiId));
//		return super.find(query);
//	}
//	
//	@Override
//	public List<ApiEntity> findApiByIds(List<String> apiIds, int checkState) {
//		Criteria c = new Criteria();
//		c.and(DBFieldsConstants.APIS_ID).in(apiIds);
//		c.and(DBFieldsConstants.APIS_CHECKSTATE).is(checkState);
//		Query query = new Query(c);
//		return super.find(query);
//	}
//
//	@Override
//	public List<ApiEntity> findApiByApplyIdsAndCheckState(List<String> appIds,Integer checkState) {
//		Criteria c = new Criteria();
//		c.and(DBFieldsConstants.APIS_OPENAPPLY_ID).in(appIds);
//		c.and(DBFieldsConstants.APIS_CHECKSTATE).is(checkState);
//		Query query = new Query(c);
//		return super.find(query);
//	}
//	
//	@Override
//	public List<ApiEntity> findApiByApplyIdsAndCheckState(List<String> openApplyIds, Integer checkState, String apiType) {
//		Criteria c = new Criteria();
//		c.and(DBFieldsConstants.APIS_OPENAPPLY_ID).in(openApplyIds);
//		c.and(DBFieldsConstants.APIS_CHECKSTATE).is(checkState);
//		c.and(DBFieldsConstants.APIS_API_TYPE).is(apiType);
//		Query query = new Query(c);
//		return super.find(query);
//	}
//
//	@Override
//	public List<ApiEntity> findByField(String key, String value) {
//		Query query = new Query().addCriteria(Criteria.where(key).is(value));
//		return super.find(query);
//	}
//
//	@Override
//	public Page<ApiEntity> findApiPageByIds(List<String> apiIds, Page<ApiEntity> page) {
//		Query query = new Query().addCriteria(Criteria.where(DBFieldsConstants.APIS_ID).in(apiIds));
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public ApiEntity findByVersion(String versionId, String version) {
//		Criteria c = new Criteria();
//		c.and(DBFieldsConstants.APIS_APIVERSION_VERSIONID).in(versionId);
//		c.and(DBFieldsConstants.APIS_APIVERSION_VERSION).is(version);
//		Query query = new Query(c);
//		return super.findOne(query);
//	}
//
//	@Override
//	public Page<ApiEntity> findApiPageByIdsAndNameLike(List<String> apiIds, String apiName, Page<ApiEntity> page) {
//		Criteria c = new Criteria();
//		if(StringUtils.isNotBlank(apiName)){
//			Criteria c1 = Criteria.where(DBFieldsConstants.APIS_APICHNAME).regex(apiName,"i")
//					.and(DBFieldsConstants.APIS_ID).in(apiIds)
//					.and(DBFieldsConstants.APIS_CHECKSTATE).is(AuditConstants.API_CHECK_STATE_SUCCESS);
//					//.andOperator(Criteria.where(DBFieldsConstants.APIS_ID).in(apiIds))
//					//.andOperator(Criteria.where(DBFieldsConstants.APIS_CHECKSTATE).is(AuditConstants.API_CHECK_STATE_SUCCESS));
//			Criteria c2 = Criteria.where(DBFieldsConstants.APIS_APIENNAME).regex(apiName,"i")
//					.and(DBFieldsConstants.APIS_ID).in(apiIds)
//					.and(DBFieldsConstants.APIS_CHECKSTATE).is(AuditConstants.API_CHECK_STATE_SUCCESS);
//					//.andOperator(Criteria.where(DBFieldsConstants.APIS_ID).in(apiIds))
//					//.andOperator(Criteria.where(DBFieldsConstants.APIS_CHECKSTATE).is(AuditConstants.API_CHECK_STATE_SUCCESS));
//			c.orOperator(c1,c2);
//			return super.findPage(page, new Query(c));
//		}else{
//			Query query = new Query().addCriteria(Criteria.where(DBFieldsConstants.APIS_ID).in(apiIds));
//			return super.findPage(page, query);
//		}
//
//	}
//
//	@Override
//	public List<ApiEntity> findByIdsOrAppIds(List<String> apiIds, List<String> appIds) {
//		Criteria c = new Criteria();
//		Criteria c1 = Criteria.where(DBFieldsConstants.APIS_ID).in(apiIds)
//		.andOperator(Criteria.where(DBFieldsConstants.APIS_CHECKSTATE).is(AuditConstants.API_CHECK_STATE_SUCCESS));
//		Criteria c2 = Criteria.where(DBFieldsConstants.APIS_OPENAPPLY_ID).in(appIds)
//				.andOperator(Criteria.where(DBFieldsConstants.APIS_CHECKSTATE).is(AuditConstants.API_CHECK_STATE_SUCCESS));
//		c.orOperator(c1,c2);
//		return super.find(new Query(c));
//	}
//
//	@Override
//	public void deleteApis(List<String> successApiIds) {
//		Query query = new Query().addCriteria(Criteria.where("id").in(successApiIds));
//		super.remove(query);
//	}
//
//	@Override
//	public List<ApiEntity> findApiByCheckState(int checkState) {
//		Query query = new Query().addCriteria(Criteria.where("checkState").is(checkState));
//		return super.find(query);
//	}
//
//
//}
