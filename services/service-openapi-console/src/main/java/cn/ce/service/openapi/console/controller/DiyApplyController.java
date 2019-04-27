package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.diyApply.entity.tenantAppsEntity.Tenant;
import cn.ce.service.openapi.base.account.service.AccountService;
import cn.ce.service.openapi.base.common.*;
import cn.ce.service.openapi.base.common.gateway.ApiCallUtils;
import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.QueryDiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.service.IConsoleDiyApplyService;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.util.CoverBeanUtils;
import cn.ce.service.openapi.base.util.PropertiesUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * 
 * 
 * @ClassName: DiyApplyController
 * @Description:定制应用控制类
 * @author: lida
 * @date: 2017年10月14日 下午3:41:37
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 *
 */
@Slf4j
@RestController
@RequestMapping("/diyApply")
@Api("定制应用控制类")
public class DiyApplyController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Resource
	private IConsoleDiyApplyService consoleDiyApplyService;

	@RequestMapping(value = "/findApplyList", method = RequestMethod.POST)
	@ApiOperation(value = "根据条件查询应用列表_TODO", httpMethod = "POST", response = Result.class, notes = "根据条件查询应用列表")
	public Result<?> findApplyList(@RequestBody DiyApplyEntity apply,Principal principal,@RequestHeader(required = false) String Authorization,
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "1") int currentPage) {
		
		QueryDiyApplyEntity queryApply = new QueryDiyApplyEntity();
		if(!CoverBeanUtils.copyProperties(queryApply, apply)){
			return new Result<String>("程序内部异常",ErrorCodeNo.SYS004, null, Status.FAILED);
		}
		queryApply.setApplyId(apply.getId());
		queryApply.setCurrentPage(currentPage);
		queryApply.setPageSize(pageSize);
		
		//只获取当前登录的用户数据,如果获取数据失败就会报异常
		if(principal == null){
			return new Result<String>("用户未登录", ErrorCodeNo.SYS003, null, Status.FAILED);
		}
		cn.ce.framework.base.pojo.Result result = accountService.selectUserDetailByUserName(Authorization);
		User user = JSON.parseObject(JSON.toJSONString(result.getData()), User.class);
		queryApply.setUserId(user.getId().toString());

		if(StringUtils.isBlank(apply.getProjectId())){
			// 项目id允许为空？
		}
		
		return consoleDiyApplyService.findApplyList(queryApply);
	}

	@RequestMapping(value = "/deleteApplyByid", method = RequestMethod.DELETE)
	@ApiOperation("根据标识删除应用_TODO")
	public Result<?> deleteApplyByid(HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id) {

		return consoleDiyApplyService.deleteApplyByid(id);

	}

	@RequestMapping(value = "/getApplyById", method = RequestMethod.GET)
	@ApiOperation("根据应用标识查询应用")
	public Result<?> getApplyById(@RequestParam(required = true) String applyId) {

		return consoleDiyApplyService.findById(applyId);
	}

	/**
	 * 
	 * @Title: saveApply
	 * @Description: 做兼容处理，如果包含id支持update
	 * @author: makangwei
	 * @date:   2018年2月8日 下午7:29:09  
	 */
	@RequestMapping(value = "/saveApply", method = RequestMethod.POST)
	@ApiOperation("新增或修改应用")
	public Result<?> saveApply(HttpServletRequest request,Principal principal,@RequestHeader(required = false) String Authorization, @RequestBody DiyApplyEntity apply) {

		//如果id不为空就update
		if(StringUtils.isNotBlank(apply.getId())){
			return consoleDiyApplyService.updateApply(apply);
		}
		
		if (StringUtils.isBlank(apply.getApplyName())) {
			return new Result<String>("应用名称不能为空!",ErrorCodeNo.SYS005,null,Status.FAILED);
		}

		cn.ce.framework.base.pojo.Result result = accountService.selectUserDetailByUserName(Authorization);
		User user = JSON.parseObject(JSON.toJSONString(result.getData()), User.class);

		apply.setUser(user);

		String sourceConfig = request.getParameter("sourceConfig");
		return consoleDiyApplyService.saveApply(sourceConfig,apply);

	}

	@RequestMapping(value = "/updateApply", method = RequestMethod.POST)
	@ApiOperation("修改应用，不能修改产品密钥")
	public Result<?> updateApply(@RequestBody DiyApplyEntity apply) {
		return consoleDiyApplyService.updateApply(apply);
	}

	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	@ApiOperation("批量发布应用 更改checkState为1_TODO")
	public Result<String> batchUpdate(@RequestParam String ids) {
		
		return consoleDiyApplyService.batchUpdateCheckState(ids, AuditConstants.DIY_APPLY_CHECKED_COMMITED, null);
	}

	@RequestMapping(value="/getProjectList",method = RequestMethod.GET)
	public Object getProjectList(HttpServletRequest request){
        String sourceConfig = request.getParameter("sourceConfig");

        if(StringUtils.equalsIgnoreCase(DataSourceEnum.SANDBOX.toString(),sourceConfig)){
            String ticket = request.getHeader("ticket");
            log.info("getProjectList ticket:{}",ticket);
            String url = propertiesUtil.getSourceConfigValue(null,"getProjectsList");
            Map<String,String> headers = new HashMap<String, String>();
            headers.put("ticket",request.getHeader("ticket"));
            String resultStr =  ApiCallUtils.getOrDelMethod(url,headers,io.netty.handler.codec.http.HttpMethod.GET);
            return com.alibaba.fastjson.JSONObject.parseObject(resultStr);
        }else{
            com.alibaba.fastjson.JSONObject job = new com.alibaba.fastjson.JSONObject();
            job.put("Message","OK");
            job.put("Data",new ArrayList<Object>());
            job.put("Code",200);
            return job;
        }
	}
}
