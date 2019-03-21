package cn.ce.service.openapi.base.openApply.dao.impl;//package cn.ce.service.openapi.base.openApply.dao.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.bson.types.ObjectId;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Repository;
//
//import cn.ce.service.openapi.base.common.MongoFiledConstants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.BathUpdateOptions;
//import cn.ce.service.openapi.base.core.bean.MongoDBWhereEntity;
//import cn.ce.service.openapi.base.core.mongo.BaseMongoDaoImpl;
//import cn.ce.service.openapi.base.openApply.dao.INewOpenApplyDao;
//import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年10月11日
//*/
//
//@Repository(value = "newOpenApplyDao")
//public class NewOpenApplyDaoImpl extends  BaseMongoDaoImpl<OpenApplyEntity> implements INewOpenApplyDao{
//
//	@Override
//	public OpenApplyEntity save(OpenApplyEntity openApplyEntity) {
//		if(StringUtils.isNotBlank(openApplyEntity.getId())){
//			super.update(openApplyEntity);
//		}else {
//			super.save(openApplyEntity);
//		}
//		return openApplyEntity;
//	}
//
//	@Override
//	public OpenApplyEntity findOpenApplyById(String id) {
//		return super.findById(id);
//	}
//
//	@Override
//	public List<OpenApplyEntity> findOpenApplyByNameOrKey(String applyName, String applyKey) {
//		
//		Criteria c = new Criteria();
//		Criteria c1 = Criteria.where(MongoFiledConstants.OPEN_APPLY_APPLYNAME).is(applyName);
//		Criteria c2 = Criteria.where(MongoFiledConstants.OPEN_APPLY_APPLYKEY).is(applyKey);
//		c.orOperator(c1,c2);
//		Query query = new Query(c);
//		return super.find(query);
//	}
//
//	@Override
//	public List<OpenApplyEntity> findOpenApplyByEntity(Map<String,MongoDBWhereEntity> whereEntity) {
//		Query query = new Query(super.getCriteriaByWhereEntity(whereEntity));
//		return super.find(query);
//	}
//	
//	@Override
//	public List<OpenApplyEntity> findOpenApplyByNeqId(OpenApplyEntity apply) {
//		Criteria c = new Criteria();
//		Criteria c1 = Criteria.where(MongoFiledConstants.OPEN_APPLY_APPLYNAME).is(apply.getApplyName());
//		c1.andOperator(Criteria.where(MongoFiledConstants.OPEN_APPLY_ID).ne(apply.getId()));
//							   
//		Criteria c2 = Criteria.where(MongoFiledConstants.OPEN_APPLY_APPLYKEY).is(apply.getApplyName());
//			c2.andOperator(Criteria.where(MongoFiledConstants.OPEN_APPLY_ID).ne(apply.getId()));
//			
//		c.orOperator(c1,c2);
//		Query query = new Query(c);
//		return super.find(query);
//	}
//	
//	@Override
//	public Page<OpenApplyEntity> findOpenApplyByEntity(Map<String, MongoDBWhereEntity> whereEntity, Page<OpenApplyEntity> page) {
//		Query query = new Query(super.getCriteriaByWhereEntity(whereEntity)).with(new Sort(Direction.DESC,MongoFiledConstants.BASIC_CREATEDATE));
//		return super.findPage(page, query);
//	}
//	
//	@Override
//	public int batchSaveApply(List<String> ids,Integer checkState) {
//		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
//		for (int i = 0; i < ids.size(); i++) {
//			list.add(new BathUpdateOptions(Query.query(Criteria.where(MongoFiledConstants.OPEN_APPLY_ID).is(new ObjectId(ids.get(i)))),
//					Update.update(MongoFiledConstants.OPEN_APPLY_CHECKSTATE, checkState), false, true));
//		}
//		return super.bathUpdate(super.mongoTemplate, OpenApplyEntity.class, list);
//	}
//
//}
