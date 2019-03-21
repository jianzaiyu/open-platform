package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : api参数实体
* @Author : makangwei
* @Date : 2018年1月19日
*/
public class ApiArgEntity {

	private String id;
	private String apiId;
	private String argName;
	private String argType;
	private boolean required; 
	private String example;
	private String argDesc;
	private boolean imported; //对于query标识是否是导入的。对于arg标识是否已经导出	
	
	public boolean isImported() {
		return imported;
	}
	public void setImported(boolean imported) {
		this.imported = imported;
	}
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
	public String getArgName() {
		return argName;
	}
	public void setArgName(String argName) {
		this.argName = argName;
	}
	public String getArgType() {
		return argType;
	}
	public void setArgType(String argType) {
		this.argType = argType;
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
	public String getArgDesc() {
		return argDesc;
	}
	public void setArgDesc(String argDesc) {
		this.argDesc = argDesc;
	}
	
	@Override
	public String toString() {
		return "ApiArgEntity [id=" + id + ", apiId=" + apiId + ", argName=" + argName + ", argType=" + argType
				+ ", required=" + required + ", example=" + example + ", argDesc=" + argDesc + "]";
	}
}
