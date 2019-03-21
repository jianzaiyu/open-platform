package cn.ce.service.openapi.base.apis.dao.impl;//package cn.ce.service.openapi.base.apis.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//
//import com.mongodb.WriteResult;
//
//import cn.ce.service.openapi.base.apis.dao.IApiOauthDao;
//import cn.ce.service.openapi.base.apis.entity.ApiAuditEntity;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.AbstractBaseMongoDao;
//import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年8月18日
//*/
//@Service
//public class ApiOauthDaoImpl extends AbstractBaseMongoDao<ApiAuditEntity>  implements IApiOauthDao{
//
//	@Override
//	public void addAuditEntity(ApiAuditEntity auditEntity) {
//		super.add(auditEntity);
//	}
//
//	@Override
//	public void init() {
//        boolean ext = mongoTemplate.collectionExists(ApiAuditEntity.class);
//        if (!ext) {
//            System.out.println("=========>> Init Create Collection : API_AUDIT....");
//            mongoTemplate.createCollection(ApiAuditEntity.class);
//  
//        }
//	}
//
//	@Override
//	public ApiAuditEntity findById(String authId) {
//		return super.findById(authId, ApiAuditEntity.class);
//	}
//
//	public void UpdateAuditEntity(ApiAuditEntity auditEntity) {
//		
//		super.updateById(auditEntity.getId(), auditEntity);
//	}
//
//	@Override
//	public int updateClientIdAndSecret(String authId, String clientId, String secret) {
//		Query query = new Query(Criteria.where("id").is(authId));
//		Update update = new Update();
//		update.addToSet("client_id",clientId);
//		update.addToSet("secret",secret);
//		WriteResult wr = mongoTemplate.updateMulti(query, update, ApiAuditEntity.class);
//		return wr.getN();
//		
//	}
//
//	@Override
//	public Page<ApiAuditEntity> findAsPage(String userId, int currentPage, int pageSize) {
//		
//		Query query = new Query(Criteria.where("user_id").is(userId));
//		return super.findAsPage(query, currentPage, pageSize, ApiAuditEntity.class);
//	}
//
//	@Override
//	public Page<ApiAuditEntity> findAsPage1(String userId, int currentPage, int pageSize) {
//		
//		Query query = new Query(Criteria.where("supplier_id").is(userId)).with(new Sort(new Order(Direction.DESC,"apply_time")));
//		return super.findAsPage(query, currentPage, pageSize, ApiAuditEntity.class);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<DiyApplyEntity> findByApplyId(String applyId,String apiId) {
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("apply_id", applyId);
//		map.put("api_id", apiId);
//		return super.findByFields(map, ApiAuditEntity.class);
//	}
//
//	@Override
//	public List<ApiAuditEntity> getListByApiId(List<String> apiIds) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<ApiAuditEntity> getApiAuditEntity(List<String> authIds) {
//		
//		return super.findInAsList("id", authIds, "createtime", ApiAuditEntity.class);
//	}
//
//	@Override
//	public int deleteById(String auditId) {
//		
//		return super.delById(auditId, ApiAuditEntity.class);
//	}
//
//}
