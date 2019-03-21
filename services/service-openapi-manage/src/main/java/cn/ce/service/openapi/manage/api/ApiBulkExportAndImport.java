package cn.ce.service.openapi.manage.api;

import cn.ce.service.openapi.base.apis.entity.ApiExportParamEntity;
import cn.ce.service.openapi.base.apis.service.IApiTransportService;
import cn.ce.service.openapi.base.common.*;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.util.PageValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
* @Description : api在不同环境的批量导出功能
* @Author : makangwei
* @Date : 2017年12月6日
*/
@Slf4j
@RestController
@RequestMapping(value="/apiBulk")
public class ApiBulkExportAndImport {

    @Resource
    private IApiTransportService apiTransportService;
    

	/**
	 * @Description: 导出文件
	 * @author: makangwei 
	 * @date:   2017年12月12日 下午6:23:06 
	 */
	@RequestMapping(value="/exportApis",method=RequestMethod.GET)
	public String exportApis(HttpServletResponse response, String recordId){
		// TODO 20171211 mkw 虽然返回的是string，但是实际String并不起作用
		log.info("批量导出api文档");
		
		return apiTransportService.exportApis(recordId, response);
	}
	
	/**
	 * 
	 * @Title: generalExportList
	 * @Description: 生成导出记录
	 * @author: makangwei 
	 * @date:   2017年12月12日 下午6:22:43 
	 */
	@RequestMapping(value="/generalExportList", method=RequestMethod.POST)
	public Result<?> generalExportList(HttpServletRequest request,
			@RequestBody ApiExportParamEntity exportParam){
		
		log.info("生成批量导出api的文件记录");
		
		if(exportParam.getApiIds() == null){
			exportParam.setApiIds(new ArrayList<String>());
		}
		if(exportParam.getAppIds() == null){
			exportParam.setAppIds(new ArrayList<String>());
		}
		
		User user = (User)request.getSession().getAttribute(Constants.SES_LOGIN_USER);
		
		if(null == user){
			return new Result<String>("用户session不存在", ErrorCodeNo.SYS003, null, Status.SUCCESS);
		}
	
		return apiTransportService.generalExportList(exportParam, user);
	}
	
	@RequestMapping(value="/importApis", method=RequestMethod.POST)
	public Result<?> importApis(HttpServletRequest request, @RequestParam("file") MultipartFile file){
		
		User user = (User)request.getSession().getAttribute(Constants.SES_LOGIN_USER);
		log.info("文件上传");
		
		byte[] b= null;
		String upStr = null;
		if(!file.getOriginalFilename().endsWith(".json")){
			return Result.errorResult("文件格式不正确", ErrorCodeNo.DOWNLOAD003, "", Status.FAILED);
		}
		try {
			b = file.getBytes();
			long size = file.getSize();
			if(size > AuditConstants.MAX_UPLOAD_SIZE){
				return new Result<String>("文件大小不能超过2M", ErrorCodeNo.UPLOAD002, null, Status.FAILED);
			}
			upStr = new String(b,"utf-8");
		} catch (IOException e) {
			log.info("上传文件读取数据发生异常");
			return new Result<String>("上传文件读取数据发生异常", ErrorCodeNo.SYS004, null, Status.FAILED);
		}
		
		return apiTransportService.importApis(upStr, user);
	}
	
	@RequestMapping(value="getExportRecords", method=RequestMethod.GET)
	public Result<?> getExportRecords(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(required=false,defaultValue= "1") int currentPage, 
			@RequestParam(required=false,defaultValue= "10")int pageSize){
	
		
		return apiTransportService.getExportRecords(PageValidateUtil.checkCurrentPage(currentPage)
				,PageValidateUtil.checkPageSize(pageSize));
	}
	
	@RequestMapping(value="getExportRecordById", method=RequestMethod.GET)
	public Result<?> getExportRecordById(@RequestParam(required=true)String recordId){
		
		return apiTransportService.getExportRecordById(recordId);
	}
	
	@RequestMapping(value="/getImportRecords", method=RequestMethod.GET)
	public Result<?> getImportRecords(
			@RequestParam(required=false,defaultValue= "1") int currentPage, 
			@RequestParam(required=false,defaultValue= "10")int pageSize){
		
		return apiTransportService.getImportRecords(PageValidateUtil.checkCurrentPage(currentPage)
				,PageValidateUtil.checkPageSize(pageSize));
	}
	
	@RequestMapping(value="/getImportRecordById", method=RequestMethod.GET)
	public Result<?> getImportRecordById(@RequestParam(required=true)String recordId){
		
		return apiTransportService.getImportRecordById(recordId);
	}
	
}
