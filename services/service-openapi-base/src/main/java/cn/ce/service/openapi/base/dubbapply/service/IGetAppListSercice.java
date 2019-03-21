package cn.ce.service.openapi.base.dubbapply.service;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.dubbapply.entity.Interfaceapplyentity.DubboApps;

public interface IGetAppListSercice {

	/**
	 * 获取物理应用列表
	 * 
	 * @param unit
	 * @return
	 */
	public Result<DubboApps> findAppsByUnit(String unit);
}
