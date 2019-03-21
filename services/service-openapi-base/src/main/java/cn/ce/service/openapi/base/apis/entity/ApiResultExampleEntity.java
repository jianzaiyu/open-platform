package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月19日
*/
public class ApiResultExampleEntity {

	private String id;
	private String apiId;
	private String rexName;
	private String rexType;
	private String stateCode;
	private String rexValue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getRexName() {
		return rexName;
	}
	public void setRexName(String rexName) {
		this.rexName = rexName;
	}
	public String getRexType() {
		return rexType;
	}
	public void setRexType(String rexType) {
		this.rexType = rexType;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getRexValue() {
		return rexValue;
	}
	public void setRexValue(String rexValue) {
		this.rexValue = rexValue;
	}
	@Override
	public String toString() {
		return "ApiResultExampleEntity [id=" + id + ", apiId=" + apiId + ", rexName=" + rexName + ", rexType=" + rexType
				+ ", stateCode=" + stateCode + ", rexValue=" + rexValue + "]";
	}
	
}
