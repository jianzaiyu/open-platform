package cn.ce.service.openapi.base.diyApply.dao.impl;//package cn.ce.service.openapi.base.diyApply.dao.impl;
//
//import java.util.ArrayList;
//import java.util.Iterator;
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
//import cn.ce.service.openapi.base.common.DBFieldsConstants;
//import cn.ce.service.openapi.base.common.MongoFiledConstants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.BathUpdateOptions;
//import cn.ce.service.openapi.base.core.mongo.BaseMongoDaoImpl;
//import cn.ce.service.openapi.base.diyApply.dao.IDiyApplyDao;
//import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
//
///***
// * 
// * 
// * @ClassName: ApplyDaoImpl
// * @Description:应用数据层实现
// * @author: lida
// * @date: 2017年8月23日14:29:53
// * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
// *
// */
//@Repository("diyApplyDao")
//public class DiyApplyDaoImpl extends BaseMongoDaoImpl<DiyApplyEntity> implements IDiyApplyDao {
//
//	@Override
//	public void saveOrUpdate(DiyApplyEntity entity) {
//		// TODO Auto-generated method stub
//		if (StringUtils.isNotBlank(entity.getId())) {
//			super.update(entity);
//		} else {
//			super.save(entity);
//		}
//	}
//
//	@Override
//	public void delete(String id) {
//		super.removeById(id);
//	}
//
//	@Override
//	public Page<DiyApplyEntity> findPageByQuery(Query query, Page<DiyApplyEntity> page) {
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public List<DiyApplyEntity> findListByQuery(Query query) {
//		return super.find(query);
//	}
//
//	@Override
//	public DiyApplyEntity findById(String id) {
//		return super.findById(id);
//	}
//
//	public String bathUpdateByid(List<String> ids, int checkState, String checkMem) {
//		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
//		for (int i = 0; i < ids.size(); i++) {
//			list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//					Update.update("checkState", checkState), false, true));
//			list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//					Update.update("checkMem", checkMem), false, true));
//
//		}
//		return String.valueOf(super.bathUpdate(super.mongoTemplate, DiyApplyEntity.class, list));
//	}
//
//	public String bathUpdateByidSaveAppID(List<String> ids, int checkState, List<String> appid) {
//		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
//		for (int i = 0; i < ids.size(); i++) {
//			list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//					Update.update("checkState", checkState), false, true));
//		}
//		return String.valueOf(super.bathUpdate(super.mongoTemplate, DiyApplyEntity.class, list));
//	}
//
//	@Override
//	public String bathUpdateByidAndPush(List<String> ids, Map<String, Object> map, int checkState, String checkMem) {
//		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
//		for (int i = 0; i < ids.size(); i++) {
//			Iterator<String> iter = map.keySet().iterator();
//			while (iter.hasNext()) {
//				String keytemp = iter.next();
//				if (keytemp.equals(ids.get(i))) {
//					list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//							Update.update("appId", map.get(keytemp)), false, true));
//					list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//							Update.update("checkState", checkState), false, true));
//					list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//							Update.update("checkMem", checkMem), false, true));
//				}
//			}
//		}
//		return String.valueOf(super.bathUpdate(super.mongoTemplate, DiyApplyEntity.class, list));
//	}
//
//	@Override
//	public Page<DiyApplyEntity> findApplyList(String applyName, String productName, Integer checkState, String userId,
//			Page<DiyApplyEntity> page) {
//
//		Criteria c = new Criteria();
//		
//		c.and(DBFieldsConstants.APIS_USERID).is(userId);
//		if (StringUtils.isNotBlank(applyName)) {
//			c.and(DBFieldsConstants.DIY_APPLY_APPLYNAME).regex(applyName,"i");
//		}
//		if (StringUtils.isNotBlank(productName)) {
//			c.and(DBFieldsConstants.DIY_APPLY_PRODUCTNAME).regex(productName,"i");
//		}
//		if (checkState != null) {
//			c.and(DBFieldsConstants.DIY_APPLY_CHECKSTATE).is(checkState);
//		}
//		Query query = new Query(c).with(new Sort(Direction.DESC, DBFieldsConstants.DIY_APPLY_CREATEDATE));
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public List<DiyApplyEntity> findListByEntity(DiyApplyEntity entity) {
//		Criteria c = new Criteria();
//
//		if (StringUtils.isNotBlank(entity.getUser().getId())) {
//			c.and(MongoFiledConstants.BASIC_USERID).is(entity.getUser().getId());
//		}
//
//		if (StringUtils.isNotBlank(entity.getApplyName())) {
//			c.and(MongoFiledConstants.DIY_APPLY_APPLYNAME).is(entity.getApplyName());
//		}
//
//		// 修改时排除当前修改应用
//		if (StringUtils.isNotBlank(entity.getId())) {
//			c.and(MongoFiledConstants.BASIC_ID).ne(entity.getId());
//		}
//
//		Query query = new Query(c).with(new Sort(Direction.DESC, MongoFiledConstants.BASIC_CREATEDATE));
//
//		return super.find(query);
//		
//	}
//
//	@Override
//	public Page<DiyApplyEntity> findPageByParam(String productName, String userName, Integer checkState,
//			String applyName, int currentPage, int pageSize) {
//		Page<DiyApplyEntity> page = new Page<>(currentPage, 0, pageSize);
//
//		Criteria c = new Criteria();
//		if (StringUtils.isNotBlank(productName)) {
//			c.and("productName").regex(productName);
//		}
//		if (StringUtils.isNotBlank(userName)) {
//			c.and("userName").regex(userName);
//		}
//		if (null != checkState) {
//			c.and("checkState").is(checkState);
//		}
//		if (StringUtils.isNotBlank(applyName)) {
//			c.and("applyName").regex(applyName);
//		}
//		Query query = new Query(c).with(new Sort(Direction.DESC, MongoFiledConstants.BASIC_CREATEDATE));
//		
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public List<DiyApplyEntity> findListByIds(List<String> ids) {
//		
//		Query query = new Query(Criteria.where("id").in(ids));
//		
//		return 	super.find(query);
//	}
//}
