package cn.ce.service.openapi.base.apis.service;

import java.util.List;

import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.entity.QueryApiEntity;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.users.entity.User;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月12日
*/
public interface IConsoleApiService {

	Result<?> publishApi(String sourceConfig, User user, NewApiEntity apiEntity);

	Result<?> submitApi(List<String> apiId);

	Result<?> modifyApi(String sourceConfig, NewApiEntity apiEntity);

	Result<?> showApi(String apiId);

	Result<?> showApiList(QueryApiEntity entity);

//	CloudResult<?> checkApiEnName(String apiEnName, String openApplyId);

	Result<?> checkApiChName(String apiChName, String openApplyId);

	Result<?> checkVersion(String versionId, String version);
	
	boolean boundDiyApplyWithApi(
            String policyId,
            String clientId,
            String secret,
            Integer rate,
            Integer per,
            Integer quotaMax,
            Integer quotaRenewRate,
            List<String> openApplyIds);

	Result<?> checkListenPath(String listenPath);

	Result<?> getResourceType(String sourceConfig);

//	CloudResult<?> migraApi();

	Result<?> showDocApiList(QueryApiEntity apiEntity);
	
	// warn 返回的是开放应用绑定的version_id 作为api_id
	// 该接口作为网关获取开放应用和api绑定关系的调用。不作为其它调用
    Result<?> getOpenApplyBound();

    // warn 返回的是开放应用绑定的version_id 作为api_id
 	// 该接口作为定制应用绑定开放应用和api绑定关系的调用。不作为其它调用
	Result<?> getDiyApplyBound();

	// 获取审核通过的api总量
    int getTotalAmount();

	int getTotalOpenApply();


//	CloudResult<?> migraQueryArgs();

	
}
