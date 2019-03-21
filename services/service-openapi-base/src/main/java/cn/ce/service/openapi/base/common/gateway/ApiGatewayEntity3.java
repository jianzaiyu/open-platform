package cn.ce.service.openapi.base.common.gateway;

import java.util.Map;

import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;

/**
* @Description : 添加api到网关，使用oauth授权,多版本
* @Author : makangwei
* @Date : 2017年8月21日
*/
public class ApiGatewayEntity3 {
//	{
//		"name": "OAUTH Test API",
//		"api_id": "999999",
//		"org_id": "default",
//		"use_oauth2": true,
//		"oauth_meta": {
//			"allowed_access_types": [
//				"authorization_code",
//				"refresh_token",
//				"client_credentials"
//			],
//			"allowed_authorize_types": [
//				"code",
//				"token"
//			],
//			"auth_login_redirect": ""
//		},
//		"notifications": {
//			"shared_secret": "9878767657654343123434556564444",
//			"oauth_on_keychange_url": ""
//		},
//		"version_data": {
//			"not_versioned": false,
//			"versions": {
//	           "v1": {
//					"name": "v1",
//					"expires": "-1",
//					"paths": {
//						"ignored": [],
//						"white_list": [],
//						"black_list": []
//					},
//					"use_extended_paths": true,
//					"extended_paths": {},
//					"global_headers": {},
//					"global_headers_remove": [],
//					"global_size_limit": 0,
//					"override_target": "http://172.20.1.43/common/test/"
//				}
//			}
//		},
//		"proxy": {
//			"listen_path": "/oauth-test-api/",
//			"target_url": "http://httpbin.org/ip",
//			"strip_listen_path": true
//		}
//	}
	public static JSONObject getApiGatewayEntity3(String name,
			String apiId,
			String listenPath,
			Map<String,String> versionUrl,
			boolean notVersioned
			){
		
		JSONObject job = new JSONObject();
//		----------------basic-------------------------		
		job.put("name", name);
		job.put("api_id", apiId);
		job.put("org_id", "default");
		job.put("use_oauth2", true);

//		----------------oauth_meta---------------------
		JSONObject oauthMeta = new JSONObject();
		JSONArray allowedAccessTypes = new JSONArray();
		allowedAccessTypes.add("authorization_code");
		allowedAccessTypes.add("refresh_token");
		allowedAccessTypes.add("client_credentials");
		JSONArray allowedAuthorizeTypes = new JSONArray();
		allowedAuthorizeTypes.add("code");
		allowedAuthorizeTypes.add("token");
		oauthMeta.put("allowed_access_types", allowedAccessTypes);
		oauthMeta.put("allowed_authorize_types", allowedAuthorizeTypes);
		oauthMeta.put("auth_login_redirect", "");
		job.put("oauth_meta", oauthMeta);

//		----------------notifications-------------------------	
		JSONObject notifications = new JSONObject();
		notifications.put("shared_secret", "9878767657654343123434556564444");
		notifications.put("oauth_on_keychange_url", "");
		job.put("notifications", notifications);
		
//		---------------definition------------------------------
		JSONObject definition = new JSONObject();
		definition.put("location", "url");
		definition.put("key", "");
		job.put("definition", definition);
		
//		---------------version_data-------------------------
		JSONObject versionData = new JSONObject();
		versionData.put("not_versioned", false);
		JSONObject versions = new JSONObject();
		for (String key : versionUrl.keySet()) {
			JSONObject newVersion = new JSONObject();
			newVersion.put("name", key);
			newVersion.put("expires", "-1");
			newVersion.put("use_extended_paths", true);
			JSONObject extendedPaths = new JSONObject();
			newVersion.put("extended_paths", extendedPaths);
			JSONObject globalHeaders = new JSONObject();
			newVersion.put("global_headers", globalHeaders);
			JSONArray globalHeadersRemove = new JSONArray();
			newVersion.put("global_headers_remove", globalHeadersRemove);
			newVersion.put("global_size_limit", 0);
			newVersion.put("override_target", versionUrl.get(key));
			JSONObject paths = new JSONObject();
			JSONArray ignored = new JSONArray();
			JSONArray whiteList = new JSONArray();
			JSONArray blackList = new JSONArray();
			paths.put("ignored", ignored);
			paths.put("white_list", whiteList);
			paths.put("black_list", blackList);
			newVersion.put("paths", paths);
			versions.put(key, newVersion);
		}
		versionData.put("versions", versions);
		job.put("version_data", versionData);
		
//		---------------proxy-------------------------		
		JSONObject proxy = new JSONObject();
		proxy.put("listen_path", listenPath);
		proxy.put("target_url", "default");
		proxy.put("strip_listen_path", true);
		job.put("proxy", proxy);
		
		return job;
	}
	
}
