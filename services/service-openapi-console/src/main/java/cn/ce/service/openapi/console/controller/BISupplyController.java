package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.apis.service.IConsoleApiService;
import cn.ce.service.openapi.base.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Title: cn.ce.platform_console.operating.endpoint
 * @Description: 提供给BI的调用接口用于获取开放应用，定制应用，api的对应关系
 * @create 2018\5\24 0024/makangwei
 * @Copyright:中企动力科技股份有限公司 1999-2018 300.cn
 * All rights Reserved, Designed By www.300.cn
 */
@RestController
@RequestMapping("/open")
public class BISupplyController {

    @Resource
    private IConsoleApiService apiService;

    @RequestMapping(value = "/getOpenApplyBound", method = RequestMethod.GET)
    public Result<?> getOpenApplyBound(){

        return apiService.getOpenApplyBound();
    }

    @RequestMapping(value = "getDiyApplyBound", method = RequestMethod.GET)
    public Result<?> getDiyApplyBound(){

        return apiService.getDiyApplyBound();
    }
}
