package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.open.service.IOperatingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @Description : api bi 热度统计api
* @Author : makangwei
* @Date : 2018年5月28日
*/
@RestController
@RequestMapping("/open/hot")
public class OperatingController {

	@Resource
	private IOperatingService operatingService;
	

	@RequestMapping(value="/api/{startTimeStamp}/{endTimeStamp}",method=RequestMethod.GET)
	public Result<?> api(
			@PathVariable Long startTimeStamp,
			@PathVariable Long endTimeStamp,
			@RequestParam(required=false)Integer order,
			@RequestParam(required=false)String apiId,
			@RequestParam(required=false)Integer currentPage,
			@RequestParam(required=false)Integer pageSize){
		
		return operatingService.api(startTimeStamp,endTimeStamp,order,apiId,currentPage,pageSize);
	}
	
	@RequestMapping(value="openApply/{startTimeStamp}/{endTimeStamp}", method=RequestMethod.GET)
	public Result<?> openApply(			
			@PathVariable Long startTimeStamp,
			@PathVariable Long endTimeStamp,
			@RequestParam(required=false)Integer order,
			@RequestParam(required=false)String apiId,
			@RequestParam(required=false)String openApplyId,
			@RequestParam(required=false)Integer currentPage,
			@RequestParam(required=false)Integer pageSize){
		if(StringUtils.isBlank(openApplyId)){
			apiId = null;
		}
		return operatingService.openApply(startTimeStamp,endTimeStamp,order,apiId,openApplyId,currentPage,pageSize);
	}
	
	@RequestMapping(value="diyApply/{startTimeStamp}/{endTimeStamp}", method=RequestMethod.GET)
	public Result<?> diyApply(
			@PathVariable Long startTimeStamp,
			@PathVariable Long endTimeStamp,
			@RequestParam(required=false)Integer order,
			@RequestParam(required=false)String apiId,
			@RequestParam(required=false)String openApplyId,
			@RequestParam(required=false)String diyApplyId,
			@RequestParam(required=false)Integer currentPage,
			@RequestParam(required=false)Integer pageSize){
		if(StringUtils.isBlank(diyApplyId)){
			openApplyId = null;
			apiId = null;
		}else if(StringUtils.isBlank(openApplyId)){
			apiId = null;
		}
		
		return operatingService.diyApply(startTimeStamp,endTimeStamp,order,apiId,openApplyId,
				diyApplyId,currentPage,pageSize);
	}

	
}

