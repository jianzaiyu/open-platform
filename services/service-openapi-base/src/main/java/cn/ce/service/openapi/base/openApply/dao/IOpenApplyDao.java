package cn.ce.service.openapi.base.openApply.dao;//package cn.ce.service.openapi.base.openApply.dao;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.bean.MongoDBWhereEntity;
//import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
//import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;
//
///**
// * 
// * @ClassName: IAppDAO
// * @Description: 功能分组数据库操作接口
// * @author dingjia@300.cn
// *
// */
//public interface IOpenApplyDao {
//	/**
//	 * @Description 添加应用
//	 * @param app
//	 *            应用信息
//	 */
//	void addApp(OpenApplyEntity app);
//
//	/**
//	 * @Description 根据应用Id查询应用信息 @param id 应用id @return 返回应用对象 @throws
//	 */
//	OpenApplyEntity findById(String id);
//
//	/**
//	 * @Description 修改应用信息 @param id 应用id @param app 应用对象 @return 返回 @throws
//	 */
//	int modifyById(String id, OpenApplyEntity app);
//
//	/**
//	 * @Description 获取某个会员所有应用列表 @return 返回应用集合 @throws
//	 */
//	List<OpenApplyEntity> getAll();
//
//	/**
//	 * @Description 根据应用id获取应用列表 @param userId 用户id @return 返回应用信息 @throws
//	 */
//	Page<OpenApplyEntity> getAppList(String userId);
//
//	/***
//	 * 根据查询条件实体构建Apps集合（分页）
//	 * 
//	 * @author lida
//	 * @date 2017年8月8日14:15:27
//	 * @param currentPage
//	 *            当前页
//	 * @param pageSize
//	 *            页数
//	 * @param condMap
//	 *            查询条件实体
//	 * @return
//	 */
//	Page<OpenApplyEntity> findAppListPageByDBWhere(int currentPage, int pageSize,
//			Map<String, MongoDBWhereEntity> condMap);
//
//	/***
//	 * 根据查询条件实体构建Apps集合
//	 * 
//	 * @author lida
//	 * @date 2017年8月8日14:15:57
//	 * @param condMap
//	 *            查询条件实体
//	 * @return
//	 */
//	List<OpenApplyEntity> findAppListByDBWhere(Map<String, MongoDBWhereEntity> condMap);
//
//	/**
//	 * @Description 删除应用 @param id 应用id @return 返回 @throws
//	 */
//	int delById(String id);
//
//	/**
//	 * @Description: 根据应用id获取应用列表 @param appKey appkey @return 返回应用信息 @throws
//	 */
//	OpenApplyEntity findByAppKey(String appKey);
//
//	/**
//	 * @Description: 查询APP集合 @param appName 应用名称 @param currentPage 当前页 @param
//	 *               pageSize 每页显示数量 @return Page<AppEntity> 返回类型 @throws
//	 */
//	Page<OpenApplyEntity> getAsPage(String appName, int currentPage, int pageSize);
//
//	/**
//	 * @Description: 查询APP集合 @param appName 应用名称 @param appCheckstate 应用审核状态 @param
//	 *               currentPage 当前页 @param pageSize 每页显示数量 @return Page<AppEntity>
//	 *               返回类型 @throws
//	 */
//	Page<OpenApplyEntity> getAsPage(String appName, Integer appCheckstate, int currentPage, int pageSize);
//
//	/**
//	 * @Description: 查询APP集合 @param appName 应用名称 @param groupCheckstate 审核状态 @return
//	 *               List<?> 返回类型 @throws
//	 */
//	List getAppWithGroups(String appName, int groupCheckstate);
//
//	/**
//	 * @Description: 查询APP集合 @param appName 应用名称 @param ids 审核状态 @param currentPage
//	 *               当前页 @param pageSize 每页显示数量 @return Page 返回类型 @throws
//	 */
//	Page<OpenApplyEntity> findInAsPage(String appName, Collection<?> ids, int currentPage, int pageSize);
//
//	/**
//	 * @Description: 查询APP集合 @param appName 应用名称 @param ids 审核状态 @param currentPage
//	 *               当前页 @param pageSize 每页显示数量 @return Page 返回类型 @throws
//	 */
//	Page<OpenApplyEntity> findNotInAsPage(String appName, Collection<?> ids, int currentPage, int pageSize);
//
//	/***
//	 * 根据实体对象分页查询App集合
//	 * 
//	 * @param entity
//	 *            查询的实体对象
//	 * @param currentPage
//	 * @param pageSize
//	 * @return
//	 */
//	Page<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity, int currentPage, int pageSize);
//	
//	Page<OpenApplyEntity> findOpenApplyByEntity(QueryOpenApplyEntity entity, int currentPage, int pageSize);
//
//	List<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity);
//
//	Page<OpenApplyEntity> findAppListByMapCondition(Map<String, Object> condMap, int currentPage, int pageSize);
//
//	OpenApplyEntity findAppByAppName(String appName);
//	/**
//	 * 
//	 * @param ids
//	 * @return 受影响的行数
//	 */
//	public String bathUpdateByid(List<String> ids,Integer checkState,String checkMem);
//	
//	/**
//	 * 
//	 */
//	public List<OpenApplyEntity> getListByids(List<String> ids);
//
//	OpenApplyEntity findByAppId(String openApplyId);
//}
