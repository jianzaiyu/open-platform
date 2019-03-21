package cn.ce.service.openapi.base.apis.entity;

import java.util.List;

/**
* @Description : 提供给bi，用于获取定制应用下有权限访问的开放应用和该定制应用有权限访问的api的的绑定关系
* @Author : makangwei
* @Date : 2018年5月27日
*/
public class DiyApplyBound {

	private String diyApplyId;//对应开放平台diyApply的client_id
	
	private List<OpenApplyBound> openApplyIds;

	public String getDiyApplyId() {
		return diyApplyId;
	}

	public void setDiyApplyId(String diyApplyId) {
		this.diyApplyId = diyApplyId;
	}

	public List<OpenApplyBound> getOpenApplyIds() {
		return openApplyIds;
	}

	public void setOpenApplyIds(List<OpenApplyBound> openApplyIds) {
		this.openApplyIds = openApplyIds;
	}
	
}

