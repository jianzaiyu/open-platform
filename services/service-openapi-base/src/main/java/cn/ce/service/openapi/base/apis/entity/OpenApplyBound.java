package cn.ce.service.openapi.base.apis.entity;

import java.util.List;

/**
* @Description : 提供给bi，用于获取开放应用和api的的绑定关系
* @Author : makangwei
* @Date : 2018年5月27日
*/
public class OpenApplyBound {

	private String openApplyId;
	
	private List<ApiBound> apiIds;

	static class ApiBound{
		
		private String apiId; //对应开放平台api的version_id

		public String getApiId() {
			return apiId;
		}

		public void setApiId(String apiId) {
			this.apiId = apiId;
		}

		@Override
		public String toString() {
			return "ApiBound [apiId=" + apiId + "]";
		}
	}

	public String getOpenApplyId() {
		return openApplyId;
	}

	public void setOpenApplyId(String openApplyId) {
		this.openApplyId = openApplyId;
	}

	public List<ApiBound> getApiIds() {
		return apiIds;
	}

	public void setApiIds(List<ApiBound> apiIds) {
		this.apiIds = apiIds;
	}

	@Override
	public String toString() {
		return "OpenApplyBound [openApplyId=" + openApplyId + ", apiIds=" + apiIds + "]";
	}
	
}

