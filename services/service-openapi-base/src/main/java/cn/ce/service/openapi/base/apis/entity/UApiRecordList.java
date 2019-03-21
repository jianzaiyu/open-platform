package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : 文件下载绑定api的集合
* @Author : makangwei
* @Date : 2017年12月11日
*/
// download api bound entity
public class UApiRecordList {

	private int id;
	
	private String uploadId;
	
	private String apiChName;
	
	private String listenPath;
	
	private String apiType;
	
	private String openApplyId;
	
	private String appCode;
	
	private String appName;
	
	private String versionId;
	
	private String version;
	
	private boolean isDownLoadSuccess;
	
	private String errorDesc;
	
	public UApiRecordList(String uploadId, String apiChName, String listenPath, String apiType, String openApplyId
			, String appCode, String appName, String versionId, String version, boolean isDownLoadSuccess, String errorDesc){
		this.uploadId = uploadId;
		this.apiChName = apiChName;
		this.listenPath = listenPath;
		this.apiType = apiType;
		this.openApplyId = openApplyId;
		this.versionId = versionId;
		this.version = version;
		this.appCode = appCode;
		this.appName = appName;
		this.isDownLoadSuccess = isDownLoadSuccess;
		this.errorDesc = errorDesc;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApiChName() {
		return apiChName;
	}

	public void setApiChName(String apiChName) {
		this.apiChName = apiChName;
	}

	public String getListenPath() {
		return listenPath;
	}

	public void setListenPath(String listenPath) {
		this.listenPath = listenPath;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getOpenApplyId() {
		return openApplyId;
	}

	public void setOpenApplyId(String openApplyId) {
		this.openApplyId = openApplyId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public boolean isDownLoadSuccess() {
		return isDownLoadSuccess;
	}

	public void setDownLoadSuccess(boolean isDownLoadSuccess) {
		this.isDownLoadSuccess = isDownLoadSuccess;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

}
