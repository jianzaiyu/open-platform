package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.openApply.service.IConsoleOpenApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: DocumentOpenApplyController
 * @Description: TODO
 * @create 2019/1/19 14:35/MKW/文档中心相关开放应用以及api接口
 * @update 2019/1/19 14:35/MKW/(说明。)....多次修改添加多个update
 */
@RestController
@RequestMapping("/document")
public class DocumentOpenApplyController {

    @Autowired
    private IConsoleOpenApplyService openApplyService;
    @RequestMapping(value="/batch/delete/openApply",method = RequestMethod.GET)
    public Result batchDeleteOpenApply(){

        return openApplyService.batchDeleteOpenApply();
    }

    @RequestMapping(value="/batch/insert/openApply", method= RequestMethod.GET)
    public Result batchInsertOpenApply(){

        return openApplyService.batchInsertOpenApply();
    }

    @RequestMapping(value="/openApply/list", method= RequestMethod.GET)
    public Result getOpenApplyList(HttpServletRequest request,
           @RequestParam(required = false) String owner,
           @RequestParam(required = false) String name,
           @RequestParam(required = true, defaultValue = "10") int pageSize,
           @RequestParam(required = true, defaultValue = "1") int currentPage){

        return openApplyService.getOpenApplyList(owner,name,currentPage,pageSize);
    }
}
