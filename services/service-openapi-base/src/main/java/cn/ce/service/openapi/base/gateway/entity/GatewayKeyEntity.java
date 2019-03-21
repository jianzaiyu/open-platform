package cn.ce.service.openapi.base.gateway.entity;

import org.json.JSONObject;

/**
 * @Description : 说明
 * @Author : makangwei
 * @Date : 2017-8-9
 */
public class GatewayKeyEntity {

//    "quota_renewal_rate": 3600,
//    //quota_renewal_rate，Integer类型，访问最大配额的重置周期（单位秒）
//    "quota_remaining": 1000,
//    //quota_remaining，Integer类型，当前剩余访问最大配额
//    "quota_renews": 0,
//    //quota_renews，Integer类型，访问最大配额的重置时间戳（由Tyk自动填充）
//    "is_inactive": false,
//    //is_inactive，Boolean类型，密钥是否可用
//    "access_rights": {
//    //access_rights，json类型，权限列表
//           "2fdd8512a856434a61f080da67a88852": {
//           //API_ID
//                  "api_name": "test2",
//                  //api_name，API名称
//                  "api_id": "2fdd8512a856434a61f080da67a88852",
//                  //api_id，API_ID
//                  "versions": [
//                         //versions，允许的版本列表
//                         "Default"
//                  ]
//           }
//    }
//}
	private String org_id = "default"; //org_id，组织ID，String类型
	
	private long expires = -1; //expires，Integer类型，密钥过期时间（时间戳）
	
	private int rate; //rate，Integer类型，访问频次
	
	private int per = 1; //per，Integer类型，访问频次的计数周期（单位秒）
	
	private int quota_max; //quota_max，Integer类型，访问最大配额
	
	private int quota_renewal_rate; //Integer类型，访问最大配额的重置周期（单位秒）
	
	private int quota_remaining;
	
	private int quota_renews = 0;
	
	private boolean is_inactive = false; //Boolean类型，密钥是否可用true:不可用，false可用
	
	private JSONObject access_rights;

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}

	public int getQuota_max() {
		return quota_max;
	}

	public void setQuota_max(int quota_max) {
		this.quota_max = quota_max;
	}

	public int getQuota_renewal_rate() {
		return quota_renewal_rate;
	}

	public void setQuota_renewal_rate(int quota_renewal_rate) {
		this.quota_renewal_rate = quota_renewal_rate;
	}

	public int getQuota_remaining() {
		return quota_remaining;
	}

	public void setQuota_remaining(int quota_remaining) {
		this.quota_remaining = quota_remaining;
	}

	public int getQuota_renews() {
		return quota_renews;
	}

	public void setQuota_renews(int quota_renews) {
		this.quota_renews = quota_renews;
	}

	public boolean isIs_inactive() {
		return is_inactive;
	}

	public void setIs_inactive(boolean is_inactive) {
		this.is_inactive = is_inactive;
	}

	public JSONObject getAccess_rights() {
		return access_rights;
	}

	public void setAccess_rights(JSONObject job) {
		this.access_rights = job;
	}
	
	
}

