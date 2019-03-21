package cn.ce.service.openapi.base.common.gateway;

/**
 * api与网关交互的实体,密钥授权
 * @author makangwei
 * @time 2017-7-31
 */
public class ApiGatewayEntity {

	
//	{
//		"name":"test",
//		"api_id":#apiid,
//		"org_id":#userid,
//		"active":true,
//		"proxy":{
//			"listen_path":"/test7/",
//			"target_url":"http://www.300.cn/",
//			"strip_listen_path":true
//		},
//		"version_data":{
//			"not_versioned":true,
//			"versions":{
//				"Default":{
//					"name":"Default"
//				}
//			}
//		},
//		"use_keyless":false,
//    "auth": {
//   	 "auth_header_name": "Authentication"
//	  }
//	}
	
	private String name; //接口名称
	
	private String api_id; //接口id
	
	private String org_id; //上传接口的用户的id
	
	private boolean active = true;
	
	private Proxy proxy = new Proxy();
	
	private VersionData version_data = new VersionData();
	
	private boolean use_keyless = false;//true表示不使用密钥，false表示使用密钥

	private Auth auth = new Auth();
	
	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApi_id() {
		return api_id;
	}

	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public VersionData getVersion_data() {
		return version_data;
	}

	public void setVersion_data(VersionData version_data) {
		this.version_data = version_data;
	}

	public boolean isUse_keyless() {
		return use_keyless;
	}

	public void setUse_keyless(boolean use_keyless) {
		this.use_keyless = use_keyless;
	}

	@Override
	public String toString() {
		return "ApiGatewayEntity [name=" + name + ", api_id=" + api_id + ", org_id=" + org_id + ", active=" + active
				+ ", proxy=" + proxy + ", version_data=" + version_data + ", use_keyless=" + use_keyless + ", auth="
				+ auth + "]";
	}
	
}


// Proxy
class Proxy{
	
	private String listen_path; //网关上的服务名称
	
	private String target_url; //api真实访问地址
	
	private boolean strip_listen_path = true;

	public String getListen_path() {
		return listen_path;
	}

	public void setListen_path(String listen_path) {
		this.listen_path = listen_path;
	}

	public String getTarget_url() {
		return target_url;
	}

	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}
	
	

	public boolean isStrip_listen_path() {
		return strip_listen_path;
	}

	public void setStrip_listen_path(boolean strip_listen_path) {
		this.strip_listen_path = strip_listen_path;
	}

	@Override
	public String toString() {
		return "Proxy [listen_path=" + listen_path + ", target_url="
				+ target_url + ", strip_listen_path=" + strip_listen_path + "]";
	}
	
}


// VersionData
class VersionData{
	
	private boolean not_versioned = true;
	
	private Versions versions = new Versions();

	public boolean isNot_versioned() {
		return not_versioned;
	}

	public void setNot_versioned(boolean not_versioned) {
		this.not_versioned = not_versioned;
	}

	public Versions getVersions() {
		return versions;
	}

	public void setVersions(Versions versions) {
		this.versions = versions;
	}

	@Override
	public String toString() {
		return "VersionData [not_versioned=" + not_versioned + ", versions="
				+ versions + "]";
	}
	
	
}

// Versions
class Versions{
	
	private Default Default = new Default();

	public Default getDefault() {
		return Default;
	}

	public void setDefault(Default default1) {
		Default = default1;
	}

	@Override
	public String toString() {
		return "Versions [Default=" + Default + "]";
	}
	
	
}


// Default
class Default{
	
	private String name = "Default";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Default [name=" + name + "]";
	}
	
}

class Auth {
	private String auth_header_name = "Authentication";

	public String getAuth_header_name() {
		return auth_header_name;
	}

	public void setAuth_header_name(String auth_header_name) {
		this.auth_header_name = auth_header_name;
	}

	@Override
	public String toString() {
		return "Auth [auth_header_name=" + auth_header_name + "]";
	}
	
	
}



