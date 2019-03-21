package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : api返回参数实体
* @Author : makangwei
* @Date : 2018年1月19日
*/
public class ApiResultEntity {

	private String id;
	private String apiId;
	private String retName;
	private String retType;
	private String example;
	private String desc;
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
	public String getRetName() {
		return retName;
	}
	public void setRetName(String retName) {
		this.retName = retName;
	}
	public String getRetType() {
		return retType;
	}
	public void setRetType(String retType) {
		this.retType = retType;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "ApiResultEntity [id=" + id + ", apiId=" + apiId + ", retName=" + retName + ", retType=" + retType
				+ ", example=" + example + ", desc=" + desc + "]";
	}

}
