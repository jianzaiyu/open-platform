package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.apis.service.IConsoleApiService;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.diyApply.service.IConsoleDiyApplyService;
import cn.ce.service.openapi.console.entity.HomeDataStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: HomePageStatController
 * @Description: TODO
 * @create 2019/1/7 10:03/MKW/首页数据统计
 * @update 2019/1/7 10:03/MKW/(说明。)....多次修改添加多个update
 */
@RestController
@RequestMapping("statistics")
public class HomePageStatController {

    Logger logger = LoggerFactory.getLogger(HomePageStatController.class);

    @Resource
    private IConsoleApiService consoleApiService;
    @Resource
    private IConsoleDiyApplyService diyApplyService;

    private static HomeDataStatistics homeData = new HomeDataStatistics();
//    @Resource
//    private IConsoleOpenApplyService openApplyService;

    //审核通过的api总量
    @RequestMapping(value = "/total/api", method = RequestMethod.GET)
    public Result totalApi(){
        int num = consoleApiService.getTotalAmount();
        return Result.successResult("",num);
    }

    //开放应用定制应用总量
    @RequestMapping(value="/total/application", method = RequestMethod.GET)
    public Result totalApplication(){

        //获取具有审核通过的api并且api类型为开放的开放应用总量。
        int openApplyAccount = consoleApiService.getTotalOpenApply();

        //获取定制应用总量
        int diyApplyAccount = diyApplyService.getTotalDiyApply();

        return Result.successResult("",openApplyAccount+diyApplyAccount);
    }

    @RequestMapping(value = "/total/apiVisit", method= RequestMethod.GET)
    public Result totalApiVisit(){

//        long total = esService.getTotalAccessApi();
        long total = 888;
        if(total > 0){
            return Result.successResult("",total);
        }else{
            return Result.errorResult("es连接异常",ErrorCodeNo.SYS038,-1, Status.FAILED);
        }
    }



    @RequestMapping(value="/home", method= RequestMethod.GET)
    public Result home(){
        refreshHomeData();
        return Result.successResult("",homeData);
    }

    private void refreshHomeData() {

        int api = consoleApiService.getTotalAmount();
        if(api > 0){
            homeData.setApi(api);
        }
        int openApply = consoleApiService.getTotalOpenApply();
        if(openApply > 0){
            homeData.setOpenApply(openApply);
        }
        int diyApply = diyApplyService.getTotalDiyApply();
        if(diyApply > 0){
            homeData.setDiyApply(diyApply);
        }
        try{
//            long apiAccess =  esService.getTotalAccessApi();
            long apiAccess =  888;
            if(apiAccess > 0){
                homeData.setApiAccess(apiAccess);
            }
        }catch (Exception e){
            logger.error("获取es中的api访问统计异常",e);
        }
    }

}
