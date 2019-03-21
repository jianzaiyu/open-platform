package cn.ce.service.openapi.manage.diyApply;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.diyApply.entity.QueryDiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.service.IManageDiyApplyService;
import cn.ce.service.openapi.base.util.SplitUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description : 说明
 * @Author : makangwei
 * @Date : 2017年10月16日
 */
@RestController
@RequestMapping("/diyApplyManage")
@Api("定制应用")
public class DiyApplyController {

	// private static Logger _LOGGER =
	// LoggerFactory.getLogger(DiyApplyController.class);

	@Resource
	IManageDiyApplyService manageDiyApplyService;

	@RequestMapping(value = "/diyApplyList", method = RequestMethod.POST)
	@ApiOperation("定制应用列表_TODO")
//	public CloudResult<?> diyApplyList(@RequestBody QueryDiyApplyEntity queryApply) {
	public Result<?> diyApplyList(String productName, String userName, Integer checkState, String applyName,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		QueryDiyApplyEntity queryApply = new QueryDiyApplyEntity();
		queryApply.setProductName(productName);
		queryApply.setUserName(userName);
		queryApply.setCheckState(checkState);
		queryApply.setApplyName(applyName);
		queryApply.setCurrentPage(currentPage);
		queryApply.setPageSize(pageSize);
//		return manageDiyApplyService.findPagedApps(productName, userName, checkState, applyName, currentPage, pageSize);
		return manageDiyApplyService.findPagedApps(queryApply);
	}

	@RequestMapping(value = "/getApplyById", method = RequestMethod.GET)
	@ApiOperation("根据应用标识查询应用")
	public Result<?> getApplyById(@RequestParam(required = true) String applyId){
		return manageDiyApplyService.findById(applyId);
	}

	@ApiOperation("###批量审核 调用租户接口2， 开发者在开放平台发布应用审核 发布应用")
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	public Result<?> batchUpdate(
			HttpServletRequest request,
			@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "checkState",required = true) Integer checkState, 
			@RequestParam(value = "checkMem", required = false) String checkMem) {
		String sourceConfig = request.getParameter("sourceConfig");
		return manageDiyApplyService.batchUpdate(sourceConfig,SplitUtil.splitStringWithComma(ids), checkState, checkMem);
	}

}
