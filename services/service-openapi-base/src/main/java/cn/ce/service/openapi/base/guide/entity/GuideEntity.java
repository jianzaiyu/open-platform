package cn.ce.service.openapi.base.guide.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @Title: Guide.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月12日 time下午5:49:59
 *
 **/
public class GuideEntity implements Serializable {

	private static final long serialVersionUID = -9010780710345447535L;
	private String id;
	private String guideName; // 指南名稱
	private String guideDesc; // 指南描述
	private String applyId; // 應用id
	private String creatUserName; // 創建人
	private Date creatTime;
	/** 审核状态0:初始，1:提交审核，2:通过，3:未通过 */
	private Integer checkState;
	/** 审核备注 */
	private String checkMem;

	public String getCheckMem() {
		return checkMem;
	}

	public void setCheckMem(String checkMem) {
		this.checkMem = checkMem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getGuideDesc() {
		return guideDesc;
	}

	public void setGuideDesc(String guideDesc) {
		this.guideDesc = guideDesc;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCreatUserName() {
		return creatUserName;
	}

	public void setCreatUserName(String creatUserName) {
		this.creatUserName = creatUserName;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

}
