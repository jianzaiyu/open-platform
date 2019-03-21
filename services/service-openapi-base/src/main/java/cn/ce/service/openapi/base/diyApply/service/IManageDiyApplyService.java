package cn.ce.service.openapi.base.diyApply.service;

import java.util.List;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.QueryDiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.interfaceMessageInfo.InterfaMessageInfoString;

/**
 * @Description : 说明
 * @Author : makangwei
 * @Date : 2017年10月16日
 */
public interface IManageDiyApplyService {

//	public CloudResult<Page<DiyApplyEntity>> findPagedApps(String productName, String userName, Integer checkState,
//			String applyName, int currentPage, int pageSize);

	Result<Page<DiyApplyEntity>> findPagedApps(QueryDiyApplyEntity queryApply);
	
	public Result<String> batchUpdate(String sourceConfig, List<String> ids, Integer checkState, String checkMem);


	public Result<InterfaMessageInfoString> registerBathApp(String sourceConfig, String tenantId, String apps);

	/***
	 * 
	 * @Title: findById @Description: 根据id加载应用信息 @param : @param applyId @param
	 *         : @return @return: ApplyEntity @throws
	 */
	public Result<DiyApplyEntity> findById(String applyId);

	
	

}
