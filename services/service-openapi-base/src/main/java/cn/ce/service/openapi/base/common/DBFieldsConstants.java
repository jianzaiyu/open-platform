package cn.ce.service.openapi.base.common;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年10月12日
*/
public class DBFieldsConstants {

	/*************** api相关字段 **********************/
	public static final String APIS_APIVERSION_VERSIONID = "apiVersion.versionId";
	public static final String APIS_APIVERSION_NEWVERSION = "apiVersion.newVersion"; 
	public static final String APIS_APIVERSION_VERSION = "apiVersion.version";
	public static final String APIS_APIENNAME = "apiEnName";
	public static final String APIS_APICHNAME = "apiChName";
	public static final String APIS_OPENAPPLY_ID = "openApplyId";
	public static final String APIS_VERSION= "version";
	public static final String APIS_ID = "id";
	public static final String APIS_USERID = "userId";
	public static final String APIS_CHECKSTATE = "checkState";
	public static final String APIS_CREATE_TIME = "createTime";
	public static final String APIS_APP_CODE = "appCode";
	public static final String APIS_APPID = "appId";
	public static final String APIS_LISTEN_PATH = "listenPath";
	public static final String APIS_API_TYPE = "apiType";
	public static final String API_TYPE_OPEN = "OPEN";
	public static final String API_TYPE_RATAIN = "RETAIN";
	public static final String API_ENTERPRESE_NAME = "enterpriseName";
	public static final String API_SOURCE="apiSource";
	
	/*************** user相关字段 **********************/
	public static final String USER_USERTYPE = "userType";
	public static final String USER_USERNAME = "userName";
	public static final String USER_EMAIL = "email";
	public static final String USER_TELNUMBER = "telNumber";
	public static final String USER_ENTERPRISE_NAME = "enterpriseName";
	public static final String USER_CHECKSTATE = "checkState";
	public static final String USER_STATE = "state";
	public static final String USER_REGTIME = "regTime";
	public static final String USER_ID_CARD = "idCard";
	public static final String USER_ID = "id";
	
	/*************** 网关相关字段 **********************/
	public static final String GW_API_NAME = "name";
	public static final String GW_API_ID = "api_id";
	public static final String GW_API_VERSIONS = "versions";
	public static final String GW_API_VERSIONS_NAME = "name";
	public static final String GW_API_VERSIONS_EXPIRES = "expires";
	public static final String GW_API_VERSIONS_OVERRIDE_TARGET = "override_target";
	public static final String GW_API_VERSIONS_USE_EXTENDED_PATHS = "use_extended_paths";
	public static final String GW_API_VERSIONS_EXTENDED_PATHS = "extended_paths";
	public static final String GW_API_VERSIONS_EXTENDED_PATHS_WHITELIST = "white_list";
	public static final String GW_API_VERSIONS_EXTENDED_PATHS_WHITELIST_PATH = "path";
	public static final String GW_API_VERSIONS_EXTENDED_PATHS_WHITELIST_METHODACTIONS = "method_actions";
	public static final String GW_API_VERSIONS_URL_REWRITES = "url_rewrites";
	public static final String GW_API_VERSIONS_URL_REWRITE_PATH = "path";
	public static final String GW_API_VERSIONS_URL_REWRITE_METHOD = "method";
	public static final String GW_API_VERSIONS_URL_REWRITE_PATTERN = "match_pattern";
	public static final String GW_API_VERSIONS_URL_REWRITE_REWRITE_TO = "rewrite_to";
	public static final String GW_API_PROXY_LISTENPATH = "listen_path";
	public static final String GW_API_PROXY_TARGETURL = "target_url";
	public static final String GW_API_PROXY = "proxy";
	public static final String GW_API_VERSION_DATA = "version_data";
	public static final String GW_API_RESOURCE_TYPE = "resource_type";
	public static final String GW_API_MOCK_VERSION = "mock";
	public static final String GW_API_MOCK_ACTIONS_ACTION = "action";
	public static final String GW_API_MOCK_ACTIONS_CODE = "code";
	public static final String GW_API_MOCK_ACTIONS_DATA = "data";
	public static final String GW_API_MOCK_ACTIONS_HEADERS = "headers";



	
	public static final String GW_POLICY_ID = "id";
	public static final String GW_POLICY_ID_FULL = "policy_id";
	public static final String GW_POLICY_RATE = "rate";
	public static final String GW_POLICY_PER = "per";
	public static final String GW_POLICY_QUOTA_MAX = "quota_max";
	public static final String GW_POLICY_QUOTA_RENEWAL_RATE = "quota_renewal_rate";
	public static final String GW_POLICY_ACCESS_RIGHTS = "access_rights";
	public static final String GW_POLICY_ACCESS_RIGHTS_APIID = "api_id";
	public static final String GW_POLICY_ACCESS_RIGHTS_APINAME = "api_name";
	public static final String GW_POLICY_ACCESS_RIGHTS_VERSIONS = "versions";
	public static final String GW_POLICY_ORG_ID = "org_id";
	
	public static final String GW_CLIENT_ID = "client_id";
	public static final String GW_SECRET= "secret";
	public static final String GW_REDIRECT_URI = "redirect_uri";
	
	public static final String GW_COL_ID = "colId";
	public static final String GW_COL_URL = "colUrl";
	
	public static final String GW_NODE_ID = "nodeId";
	public static final String GW_NODE_URL = "nodeUrl";

	/*************** 定制应用相关字段 **********************/
	public static final String DIY_APPLY_APPLYNAME = "applyName";
	public static final String DIY_APPLY_PRODUCTNAME = "productName";
	public static final String DIY_APPLY_CHECKSTATE = "checkState";
	public static final String DIY_APPLY_CREATEDATE = "createDate";
	
	/*************** 指南相关字段 **********************/
	public static final String GUIDE_NAME = "guideName";
	public static final String GUIDE_CREATE_USERNAME= "creatUserName";
	public static final String GUIDE_APPLYID = "applyId";
	public static final String GUIDE_CHECKSTATE = "checkState";
	
	
	/*************** dubbo **********************/
	public static final String DUBBO_NODE_ID = "nodeId";
	public static final String DUBBO_ROOT_ID = "rootId";

	/*************** HTTPMETHOD **********************/

}
