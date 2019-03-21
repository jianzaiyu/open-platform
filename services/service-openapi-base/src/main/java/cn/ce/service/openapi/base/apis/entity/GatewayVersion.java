package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : 添加pi回原地址后开放平台和网关版本交互实体
* @Author : makangwei
* @Date : 2018年3月19日
*/
public class GatewayVersion {

	private String version;
	
	private String listenPath;
	
	private String method;
	
	private String orgPath;

	public GatewayVersion(String version, String listenPath, String method, String orgPath) {
		super();
		this.version = version;
		this.listenPath = listenPath;
		this.method = method;
		this.orgPath = orgPath;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getListenPath() {
		return listenPath;
	}

	public void setListenPath(String listenPath) {
		this.listenPath = listenPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	
}

