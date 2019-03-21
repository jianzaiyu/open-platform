package cn.ce.service.openapi.base.gateway.dao.impl;//package cn.ce.service.openapi.base.gateway.dao.impl;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import com.mongodb.WriteResult;
//
//import cn.ce.service.openapi.base.common.DBFieldsConstants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.AbstractBaseMongoDao;
//import cn.ce.service.openapi.base.gateway.dao.IGatewayNodeManageDao;
//import cn.ce.service.openapi.base.gateway.entity.GatewayNodeEntity;
//
///**
// *
// * @author makangwei
// * 2017-8-4
// */
//@SuppressWarnings("unchecked")
//@Repository
//public class GatewayNodeManageDao extends AbstractBaseMongoDao<GatewayNodeEntity> implements IGatewayNodeManageDao {
//
//	Logger LOGGER = LoggerFactory.getLogger(GatewayNodeManageDao.class);
//	
//	@Override
//	public void init() {
//		
//		boolean isHasCollection2 = mongoTemplate.collectionExists(GatewayNodeEntity.class);
//		
//		if(!isHasCollection2){
//			LOGGER.info("------------init create Collection GatewayNodeEntity:----------------");
//			mongoTemplate.createCollection(GatewayNodeEntity.class);
//		}
//	}
//	
//	public boolean addGatewayNode(GatewayNodeEntity nodeEntity) {
//		
//		try{
//			add(nodeEntity);
//			return true;
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	public Page<GatewayNodeEntity> getAllGatewayNode(Integer currentPage,
//			Integer pageSize, String colId) {
//		
//		Query query = new Query();
//		query.addCriteria(Criteria.where("colId").is(colId));
//		Page<GatewayNodeEntity> page = findAsPage(query,currentPage,pageSize,GatewayNodeEntity.class);
//		
//		if(page != null){
//			return page;
//		}
//		return null;
//	}
//
//	public boolean deleteGatewayNodeById(String nodeId) {
//		Query query =new Query();
//		query.addCriteria(Criteria.where("_id").is(nodeId));
//		WriteResult rt = mongoTemplate.remove(query, GatewayNodeEntity.class);
//		
//		LOGGER.info("-------数据是否已删除:"+rt.getN());
//		
//		if(rt.getN() > 0 ){
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public List<GatewayNodeEntity> checkNodeUrl(String nodeUrl, String nodeId) {
//		Criteria c = new Criteria();
//		c.and(DBFieldsConstants.GW_COL_URL).is(nodeUrl);
//		c.and(DBFieldsConstants.GW_COL_ID).ne(nodeId);
//		
//		return super.find(new Query(c), GatewayNodeEntity.class);
//	}
//
//	@Override
//	public List<GatewayNodeEntity> getAll(String colId) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("colId").is(colId));
//		return super.find(query, GatewayNodeEntity.class);
//	}
//}
