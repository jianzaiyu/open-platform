package cn.ce.service.openapi.base.apisecret.dao.impl;//package cn.ce.service.openapi.base.apisecret.dao.impl;
//
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import cn.ce.service.openapi.base.apisecret.dao.IApiSecretKeyDao;
//import cn.ce.service.openapi.base.apisecret.entity.ApiSecretKey;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.AbstractBaseMongoDao;
//
//@Repository(value ="apiSecretDao")
//public class ApiSecretKeyDaoImpl extends AbstractBaseMongoDao<ApiSecretKey> implements IApiSecretKeyDao {
//	
//	@Autowired
//	private MongoTemplate mongoTemplate;
//
//	@Override
//	public void addApiSecretKey(ApiSecretKey api) {
//		// TODO Auto-generated method stub
//		super.add(api);
//	}
//
//	@Override
//	public ApiSecretKey findOneByKey(String secretKey) {
//		return super.findById(secretKey, ApiSecretKey.class);
//	}
//
//	
//	@Override
//	public List<ApiSecretKey> findSecretKeyByApiIds(List<String> apiIds) {
//		return super.findInAsList("apiId",apiIds,"createDate",ApiSecretKey.class);
//	}
//
//	@Override
//	public int delApi(String id) {
//		Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));
//        List<ApiSecretKey> findAllAndRemove = mongoTemplate.findAllAndRemove(query, ApiSecretKey.class);
//		return findAllAndRemove.isEmpty() ? 0 : findAllAndRemove.size();
//	}
//
//	@Override
//	public void updateApiSecretKey(ApiSecretKey secretKey) {
//		super.updateById(secretKey.getId(), secretKey);
//	}
//
//	@Override
//	public void init() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public ApiSecretKey findOneByField(String key, String value) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where(key).is(value));
//		ApiSecretKey secretEntity = mongoTemplate.findOne(query, ApiSecretKey.class);
//		
//		return secretEntity;
//	}
//	public List<ApiSecretKey> findSecretKeyByEntity(ApiSecretKey entity) {
//		//分页
//		List<ApiSecretKey> findList = super.find(generalSecretQueryBean(entity),ApiSecretKey.class);
//		
//		return findList;
//	}
//
//	@Override
//	public Page<ApiSecretKey> findSecretKeyPageByEntity(ApiSecretKey entity, int currentPage, int pageSize) {
//		//分页
//		Page<ApiSecretKey> findAsPage = super.findAsPage(generalSecretQueryBean(entity), currentPage, pageSize, ApiSecretKey.class);
//		
//		return findAsPage;
//	}
//	
//	private Query generalSecretQueryBean(ApiSecretKey entity){
//		//构建查询对象
//		Criteria c = new Criteria();
//		
//		if(StringUtils.isNotBlank(entity.getApiId())){
//			c.and("apiId").is(entity.getApiId());
//		}
//		
//		if(null != entity.getUserId()){
//			c.and("userId").is(entity.getUserId());
//		}
//		
//		
//		//构建排序对象
//		Query query = new Query(c).with(new Sort(Direction.DESC, "appid"));
//		
//		return query;
//	}
//
//}
