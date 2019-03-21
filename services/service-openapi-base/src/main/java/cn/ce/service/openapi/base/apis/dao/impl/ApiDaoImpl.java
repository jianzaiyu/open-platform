package cn.ce.service.openapi.base.apis.dao.impl;//package cn.ce.service.openapi.base.apis.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Repository;
//
//import com.mongodb.WriteResult;
//
//import cn.ce.service.openapi.base.apis.dao.IApiDAO;
//import cn.ce.service.openapi.base.apis.entity.ApiEntity;
//import cn.ce.service.openapi.base.common.Constants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.common.page.PageContext;
//import cn.ce.service.openapi.base.core.AbstractBaseMongoDao;
//
///**
// * 
// * @ClassName: IApiDAOIMpl
// * @Description: 接口数据库操作类型
// * @author dingjia@300.cn
// *
// */
//@SuppressWarnings("deprecation")
//@Repository(value ="apiDao")
//public class ApiDaoImpl extends AbstractBaseMongoDao<ApiEntity> implements IApiDAO {
//    /** 定义 */
//    private String appIdField = "appid";
//
//    /** 定义 */
//    private String categoryCodeField = "categorycode";
//
//    /** 定义 */
//    private String checkSta = "checkstate";
//
//    public ApiDaoImpl() {
//        PageContext.init(1, Constants.PAGE_SIZE);
//    }
//
//    @SuppressWarnings("unchecked")
//	@Override
//    public List<ApiEntity> getAPIs(String groupId) {
//    	
//        return super.findByField(appIdField, groupId, ApiEntity.class);
//    }
//
//    @Override
//    public List<ApiEntity> getAPIs(String groupId, int checkState) {
//        Map<String, Object> fields = new HashMap<String, Object>();
//        fields.put(appIdField, groupId);
//        fields.put(checkSta, checkState);
//        return super.findByFieldsAnd(fields, ApiEntity.class);
//    }
//
//    @Override
//    public List<ApiEntity> getCategoryAPIs(String categoryCode, int checkState) {
//        Map<String, Object> fields = new HashMap<String, Object>();
//        fields.put(categoryCodeField, categoryCode);
//        fields.put(checkSta, checkState);
//        return super.findByFieldsAnd(fields, ApiEntity.class);
//    }
//
//    @Override
//    public List<ApiEntity> getAPIs(int checkState) {
//        Map<String, Object> fields = new HashMap<String, Object>();
//        fields.put(checkSta, checkState);
//        return super.findByFieldsAnd(fields, ApiEntity.class);
//    }
//
//    @Override
//    public void addAPI(ApiEntity api) {
//        super.add(api);
//    }
//
//    @Override
//    public ApiEntity findOne(String apgrpId) {
//        return super.findOneByField(appIdField, apgrpId, ApiEntity.class);
//    }
//
//    @Override
//    public ApiEntity findOneByEnName(String enName) {
//        return super.findOneByField("apienname", enName, ApiEntity.class);
//    }
//
//    @Override
//    public int delApi(String id) {
//        return super.delById(id, ApiEntity.class);
//    }
//
//    @Override
//    public ApiEntity findOneById(String id) {
//        return super.findById(id, ApiEntity.class);
//    }
//
//    @Override
//    public int updateAPI(ApiEntity api) {
//        super.updateById(api.getId(), api);
//        return 1;
//    }
//
//    @Override
//    public Page<ApiEntity> getAPIsAsPage(String groupId) {
//        return super.findAsPage(appIdField, groupId, PageContext.getCuurentPage(), PageContext.getPageSize(),
//                        ApiEntity.class);
//
//    }
//
//
//	@PostConstruct
//    public void init() {
//        boolean ext = mongoTemplate.collectionExists(ApiEntity.class);
//        if (!ext) {
//            System.out.println("=========>> Init Create Collection : APIMG_APIS ....");
//            mongoTemplate.createCollection(ApiEntity.class);
//            /** 配置分片 */
//            /* super.shardCollection("OPC_API", "_id"); */
//
//            /** 创建唯一索引 ，失败，分片后无法创建唯一索引*/
////            IndexOperations io = mongoTemplate.indexOps(APIEntity.class);
////            Index index = new Index();
////            // 为name属性加上 索引
////            index.on("apienname", Order.ASCENDING);
////            // 唯一索引
////            index.unique();
////            io.ensureIndex(index);
//        }
//    }
//
//    @Override
//    public Page<ApiEntity> searchApis(String nameDesc, int currentPage, int pageSize) {
//        // 2 为降序排列
//        return super.findLikeOrLikeAsPage("apichname", nameDesc, "desc", nameDesc, 2, "createdate", currentPage,
//                        pageSize, ApiEntity.class);
//    }
//
//    @Override
//    public Page<ApiEntity> getGroupAPIsAsPage(String groId, int checkState, int state, int currentPage, int pageSize) {
//        Map<String, Object> fields = new HashMap<String, Object>();
//        fields.put("groupid", groId);
//        fields.put("checkstate", checkState);
//        fields.put("state", state);
//        return super.findByFieldsAsPage(fields, currentPage, pageSize, ApiEntity.class);
//    }
//
//	@Override
//	public ApiEntity findByApiCnName(String cnname) {
//		return super.findOneByField("apichname", cnname, ApiEntity.class);
//	}
//
//	@Override
//	public Page<ApiEntity> findApisByEntity(ApiEntity entity,int currentPage,int pageSize) {
//		
//		//构建查询对象
//		Criteria c = new Criteria();
//		
//		if(StringUtils.isNotBlank(entity.getOpenApplyId())){
//			c.and("appid").is(entity.getOpenApplyId());
//		}
//		
//		if(null != entity.getCheckState()){
//			c.and("checkstate").is(entity.getCheckState());
//		}
//		
//		if(StringUtils.isNotBlank(entity.getApiChName())){
//			c.and("apichname").regex(entity.getApiChName());
//		}
//		
//		if(StringUtils.isNotBlank(entity.getUserId())){
//			c.and("userid").is(entity.getUserId());
//		}
//		
//		//构建排序对象
//		Query query = new Query(c).with(new Sort(Direction.DESC, "createtime"));
//		
//		//分页
//		Page<ApiEntity> findAsPage = super.findAsPage(query, currentPage, pageSize, ApiEntity.class);
//		
//		return findAsPage;
//	}
//	
//	@Override
//	public List<ApiEntity> findApiListByQuery(Query query) {
//		return super.find(query, ApiEntity.class);
//	}
//
//	@Override
//	public List<ApiEntity> findByField(String key, String value) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where(key).is(value));
//		return super.find(query, ApiEntity.class);
//	}
//
//	@Override
//	public List<ApiEntity> findApiListByIds(List<String> ids) {
//		return super.findInAsList("id", ids, "createtime", ApiEntity.class);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public ApiEntity findOneByFields(Map<String, Object> map) {
//		
//		List<ApiEntity> list = super.findByFields(map, ApiEntity.class);
//		if(list.size()>0){
//			return list.get(0);
//		}
//		return null;
//	}
//
//	@Override
//	public boolean modifyApi(ApiEntity apientity) {
//		
//		try{
//			mongoTemplate.save(apientity);
//			return true;
//		}catch(Exception e){
//			return false;
//		}
//	}
//
//	@Override
//	public int updApiVersionByApiid(String apiid) {
//		Query query = new Query();
//        query.addCriteria(Criteria.where("apiversion.apiId").is(apiid));
//        WriteResult rt = mongoTemplate.updateMulti(query, Update.update("apiversion.newVersion", false), ApiEntity.class);
//        return rt.getN();
//	}
//
//	@Override
//	public boolean exitsVersion(String apiId, String version) {
//		Criteria c = new Criteria();
//		c.and("apiversion.apiId").is(apiId);
//		c.and("apiversion.version").is(version);
//		Query query = new Query(c);
//		return mongoTemplate.exists(query, ApiEntity.class);
//	}
//
//}
