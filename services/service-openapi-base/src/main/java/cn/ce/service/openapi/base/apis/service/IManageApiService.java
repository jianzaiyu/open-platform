package cn.ce.service.openapi.base.apis.service;

import java.util.List;

import cn.ce.service.openapi.base.apis.entity.QueryApiEntity;
import cn.ce.service.openapi.base.common.Result;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月12日
*/
public interface IManageApiService {

	Result<?> auditApi(List<String> apiId, Integer checkState, String checkMem, boolean isMocked);

	Result<?> showApi(String apiId);

	Result<?> apiList(QueryApiEntity apiEntity);

	Result<?> apiAllList(QueryApiEntity apiEntity);


}
