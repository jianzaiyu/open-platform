package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.common.Constants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.guide.entity.GuideEntity;
import cn.ce.service.openapi.base.guide.entity.QueryGuideEntity;
import cn.ce.service.openapi.base.guide.service.IConsoleGuideService;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.util.CoverBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @Title: GuideController.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月12日 time下午8:06:46

 *
 **/
@RestController
@RequestMapping("/guideConsole")
@Api("应用指南")
public class GuideConsoleController {

	@Resource
	private IConsoleGuideService consoleGuideService;

	@RequestMapping(value = "/guide", method = RequestMethod.POST)
	@ApiOperation("添加指南")
	public Result<?> guideAdd(HttpSession session, @RequestBody GuideEntity g) {
		
		if (StringUtils.isBlank(g.getGuideName())) {
			return new Result<String>("指南名称不能为空!",ErrorCodeNo.SYS005,null,Status.FAILED);
		}
		User user = (User) session.getAttribute(Constants.SES_LOGIN_USER);
		return consoleGuideService.add(user, g);
	}

	@RequestMapping(value = "/updateGuide", method = {RequestMethod.PUT,RequestMethod.POST})
	@ApiOperation("修改指南")
	public Result<?> guideUpdate(@RequestBody GuideEntity guideEntity) {
		
		if(StringUtils.isBlank(guideEntity.getId())){
			return new Result<String>("指南不存在!",ErrorCodeNo.SYS015,null,Status.FAILED);
		}
		return consoleGuideService.update(guideEntity);
	}

	@RequestMapping(value = "/guideList", method = RequestMethod.POST)
	@ApiOperation("查询指南列表_TODO")
//	public CloudResult<?> guideList(@RequestBody QueryGuideEntity entity) {
	public Result<?> guideList(@RequestBody GuideEntity entity1,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		
		QueryGuideEntity entity = new QueryGuideEntity();
		boolean bool1 = CoverBeanUtils.copyProperties(entity, entity1);
		boolean bool2 = CoverBeanUtils.copyProperty(entity,"userName", entity1.getCreatUserName());
		boolean bool3 = CoverBeanUtils.copyProperty(entity, "openApplyId", entity1.getApplyId());
		if(!bool1 || !bool2 || !bool3){
			return Result.errorResult("参数复制异常", ErrorCodeNo.SYS036, null, Status.FAILED);
		}
		entity.setCurrentPage(currentPage);
		entity.setPageSize(pageSize);
		
		return consoleGuideService.guideList(entity);
	}

	@RequestMapping(value = "/guide/{gid}", method = RequestMethod.DELETE)
	@ApiOperation("##删除指南")
	public Result<?> guideDelete(HttpSession session,
			@PathVariable("gid") String id) {
		User user = (User) session.getAttribute(Constants.SES_LOGIN_USER);
		if(null == user){
			return new Result<String>("用户未登录",ErrorCodeNo.SYS003,null,Status.FAILED);
		}
		return consoleGuideService.delete(id);
	}

	@RequestMapping(value = "/guide/{gid}", method = RequestMethod.GET)
	@ApiOperation("获取指南详情")
	public Result<GuideEntity> getGuideByid(@PathVariable("gid") String id) {

		return consoleGuideService.getByid(id);
	}

	@RequestMapping(value = "/submitVerify", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> submitVerify(@RequestBody List<String> ids) {

		return consoleGuideService.submitVerify(ids);
	}

}
