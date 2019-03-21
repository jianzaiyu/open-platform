package cn.ce.service.openapi.base.diyApply.service;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.Apps;
import cn.ce.service.openapi.base.diyApply.entity.tenantAppPage.TenantAppPage;
import cn.ce.service.openapi.base.diyApply.entity.tenantAppsEntity.TenantApps;

/**
 *
 * @Title: IPlublicDiyApplyService.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月18日 time下午7:50:17
 *
 **/
public interface IPlublicDiyApplyService {

	public Result<Apps> findPagedApps(String sourceConfig, String owner, String name, int pageNum, int pageSize);

	/**
	 * @Description 根据key获取产品实例
	 * @param key
	 * @return 产品实例
	 */
	public Result<TenantApps> findTenantAppsByTenantKey(String sourceConfig, String key);

	public Result<TenantAppPage> findTenantAppsByTenantKeyPage(
            String sourceConfig, String key, String appName, int pageNum, int pageSize);

	Result<?> limitScope(String diyApplyId, String openApplyId, String apiName, Integer currentPage, Integer pageSize);


}
