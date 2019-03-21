package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月19日
*/
public class ApiHeaderEntity {
	
	private String id;
	
	private String apiId;
	
	private String headerName;
	
	private String headerType;
	
	private boolean required;
	
	private String example;
	
	private String headerDesc; //描述

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

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderType() {
		return headerType;
	}

	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getHeaderDesc() {
		return headerDesc;
	}

	public void setHeaderDesc(String headerDesc) {
		this.headerDesc = headerDesc;
	}

	@Override
	public String toString() {
		return "ApiHeaderEntity [id=" + id + ", apiId=" + apiId + ", headerName=" + headerName + ", headerType="
				+ headerType + ", required=" + required + ", example=" + example + ", headerDesc=" + headerDesc + "]";
	}
	
}
