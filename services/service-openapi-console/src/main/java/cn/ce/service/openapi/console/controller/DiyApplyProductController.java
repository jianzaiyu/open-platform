package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.diyApply.entity.Menu;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.Apps;
import cn.ce.service.openapi.base.diyApply.entity.inparameter.GeneratorTenantKeyInParameterEntity;
import cn.ce.service.openapi.base.diyApply.entity.interfaceMessageInfo.InterfaMessageInfoString;
import cn.ce.service.openapi.base.diyApply.entity.tenantAppPage.TenantAppPage;
import cn.ce.service.openapi.base.diyApply.service.IConsoleDiyApplyService;
import cn.ce.service.openapi.base.diyApply.service.IPlublicDiyApplyService;
import cn.ce.service.openapi.base.util.PageValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @Title: DiyApplyProductController.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月13日 time上午11:02:16
 *
 **/
@RestController
@RequestMapping("/diyapplyProduct")
@Api("租户接口文档")
public class DiyApplyProductController {

	@Resource
	private IConsoleDiyApplyService consoleDiyApplyService;

	@Resource
	private IPlublicDiyApplyService plublicDiyApplyService;
	
	@RequestMapping(value = "findTenantAppsByTenantKey", method = RequestMethod.GET)
	@ApiOperation("###获取产品实例 查询带分页")
	public Result<TenantAppPage> findTenantAppsByTenantKey(
			HttpServletRequest request,
			@RequestParam(required = true) String key,
			@RequestParam(required = false) String appName, 
			@RequestParam(required = true, defaultValue = "10") int pageSize,
			@RequestParam(required = true, defaultValue = "1") int currentPage) {
		String sourceConfig = request.getParameter("sourceConfig");
		return plublicDiyApplyService.findTenantAppsByTenantKeyPage(sourceConfig,key, appName, currentPage, pageSize);
	}
	
	@RequestMapping(value = "/findPagedApps", method = RequestMethod.GET)
	@ApiOperation("获取所有应用列表")
	public Result<Apps> findPagedApps(
			HttpServletRequest request,
			@RequestParam(required = false) String owner,			
			@RequestParam(required = false) String name,
			@RequestParam(required = true, defaultValue = "10") int pageSize,			
			@RequestParam(required = true, defaultValue = "1") int currentPage) {

		String sourceConfig = request.getParameter("sourceConfig");
		return plublicDiyApplyService.findPagedApps(sourceConfig,owner, name, currentPage, pageSize);
	}

	@RequestMapping(value = "generatorTenantKey", method = RequestMethod.POST)
	@ApiOperation("获取网站")
	public Result<InterfaMessageInfoString> generatorTenantKey(
			@RequestBody GeneratorTenantKeyInParameterEntity queryVO,
			HttpServletRequest request, HttpServletResponse response) {

		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.generatorTenantKey(sourceConfig,queryVO.getId());
	}

	/**
	 * @Title: productMenuList
	 * @Description: 该接口已经废弃使用王佳的接口
	 */
	@ApiOperation("产品菜单列表")
	@Deprecated
	@RequestMapping(value = "/productMenuList", method = RequestMethod.GET)
	public Result<String> productMenuList(
			HttpServletRequest request,
			@RequestParam(required = true) String bossInstanceCode) {

		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.productMenuList(sourceConfig,bossInstanceCode);
	}

	
	/**
	 * @Title: registerMenu
	 * @Description: 该接口已经废弃。改接王佳的接口
	 */
	@ApiOperation("注册菜单")
	@Deprecated
	@RequestMapping(value = "/registerMenu", method = RequestMethod.POST)
	public Result<String> registerMenu(
			HttpServletRequest request,
			@RequestParam(value = "appid", required = true) String appid,
			@RequestParam(value = "bossInstanceCode", required = true) String bossInstanceCode,
			String menuJson) {
		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.registerMenu(sourceConfig,appid, bossInstanceCode, menuJson);
	}
	
	/**
	 * 
	 * @Title: productMenuList1
	 * @Description: 菜单功能改造。调用王佳的接口
	 * @author: makangwei
	 * @date:   2018年3月7日 下午5:45:04  
	 */
	@RequestMapping(value="/productMenuList1", method=RequestMethod.GET)
	public Result<?> productMenuList1(
			HttpServletRequest request,
			@RequestParam(required = true) String tenantId){
		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.productMenuList1(sourceConfig,tenantId);
	}
	
	/**
	 * @Title: registerMenu
	 * @Description: 注册或修改菜单
	 * @author: makangwei
	 * @date:   2018年3月7日 下午5:45:40  
	 * @throws
	 */
	@RequestMapping(value = "/registerMenu1", method = RequestMethod.POST)
	public Result<?> registerMenu1(
			HttpServletRequest request,
			@RequestParam String tenantId,
			@RequestBody ArrayList<Menu> menus) {
		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.registerMenu1(sourceConfig,tenantId,menus);
	}
	
	/**
	 * 
	 * @Title: deleteMenu1
	 * @Description: 删除菜单
	 * @author: makangwei
	 * @date:   2018年3月8日 上午10:33:10  
	 */
	@RequestMapping(value="/deleteMenu1", method=RequestMethod.POST)
	public Result<?> deleteMenu1(HttpServletRequest request,
			 @RequestParam ArrayList<String> ids){

		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.deleteMenu1(sourceConfig,ids);
	}
	
	// 查看当前应用可以访问哪些开放应用下的哪些api
	@RequestMapping(value="/getLimitScope",method=RequestMethod.GET) //查看当前定制应用是否有权限访问某组api或者某个api
	@ApiOperation("获取定制应用下的可访问的某个开放应用列表下的api_DOTO") //修改返回参数ApiEntity为NewApiEntity
	public Result<?> getLimitScope(
			@RequestParam(required=true) String diyApplyId,
			@RequestParam(required=true) String openApplyId,
			@RequestParam(required=false) String apiName,
			@RequestParam(required=false, defaultValue="1") Integer currentPage,
			@RequestParam(required=false, defaultValue="10") Integer pageSize){
		
		return plublicDiyApplyService.limitScope(diyApplyId, openApplyId, apiName, PageValidateUtil.checkCurrentPage(currentPage), PageValidateUtil.checkPageSize(pageSize));
	}
}
