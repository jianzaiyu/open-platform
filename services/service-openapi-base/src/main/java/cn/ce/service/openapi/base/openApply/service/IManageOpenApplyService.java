package cn.ce.service.openapi.base.openApply.service;

import java.util.List;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;

/**
 * 
 * @ClassName: IAppService
 * @Description: 服务分组
 * @author dingjia@300.cn
 *
 */
public interface IManageOpenApplyService {

	/**
	 * @Description: 根据应用Id查询应用信息 @param id: 应用id @return 返回应用对象 @throws
	 */
	public Result<OpenApplyEntity> findById(String id);

	/**
	 * @Description: 修改应用信息 @param app: 应用对象 @throws
	 */
//	public void modifyById(OpenApplyEntity app);

	/**
	 * @Description: 查询所有应用 @return 返回应用集合 @throws
	 */
//	public List<OpenApplyEntity> getAll();

	/**
	 * @Description: 根据用户查询应用列表（翻页） @param userId: 分组ID @param currentPage:
	 *               当前页 @param pageSize: 每页显示数量 @return 返回应用集合 @throws
	 */
//	public Page<OpenApplyEntity> getAppList(String userId, int currentPage, int pageSize);

	/**
	 * @Description: 根据App实体对象查询应用列表（翻页） @param appEntity: 服务分类实体 @param
	 *               currentPage: 当前页 @param pageSize: 每页显示数量 @return 返回应用集合 @throws
	 */
	// public Page<AppEntity> getAppListByDBWhere(Map<String,MongoDBWhereEntity>
	// condition, int currentPage, int pageSize);
//	public Page<OpenApplyEntity> getAppListByDBWhere(OpenApplyEntity appentity, int currentPage, int pageSize);

	/**
	 * @Description: 删除应用 @param id: 应用id @throws
	 */
//	public void delById(String id);

//	public OpenApplyEntity findAppByAppName(String appName);

	/***
	 * 根据实体对象分页查询App集合
	 * 
	 * @param entity
	 *            查询的实体对象
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
//	Page<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity, int currentPage, int pageSize);

	/***
	 * 根据app实体中appName及appKey检测是否已经存在
	 * 
	 * @param entity
	 *            App实体
	 * @return
	 */
//	CloudResult<OpenApplyEntity> checkAppIsHave(OpenApplyEntity entity);

//	List<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity);

//	public CloudResult<String> deleteById(String appId);

//	public CloudResult<JSONObject> appList(HttpServletRequest request, HttpServletResponse response);

	// public CloudResult<String> addGroup(HttpSession session, OpenApplyEntity app);

//	public CloudResult<String> delGroup(String appId);

	// public CloudResult<String> modifyGroup(OpenApplyEntity app);

//	public CloudResult<Page<OpenApplyEntity>> groupList(String userId, int currentPage, int pageSize);

//	public CloudResult<String> submitVerify(String id);

//	public CloudResult<String> addGroup1(HttpSession session, OpenApplyEntity app);

//	public CloudResult<String> deleteGroup(String id);

//	public CloudResult<String> modifyGroup1(OpenApplyEntity app);

	// public CloudResult<Page<OpenApplyEntity>> groupList1(String appName, String
	// userName, String enterpriseName,
	// String checkState, int currentPage, int pageSize);

	public Result<Page<OpenApplyEntity>> findOpenApplyList(QueryOpenApplyEntity queryEntity);

	public Result<String> batchUpdate(String sourceConfig, List<String> ids, Integer checkState, String batchUpdate);
	// public CloudResult<String> modifyGroup1(OpenApplyEntity app);

//	public CloudResult<Page<OpenApplyEntity>> groupList1(String appName, String checkState, int currentPage, int pageSize);

//	public CloudResult<InterfaMessageInfoString> saveOrUpdateApps(String apps);

}
