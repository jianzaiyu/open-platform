package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : api返回状态码实体
* @Author : makangwei
* @Date : 2018年1月19日
*/
public class ApiCodeEntity {

	private String id;
	private String apiId;
	private String codeName;
	private String codeDesc;

	public ApiCodeEntity(){
		super();
	}
	
	public ApiCodeEntity(String id, String apiId, String codeName, String codeDesc) {
		super();
		this.id = id;
		this.apiId = apiId;
		this.codeName = codeName;
		this.codeDesc = codeDesc;
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

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	@Override
	public String toString() {
		return "ApiCodeEntity [id=" + id + ", apiId=" + apiId + ", codeName=" + codeName + ", codeDesc=" + codeDesc
				+ ", getId()=" + getId() + ", getApiId()=" + getApiId() + ", getCodeName()=" + getCodeName()
				+ ", getCodeDesc()=" + getCodeDesc() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
}
