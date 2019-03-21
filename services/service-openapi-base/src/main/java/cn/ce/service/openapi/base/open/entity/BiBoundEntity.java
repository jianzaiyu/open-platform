package cn.ce.service.openapi.base.open.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
* @Description : 对接bi热度实体。开放应用，定制应用，api通用
* @Author : makangwei
* @Date : 2018年6月5日
*/
public class BiBoundEntity {

	private String AppCode; //对应开放应用id
	private Long Number; //访问量
	private String AppKey; //对应定制应用clientId
	private String apiID; //对应api的versionid
	@JSONField(format="yyyy-mm-dd'T'hh:MM:ss")
	private String TimeStamp; //时间戳
	public String getAppCode() {
		return AppCode;
	}
	public void setAppCode(String appCode) {
		AppCode = appCode;
	}
	public Long getNumber() {
		return Number;
	}
	public void setNumber(Long number) {
		Number = number;
	}
	public String getAppKey() {
		return AppKey;
	}
	public void setAppKey(String appKey) {
		AppKey = appKey;
	}
	public String getApiID() {
		return apiID;
	}
	public void setApiID(String apiID) {
		this.apiID = apiID;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "BiBoundEntity [AppCode=" + AppCode + ", Number=" + Number + ", AppKey=" + AppKey + ", apiID=" + apiID
				+ ", TimeStamp=" + TimeStamp + "]";
	}
}

