package cn.ce.service.openapi.base.common.gateway;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
* @Description : api与网关交互的实体,加了版本控制，采用密钥授权
* @Author : makangwei
* @Date : 2017年8月17日
*/
public class ApiGatewayEntity2 {

	
//	{
//	       "name": "test",
//	       "api_id": "2017081603",
//	       "org_id": "default",
//	       "active": true,
//	       "proxy": {
//				"listen_path": "/081603/",
//				"target_url": "http://baidu.com/",
//				"strip_listen_path": true
//	       },
//		   "definition": {
//				"location": "url",
//				"key": ""
//	       },
//	       "version_data": {
//				"not_versioned": false,
//			"versions": {
//				"v1": {
//					"name": "v1",
//					"expires": "2017-08-31 00:00",
//					"paths": {
//					"ignored": [],
//					"white_list": [],
//					"black_list": []
//				},
//				"use_extended_paths": true,
//				"extended_paths": {},
//				"global_headers": {},
//				"global_headers_remove": [],
//				"global_size_limit": 0,
//				"override_target": "http://172.23.151.15:8081/common/testVersion"
//				 },
//				"v2": {
//					"name": "v2",
//					"expires": "2017-08-31 00:00",
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
//					"override_target": "http://172.23.151.15:8081/common/testVersion/"
//				}
//			}
//		},
//		"use_keyless": false,
//		"auth": {
//			  "auth_header_name": "Authentication"
//		}
//	}

	/**
	 * 
	 * @Description : api和网关交互的实体。带有版本
	 * @Author : makangwei
	 * @Date : 2017年8月14日
	 * @param name api名称
	 * @param apiId
	 * @param listenPath
	 * @param verUrl
	 * @param notVersioned 
	 * @return
	 */
	public static JSONObject getApiGatewayEntity2(String name,String apiId, 
			String listenPath,Map<String,String> verUrl, boolean notVersioned){
		
		JSONObject job = new JSONObject();
		JSONObject proxy = new JSONObject();
		JSONObject definition = new JSONObject();
		JSONObject version_data = new JSONObject();
		JSONObject versions = new JSONObject();
		JSONObject auth = new JSONObject();
		
		for (String key : verUrl.keySet()) {
			
			JSONObject paths = new JSONObject();
			JSONArray ignored = new JSONArray();
			JSONArray whiteList = new JSONArray();
			JSONArray blackList = new JSONArray();
			JSONObject extendedPaths = new JSONObject();
			JSONObject globalHeaders = new JSONObject();
			JSONArray globalHeadersRemove = new JSONArray();
			
			paths.put("ignored", ignored);
			paths.put("white_list", whiteList);
			paths.put("black_list", blackList);
			
			JSONObject keyVersion = new JSONObject();
			keyVersion.put("name", key);
			keyVersion.put("expires", "-1");
			keyVersion.put("paths", paths);
			keyVersion.put("use_extended_paths", true);
			keyVersion.put("extended_paths", extendedPaths);
			keyVersion.put("global_headers", globalHeaders);
			keyVersion.put("global_headers_remove", globalHeadersRemove);
			keyVersion.put("global_size_limit", 0);
			keyVersion.put("override_target", verUrl.get(key));
			
			versions.put(key, keyVersion);
		}
		
		
		
		proxy.put("listen_path", listenPath);
		proxy.put("target_url", "default");	//地址将来会被替换为每个版本的真实地址
		proxy.put("strip_listen_path", true);
		
		definition.put("location", "url");
		definition.put("key", "");
		
		version_data.put("not_versioned", notVersioned);
		version_data.put("versions", versions);
		
		auth.put("auth_header_name", "Authentication");
		job.put("proxy", proxy);
		job.put("definition", definition);
		job.put("version_data", version_data);
		job.put("use_keyless", false);
		job.put("name", name);
		job.put("api_id", apiId);
		job.put("org_id", "default");
		job.put("active", true);
		job.put("auth", auth);
		
		return job;
	}
}
















