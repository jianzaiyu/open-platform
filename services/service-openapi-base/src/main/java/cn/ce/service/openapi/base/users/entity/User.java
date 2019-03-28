package cn.ce.service.openapi.base.users.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E -mail: dingjia@300.cn 创建时间：2017年7月17日 下午3:53:13
 * @version V1.0 类说明
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 唯一标识 */
	private String id;
	/** 用户名称 */
	private String userName;
	/** 密码 */
	private String password;
	/** 邮箱 */
	private String email;
	/** 电话 */
	private String telNumber;

	/******************** 认证时需要的信息 ************************/
	private String enterpriseName; // 企业名称

	private String idCard; // 身份证号码

	private String userRealName; // 真实姓名

	/**
	 * 组织机构编码,标识当前用户的组织
	 */
	private String orgId;

	/** 组织机构名称 冗余字段 */
	private String orgName;

	/** 签名秘钥 */
	private String appSecret;

	/** 状态  0:禁用，1:启用*/
	private Integer state;
	/** 所属服务分类 */
	private String appId;
	/** 用户类型 0:管理员，1:普通用户，2:提供者 */
	private Integer userType;
	/** 注册时间 */
	private Date regTime;
	private Integer checkCode; // 短信校验码

	/** 审核状态0:初始，1:提交审核，2:通过，3:未通过 */
	private Integer checkState;

	/** 审核备注 */
	private String checkMem;

	/**
	 * mysql分页查询做聚合处理
	 */
	private Integer totalNumber;
	
	public User() {
		super();
	}

	public User(String id, String userName, String password, String email, String telNumber, Integer state, String appId,
			Integer userType, Date regTime, Integer checkState, String checkMem, String orgId, String appSecret) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.telNumber = telNumber;
		this.state = state;
		this.appId = appId;
		this.userType = userType;
		this.regTime = regTime;
		this.checkState = checkState;
		this.checkMem = checkMem;
		this.orgId = orgId;
		this.appSecret = appSecret;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Integer getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(Integer checkCode) {
		this.checkCode = checkCode;
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
	
	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", telNumber=" + telNumber + ", enterpriseName=" + enterpriseName + ", idCard=" + idCard
				+ ", userRealName=" + userRealName + ", orgId=" + orgId + ", orgName=" + orgName + ", appSecret="
				+ appSecret + ", state=" + state + ", appId=" + appId + ", userType=" + userType + ", regTime="
				+ regTime + ", checkCode=" + checkCode + ", checkState=" + checkState + ", checkMem=" + checkMem + "]";
	}
	
}
