package cn.ce.service.openapi.base.apis.entity;

import java.util.List;

/**
* @Description : 文件导出list实体封装
* @Author : makangwei
* @Date : 2017年12月11日
*/
public class ApiExportParamEntity {

	private List<String> apiIds;
	
	private List<String> appIds;
	
	private Integer allFlag; //如果allFlag =1 代表导出数据库中所有审核通过的api

	public List<String> getApiIds() {
		return apiIds;
	}

	public void setApiIds(List<String> apiIds) {
		this.apiIds = apiIds;
	}

	public List<String> getAppIds() {
		return appIds;
	}

	public void setAppIds(List<String> appIds) {
		this.appIds = appIds;
	}

	public Integer getAllFlag() {
		return allFlag;
	}

	public void setAllFlag(Integer allFlag) {
		this.allFlag = allFlag;
	}

}
