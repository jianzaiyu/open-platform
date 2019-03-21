package cn.ce.service.openapi.base.openApply.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import cn.ce.service.openapi.base.common.MongoFiledConstants;

/**
 * 
 * @ClassName: AppEntity
 * @Description: 服务分组类型
 * @author dingjia@300.cn
 *
 */
public class OpenApplyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 唯一标识 */
	@Id
	private String id; // 对应接口应用code 2， 开发者在开放平台发布应用审核

	private String appId; // 对应接口返回应用id 2， 开发者在开放平台发布应用审核

	/** 提供者标识 */
	private String userId;

	/** 提供者姓名 */
	private String userName;

	private String enterpriseName; // 企业名称

	private String applyKey;

	private String applyIcon; //图标

	/** 开放应用名称 */
	private String applyName;

	/** 服务描述 */
	private String applyDesc;

	private Date createDate;

	/** 审核状态0:初始，1:提交审核，2:通过，3:未通过 */
	private Integer checkState;

	/** 审核备注 */
	private String checkMem;

	/** dao判断刨除当前修改id的数据进行判断时赋值 */
	@Transient
	private String neqId;

	public OpenApplyEntity(){}


	public OpenApplyEntity(String applyId, String appId, String applyKey, String applyIcon, String applyName, String applyDesc, int checkState, Date createDate) {
		this.id = applyId;
		this.appId = appId;
		this.applyKey = applyKey;
		this.applyIcon = applyIcon;
		this.applyName = applyName;
		this.applyDesc = applyDesc;
		this.checkState = checkState;
		this.createDate = createDate;
	}

	public String getApplyIcon() {
		return applyIcon;
	}

	public void setApplyIcon(String applyIcon) {
		this.applyIcon = applyIcon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getApplyKey() {
		return applyKey;
	}

	public void setApplyKey(String applyKey) {
		this.applyKey = applyKey;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getNeqId() {
		return neqId;
	}

	public void setNeqId(String neqId) {
		this.neqId = neqId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "DevApplyEntity [id=" + id + ", userId=" + userId + ", userName=" + userName + ", applyKey=" + applyKey
				+ ", applyName=" + applyName + ", createDate=" + createDate + ", checkState=" + checkState
				+ ", checkMem=" + checkMem + ", neqId=" + neqId + "]";
	}

}
