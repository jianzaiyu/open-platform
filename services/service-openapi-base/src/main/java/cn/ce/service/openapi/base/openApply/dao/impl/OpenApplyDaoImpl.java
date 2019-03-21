package cn.ce.service.openapi.base.openApply.dao.impl;//package cn.ce.service.openapi.base.openApply.dao.impl;
//
//import java.util.ArrayList;
//import java.util.Collection;
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
//import cn.ce.service.openapi.base.common.Constants;
//import cn.ce.service.openapi.base.common.DBFieldsConstants;
//import cn.ce.service.openapi.base.common.MongoFiledConstants;
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.common.page.PageContext;
//import cn.ce.service.openapi.base.core.AbstractBaseMongoDao;
//import cn.ce.service.openapi.base.core.BathUpdateOptions;
//import cn.ce.service.openapi.base.core.bean.MongoDBWhereEntity;
//import cn.ce.service.openapi.base.openApply.dao.IOpenApplyDao;
//import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
//import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;
//
///**
// * 
// * @ClassName: AppDAOImpl
// * @Description: 开发者创建的应用操作类型
// * @author dingjia@300.cn
// *
// */
//@Repository(value = "openApplyDao")
//public class OpenApplyDaoImpl extends AbstractBaseMongoDao<OpenApplyEntity> implements IOpenApplyDao {
//	/** 定义 */
//	private String clientName = "client_name";
//
//	/** 定义 */
//	private String createTime = "create_time";
//
//	@Override
//	public void addApp(OpenApplyEntity app) {
//		this.add(app);
//	}
//
//	@Override
//	public OpenApplyEntity findById(String id) {
//		return super.findById(id, OpenApplyEntity.class);
//	}
//
//	@Override
//	public int modifyById(String id, OpenApplyEntity app) {
//		super.updateById(id, app);
//		return 1;
//	}
//
//	@Override
//	public List<OpenApplyEntity> getAll() {
//		return super.getAll(OpenApplyEntity.class);
//	}
//
//	@Override
//	public int delById(String id) {
//		return super.delById(id, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> getAppList(String userid) {
//		return super.findAsPage("userid", userid, PageContext.getCuurentPage(), PageContext.getPageSize(),
//				OpenApplyEntity.class);
//	}
//
//	@Override
//	public OpenApplyEntity findByAppKey(String appKey) {
//		return super.findOneByField("client_id", appKey, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> getAsPage(String appName, int currentPage, int pageSize) {
//		return super.findLikeAsPage(clientName, appName, Constants.SORT_TYPE_DESC, createTime, currentPage, pageSize,
//				OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> getAsPage(String appName, Integer appCheckstate, int currentPage, int pageSize) {
//		return super.findLikeAndIsAsPage(clientName, appName, "checkState", appCheckstate, Constants.SORT_TYPE_DESC,
//				createTime, currentPage, pageSize, OpenApplyEntity.class);
//	}
//
//	@Override
//	public List<?> getAppWithGroups(String appName, int groupCheckstate) {
//		return super.findArrayElemAsPage("groups", "approvedstate", groupCheckstate, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findInAsPage(String appname, Collection<?> ids, int currentPage, int pageSize) {
//		return super.findLikeAndInAsPage(clientName, appname, "_id", ids, Constants.SORT_TYPE_DESC, createTime,
//				currentPage, pageSize, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findNotInAsPage(String appname, Collection<?> ids, int currentPage, int pageSize) {
//		return super.findLikeAndNotInAsPage(clientName, appname, "_id", ids, Constants.SORT_TYPE_DESC, createTime,
//				currentPage, pageSize, OpenApplyEntity.class);
//	}
//
//	@Override
//	public void init() {
//		// TODO Auto-generated method stub
//
//	}
//
//	public OpenApplyEntity findAppByAppName(String appName) {
//		return super.findOneByField("appname", appName, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findAppListPageByDBWhere(int currentPage, int pageSize,
//			Map<String, MongoDBWhereEntity> condMap) {
//		return super.findPageListByWhereEntity(currentPage, pageSize, condMap, OpenApplyEntity.class);
//	}
//
//	@Override
//	public List<OpenApplyEntity> findAppListByDBWhere(Map<String, MongoDBWhereEntity> condMap) {
//		return super.findListByWhereEntity(condMap, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findAppListByMapCondition(Map<String, Object> condMap, int currentPage, int pageSize) {
//		return super.findByFieldsAsPage(condMap, currentPage, pageSize, OpenApplyEntity.class);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity, int currentPage, int pageSize) {
//		// 分页
//		Page<OpenApplyEntity> findAsPage = super.findAsPage(
//				generalSecretQueryBean(entity).with(new Sort(Direction.DESC, "createdate")), currentPage, pageSize,
//				OpenApplyEntity.class);
//
//		return findAsPage;
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findOpenApplyByEntity(QueryOpenApplyEntity entity, int currentPage, int pageSize) {
//		Criteria c = new Criteria();
//
//		if (StringUtils.isNotBlank(entity.getApplyName())) {
//			c.and(MongoFiledConstants.OPEN_APPLY_APPLYNAME).regex(entity.getApplyName(),"i");
//		}
//
//		if (StringUtils.isNotBlank(entity.getUserName())) {
//			c.and(MongoFiledConstants.OPEN_APPLY_USERNAME).regex(entity.getUserName(),"i");
//		}
//
//		if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
//			c.and(MongoFiledConstants.OPEN_APPLY_ENTERPRISENAME).regex(entity.getEnterpriseName(),"i");
//		}
//
//		if (null != entity.getCheckState()) {
//			c.and(MongoFiledConstants.OPEN_APPLY_CHECKSTATE).is(entity.getCheckState());
//		}
//
//		Query query = new Query(c).with(new Sort(Direction.DESC, MongoFiledConstants.BASIC_CREATEDATE));
//		// 分页
//		Page<OpenApplyEntity> findAsPage = super.findAsPage(query, currentPage, pageSize, OpenApplyEntity.class);
//
//		return findAsPage;
//	}
//
//	private Query generalSecretQueryBean(OpenApplyEntity entity) {
//		// 构建查询对象
//		Criteria c = new Criteria();
//
//		if (StringUtils.isNotBlank(entity.getUserId())) {
//			c.and("userId").is(entity.getUserId());
//		}
//
//		if (StringUtils.isNotBlank(entity.getApplyName())) {
//			Criteria c1 = Criteria.where("appName").regex(".*?" + entity.getApplyName() + ".*", "i");
//			Criteria c2 = Criteria.where("appKey").regex(".*?" + entity.getApplyName() + ".*", "i");
//			c.orOperator(c1, c2);
//		}
//
//		if (StringUtils.isNotBlank(entity.getNeqId())) {
//			c.and("id").ne(entity.getNeqId());
//		}
//
//		if (StringUtils.isAllLowerCase(entity.getUserName())) {
//			c.and("userName").regex(entity.getUserName(),"i");
//		}
//
//		if (StringUtils.isAllLowerCase(entity.getEnterpriseName())) {
//			c.and("enterpriseName").regex(entity.getEnterpriseName(),"i");
//		}
//
//		if (StringUtils.isNotBlank(entity.getApplyKey())) {
//			c.and("appKey").is(entity.getApplyKey());
//		}
//
//		if (null != entity.getCheckState()) {
//			c.and("checkState").is(entity.getCheckState());
//		}
//
//		// 构建排序对象
//		Query query = new Query(c).with(new Sort(Direction.DESC, "createDate"));
//
//		return query;
//	}
//
//	@Override
//	public List<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity) {
//		List<OpenApplyEntity> listApp = super.find(generalSecretQueryBean(entity), OpenApplyEntity.class);
//		return listApp;
//	}
//
//	@Override
//	/**
//	 * 批量审核
//	 */
//	public String bathUpdateByid(List<String> ids, Integer checkState, String checkMem) {
//		// TODO Auto-generated method stub
//		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
//		for (int i = 0; i < ids.size(); i++) {
//			list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//					Update.update("checkState", checkState), true, true));
//			list.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ids.get(i)))),
//					Update.update("checkMem", checkMem), true, true));
//		}
//		return String.valueOf(super.bathUpdate(super.mongoTemplate, OpenApplyEntity.class, list));
//	}
//
//	@Override
//	public List<OpenApplyEntity> getListByids(List<String> ids) {
//		// TODO Auto-generated method stub
//		return super.find(new Query(Criteria.where("id").in(ids)), OpenApplyEntity.class);
//	}
//
//	@Override
//	public OpenApplyEntity findByAppId(String openApplyId) {
//		
//		return super.findOneByField(DBFieldsConstants.APIS_APPID, openApplyId, OpenApplyEntity.class);
//	}
//
//}
