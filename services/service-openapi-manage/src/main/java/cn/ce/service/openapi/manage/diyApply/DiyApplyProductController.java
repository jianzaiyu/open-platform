package cn.ce.service.openapi.manage.diyApply;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.Apps;
import cn.ce.service.openapi.base.diyApply.entity.inparameter.RegisterBathAppInParameterEntity;
import cn.ce.service.openapi.base.diyApply.entity.interfaceMessageInfo.InterfaMessageInfoString;
import cn.ce.service.openapi.base.diyApply.entity.tenantAppPage.TenantAppPage;
import cn.ce.service.openapi.base.diyApply.service.IManageDiyApplyService;
import cn.ce.service.openapi.base.diyApply.service.IPlublicDiyApplyService;
import cn.ce.service.openapi.base.util.PageValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @Title: DiyApplyProductController.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月13日 time上午11:02:16
 *
 **/
@Slf4j
@RestController
@RequestMapping("/diyapplyProduct")
@Api("manage租户接口")
public class DiyApplyProductController {

	@Resource
	private IManageDiyApplyService manageDiyApplyService;
	@Resource
	private IPlublicDiyApplyService plublicDiyApplyService;

	@RequestMapping(value = "/findPagedApps", method = RequestMethod.GET)
	@ApiOperation("获取所有应用列表")
	public Result<Apps> findPagedApps(
			HttpServletRequest request,
			@RequestParam(required = false) String owner,
			@RequestParam(required = false) String name,
			@RequestParam(required = true, defaultValue = "10") int pageSize,
			@RequestParam(required = true, defaultValue = "1") int currentPage) {
		
		log.info("*************获取所有产品列表************");
		log.info("查询条件：");
		log.info("所属企业:"+owner);
		log.info("名称模糊："+name);

		String sourceConfig = request.getParameter("sourceConfig");
		return plublicDiyApplyService.findPagedApps(sourceConfig, owner, name,
				PageValidateUtil.checkCurrentPage(currentPage), 
				PageValidateUtil.checkPageSize(pageSize));
	}

	@RequestMapping(value = "findTenantAppsByTenantKey", method = RequestMethod.GET)
	@ApiOperation("获取产品实例 查询带分页")
	public Result<TenantAppPage> findTenantAppsByTenantKey(
			HttpServletRequest request,
			@RequestParam(required = true) String key,
			@RequestParam(required = false)  String appName,
			@RequestParam(required = true, defaultValue = "10") int pageSize,
			@RequestParam(required = true, defaultValue = "1") int currentPage) {
		String sourceConfig = request.getParameter("sourceConfig");
		return plublicDiyApplyService.findTenantAppsByTenantKeyPage(sourceConfig, key, appName, currentPage, pageSize);
	}

	@RequestMapping(value = "registerBathApp", method = RequestMethod.POST)
	@ApiOperation("开发者在开放平台发布应用审核")
	public Result<InterfaMessageInfoString> registerBathApp(
			@RequestParam(required = false) String tenantId,
			@RequestBody RegisterBathAppInParameterEntity[] queryVO, HttpServletRequest request,
			HttpServletResponse response) {
		String sourceConfig = request.getParameter("sourceConfig");
		return manageDiyApplyService.registerBathApp(sourceConfig,tenantId, JSONArray.fromObject(queryVO).toString());
	}
	
	// 查看当前应用可以访问哪些开放应用下的哪些api
	@RequestMapping(value="/getLimitScope",method=RequestMethod.GET) //查看当前定制应用是否有权限访问某组api或者某个api
	public Result<?> getLimitScope(
			@RequestParam String diyApplyId,
			@RequestParam String openApplyId,
			@RequestParam(required=false) String apiName,
			@RequestParam(required=false, defaultValue="1") Integer currentPage,
			@RequestParam(required=false, defaultValue="10") Integer pageSize){
		return plublicDiyApplyService.limitScope(diyApplyId, openApplyId, apiName, PageValidateUtil.checkCurrentPage(currentPage), PageValidateUtil.checkPageSize(pageSize));
	}

}
