package cn.ce.service.openapi.base.guide.dao.impl;//package cn.ce.service.openapi.base.guide.dao.impl;
//
//import java.util.ArrayList;
//import java.util.List;
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
//import cn.ce.service.openapi.base.guide.dao.IGuideDao;
//import cn.ce.service.openapi.base.guide.entity.GuideEntity;
//
///**
// *
// * @Title: GuideDaoImpl.java
// * @Description
// * @version V1.0
// * @author yang.yu@300.cn
// * @date dat2017年10月12日 time下午6:51:38
// *
// **/
//@Repository(value = "guideDaoImpl")
//public class GuideDaoImpl extends BaseMongoDaoImpl<GuideEntity> implements IGuideDao {
//
//	@Override
//	public void saveOrUpdateGuide(GuideEntity g) {
//		// TODO Auto-generated method stub
//		if (StringUtils.isNotBlank(g.getId())) {
//			super.update(g);
//		} else {
//			super.save(g);
//		}
//	}
//
//	@Override
//	@Deprecated
//	public Page<GuideEntity> listByPage(Page<GuideEntity> page, Query query) {
//		return super.findPage(page, query);
//	}
//
//	@Override
//	@Deprecated
//	public List<GuideEntity> list(Query query) {
//		return super.find(query);
//	}
//
//	@Override
//	public List<GuideEntity> getListByNameAndApply(String guideName, String applyId) {
//
//		Query query = new Query(Criteria.where("guideName").is(guideName)
//				.and("applyId").is(guideName));
//		
//		return super.find(query);
//		
//	}
//
//	@Override
//	public String bachUpdateGuide(List<String> ids, Integer state, String checkMem) {
//		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
//		for (int i = 0; i < ids.size(); i++) {
//			list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//					Update.update("checkState", state), false, true));
//			if (StringUtils.isNotBlank(checkMem)) {
//				list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//						Update.update("checkMem", checkMem), false, true));
//			}
//
//		}
//		return String.valueOf(super.bathUpdate(super.mongoTemplate, GuideEntity.class, list));
//	}
//
//	@Override
//	public GuideEntity getById(String id) {
//		// TODO Auto-generated method stub
//		return super.findById(id);
//	}
//
//	@Override
//	public void deleteByid(String id) {
//		// TODO Auto-generated method stub
//		super.removeById(id);
//	}
//	
//
//	@Override
//	public Page<GuideEntity> listPage(String guideName, String creatUserName, String applyId, Integer checkState,
//			int currentPage, int pageSize) {
//		Page<GuideEntity> page = new Page<GuideEntity>(currentPage, 0, pageSize);
//		Criteria c = new Criteria();
//		
//		if(null != checkState){
//			c.and("checkState").is(checkState);
//		}
//		if (StringUtils.isNotBlank(applyId)) {
//			c.and("applyId").is(applyId);
//		}
//		if (StringUtils.isNotBlank(guideName)) {
//			c.and("guideName").regex(guideName);
//		}
//		if (StringUtils.isNotBlank(creatUserName)) {
//			c.and("creatUserName").regex(creatUserName);
//		}
//		Query query = new Query(c).with(new Sort(Direction.DESC, "creatTime"));
//		
//		return super.findPage(page, query);
//	}
//
//	@Override
//	public List<GuideEntity> findListByNameNeId(String guideName, String id) {
//		
//		Query query = new Query(
//				Criteria.where("guideName").is(guideName).and(MongoFiledConstants.BASIC_ID).ne(id));
//
//		return super.find(query);
//	}
//
//	@Override
//	public Page<GuideEntity> listPage2(GuideEntity entity, int currentPage, int pageSize) {
//		
//		Page<GuideEntity> page = new Page<GuideEntity>(currentPage, 0, pageSize);
//		Criteria c = new Criteria();
//
//		if (StringUtils.isNotBlank(entity.getGuideName())) {
//			c.and(DBFieldsConstants.GUIDE_NAME).regex(entity.getGuideName(),"i");
//		}
//		if (StringUtils.isNotBlank(entity.getCreatUserName())) {
//			c.and(DBFieldsConstants.GUIDE_CREATE_USERNAME).regex(entity.getCreatUserName(),"i");
//		}
//		if (StringUtils.isNotBlank(entity.getApplyId())) {
//			c.and(DBFieldsConstants.GUIDE_APPLYID).is(entity.getApplyId());
//		}
//		if(entity.getCheckState() != null){
//			c.and(DBFieldsConstants.GUIDE_CHECKSTATE).is(entity.getCheckState());
//		}
//
//		Query query = new Query(c).with(new Sort(Direction.DESC, MongoFiledConstants.BASIC_CREATEDATE));
//		
//		return super.findPage(page, query);
//	}
//
//}
