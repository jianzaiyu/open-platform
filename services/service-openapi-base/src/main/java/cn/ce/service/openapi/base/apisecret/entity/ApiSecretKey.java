package cn.ce.service.openapi.base.apisecret.entity;

import java.io.Serializable;
import java.util.Date;

import cn.ce.service.openapi.base.apis.entity.ApiEntity;

/**
 * 
 * @ClassName: ApiSecretKey
 * @Description: API秘钥实体
 * @author lida
 * @date 2017年8月9日16:06:55
 *
 */
public class ApiSecretKey implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	/**
	 * 秘钥
	 */
	private String secretKey;
	
	/***
	 * 申请人id
	 */
	private String userId;
	
	/***
	 * 申请人姓名
	 */
	private String userName;
	
	/***
	 * 申请时间
	 */
	private Date createDate;
	
	/***
	 * apiId
	 */
	private String apiId;
	
	 /** 审核状态0:初始，1:提交审核，2:通过，3:未通过*/
    private Integer checkState;
        
    /** 审核备注  */
    private String checkMem;
	
	private ApiEntity api;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public String getCheckMem() {
		return checkMem;
	}

	public void setCheckMem(String checkMem) {
		this.checkMem = checkMem;
	}

	public ApiEntity getApi() {
		return api;
	}

	public void setApi(ApiEntity api) {
		this.api = api;
	}

	
}
