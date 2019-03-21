package cn.ce.service.openapi.base.diyApply.entity.inparameter;

/**
 *
 * @Title: InParameterEntity.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月14日 time下午3:58:37
 *
 **/
public class RegisterBathAppInParameterEntity {

	private String appId;//应用appId   选填,注册已经存在的应用
	private String appName;//应用名称   必填 
	private String appUrl;//应用URL     选填
	private String appDesc;//应用描述    选填 
	private String appCatogroyId;//应用分类ID  选填，默认注册到定制的分类下
	private String appTag;// 模块标识     选填 模块标识      
	private String appCode;//app code     必填 此参数为 应用库对应关系不可重复 
	private String appType;//             必填 1 标品,2 
	private String owner;//所属企业标记   必填  中企动力默认为 CE    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getAppCatogroyId() {
		return appCatogroyId;
	}
	public void setAppCatogroyId(String appCatogroyId) {
		this.appCatogroyId = appCatogroyId;
	}
	public String getAppTag() {
		return appTag;
	}
	public void setAppTag(String appTag) {
		this.appTag = appTag;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}


}
