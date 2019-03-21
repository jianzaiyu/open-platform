package cn.ce.service.openapi.manage.guide;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.guide.entity.GuideEntity;
import cn.ce.service.openapi.base.guide.entity.QueryGuideEntity;
import cn.ce.service.openapi.base.guide.service.IManageGuideService;
import cn.ce.service.openapi.base.util.SplitUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @Title: GuideManageController.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月13日 time上午9:34:37
 *
 **/

@RestController
@RequestMapping("/guideManage")
@Api("指南管理")
public class GuideManageController {

	/** 日志对象 */
	@Resource
	private IManageGuideService manageGuideService;

	@ApiOperation("指南列表_TODO")
	@RequestMapping(value = "/guideList", method = RequestMethod.POST)
//	public CloudResult<?> guideList(@RequestBody QueryGuideEntity guideEntity) {
	public Result<?> guideList(String guideName, String creatUserName, String applyId,Integer checkState,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		
		QueryGuideEntity guideEntity = new QueryGuideEntity();
		guideEntity.setGuideName(guideName);
		guideEntity.setUserName(creatUserName);
		guideEntity.setOpenApplyId(applyId);
		guideEntity.setCheckState(checkState);
		guideEntity.setCurrentPage(currentPage);
		guideEntity.setPageSize(pageSize);
		return manageGuideService.guideList(guideEntity);
	}

	@ApiOperation("指南详情_TODO")
	@RequestMapping(value = "/getGuideByid", method = RequestMethod.GET)
	public Result<GuideEntity> getGuideByid(@RequestParam(value = "id", required = true) String id) {
		return manageGuideService.getByid(id);

	}

	@ApiOperation("批量审核")
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	public Result<String> batchUpdate(@RequestParam(required=true) String ids, 
			@RequestParam(required=true) Integer checkState,
			@RequestParam(required=false) String checkMem) {
		return manageGuideService.batchUpdateCheckState(SplitUtil.splitStringWithComma(ids), checkState, checkMem);
	}

}
