package cn.ce.service.openapi.base.diyApply.entity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月25日
*/
public class DiyBoundApi {

	private String id;
	
	private String diyApplyId;
	
	private String openApplyId;
	
	private String apiId;
	
	private String boundOpenId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDiyApplyId() {
		return diyApplyId;
	}

	public void setDiyApplyId(String diyApplyId) {
		this.diyApplyId = diyApplyId;
	}

	public String getOpenApplyId() {
		return openApplyId;
	}

	public void setOpenApplyId(String openApplyId) {
		this.openApplyId = openApplyId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getBoundOpenId() {
		return boundOpenId;
	}

	public void setBoundOpenId(String boundOpenId) {
		this.boundOpenId = boundOpenId;
	}
}
