package cn.ce.service.openapi.base.diyApply.service;

//import cn.ce.service.openapi.base.annotation.InterfaceDescription;
import java.util.ArrayList;
import java.util.List;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.Menu;
import cn.ce.service.openapi.base.diyApply.entity.QueryDiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.interfaceMessageInfo.InterfaMessageInfoString;

/***
 * 
 * 应用服务接口
 * 
 * @author lida
 * @date 2017年8月23日14:32:35
 *
 */
public interface IConsoleDiyApplyService {

	/***
	 * 更新/添加实体
	 * 
	 * @param entity
	 */
//	@InterfaceDescription(name="",des="保存实体",version="1.0")
	Result<?> saveApply(String sourceConfig, DiyApplyEntity entity);

	/***
	 * 根据id删除实体
	 * 
	 * @param id
	 */
	Result<String> deleteApplyByid(String id);

	/***
	 * 根据条件分页查询应用列表
	 * 
	 * @return
	 */
	Result<Page<DiyApplyEntity>> findApplyList(QueryDiyApplyEntity queryApply);

	/**
	 * 根据id加载应用信息
	 * 
	 * @param id
	 * @return
	 */
	//CloudResult<DiyApplyEntity> getApplyById(String id, int pageSize, int currentPage);

	/***
	 * 
	 * @Title: findById @Description: 根据id加载应用信息 @param : @param applyId @param
	 *         : @return @return: ApplyEntity @throws
	 */
	public Result<?> findById(String applyId);

	
	public Result<InterfaMessageInfoString> generatorTenantKey(String sourceConfig, String id);


	public Result<String> batchUpdateCheckState(String ids, Integer checkState, String checkMem);

	/**
	 * 
	 * @Title: productMenuList @Description: 获取产品菜单列表 @param : @param
	 * bossInstanceCode @param : @return @return:
	 * CloudResult<InterfaMessageInfoJasonObject> @throws
	 */
	public Result<String> productMenuList(String sourceConfig, String bossInstanceCode);

	/**
	 * 
	 * @Title: registerMenu @Description: 注册菜单 @param : @param appid @param : @param
	 * bossInstanceCode @param : @param menuJson @param : @return @return:
	 * CloudResult<InterfaMessageInfoJasonObject> @throws
	 */
	public Result<String> registerMenu(String sourceConfig, String appid, String bossInstanceCode, String menuJson);

	Result<?> updateApply(DiyApplyEntity apply);

//	CloudResult<?> migraDiyApply();

	Result<?> productMenuList1(String sourceConfig, String tenantId);

	Result<?> registerMenu1(String sourceConfig, String tenantId, List<Menu> menus);

	Result<?> deleteMenu1(String sourceConfig, ArrayList<String> ids);

    int getTotalDiyApply();
}
