package cn.ce.service.openapi.base.apis.entity;

/***
 * 
 * api接口版本信息实体
 * @author lida
 * @date 2017年8月14日16:52:40
 *
 */
public class ApiVersion {

	private String versionId; //一组版本号不同其他相同的api拥有同一个versionId
	
	private String version ;//api版本号
	
	private String versionRemark;//版本说明
	
	private boolean newVersion;//是否为当前最新版本 true为是 false为否 

	
	public ApiVersion(){
		this.version = "第一版";
		this.newVersion = true;
	}
	



	public String getVersionId() {
		return versionId;
	}




	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}




	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionRemark() {
		return versionRemark;
	}

	public void setVersionRemark(String versionRemark) {
		this.versionRemark = versionRemark;
	}

	public boolean isNewVersion() {
		return newVersion;
	}

	public void setNewVersion(boolean newVersion) {
		this.newVersion = newVersion;
	}

	@Override
	public String toString() {
		return "ApiVersion [apiId=" + versionId + ", version=" + version + ", versionRemark=" + versionRemark
				+ ", newVersion=" + newVersion + "]";
	}
}
