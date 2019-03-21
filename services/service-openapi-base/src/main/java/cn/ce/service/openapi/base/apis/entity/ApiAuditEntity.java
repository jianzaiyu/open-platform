package cn.ce.service.openapi.base.apis.entity;

import java.io.Serializable;
import java.util.Date;


/**
* @Description : api使用权限entity
* @Author : makangwei
* @Date : 2017年8月18日
*/
public class ApiAuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String apiId; //每个具体的api的apiid，是每个api的唯一标识
	private String versionApiId; //版本相关的apiid，版本不同，其他相同的一类api拥有相同的versionApiId;
	private String apiEnName;
	private String apiChName;
	private String version;
	private String appId;
	private String appKey;
	private String appName;
	private String clientId;
	private String secret;
	private Integer checkState; //0：未审核，1：提交审核，2：审核成功，3：审核失败，4：使用者删除调用
	private String userId;
	private String userName;
	private String applyId;
	private String applyName;
	private String supplierId;
	private Integer rate;
	private Integer per;
	private Integer quotaMax;
	private Integer quotaRenewalRate;
	private Date applyTime;
	private String checkMem; //审核失败原因
	
	private ApiEntity apiEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getVersionApiId() {
		return versionApiId;
	}

	public void setVersionApiId(String versionApiId) {
		this.versionApiId = versionApiId;
	}

	public String getApiEnName() {
		return apiEnName;
	}

	public void setApiEnName(String apiEnName) {
		this.apiEnName = apiEnName;
	}

	public String getApiChName() {
		return apiChName;
	}

	public void setApiChName(String apiChName) {
		this.apiChName = apiChName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getPer() {
		return per;
	}

	public void setPer(Integer per) {
		this.per = per;
	}

	public Integer getQuotaMax() {
		return quotaMax;
	}

	public void setQuotaMax(Integer quotaMax) {
		this.quotaMax = quotaMax;
	}

	public Integer getQuotaRenewalRate() {
		return quotaRenewalRate;
	}

	public void setQuotaRenewalRate(Integer quotaRenewalRate) {
		this.quotaRenewalRate = quotaRenewalRate;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getCheckMem() {
		return checkMem;
	}

	public void setCheckMem(String checkMem) {
		this.checkMem = checkMem;
	}

	public ApiEntity getApiEntity() {
		return apiEntity;
	}

	public void setApiEntity(ApiEntity apiEntity) {
		this.apiEntity = apiEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
