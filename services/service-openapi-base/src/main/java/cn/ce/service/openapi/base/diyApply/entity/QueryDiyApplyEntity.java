package cn.ce.service.openapi.base.diyApply.entity;

import cn.ce.service.openapi.base.util.PageValidateUtil;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月24日
*/
public class QueryDiyApplyEntity {

	private String applyId;
	
	private String appId;
	
	private String applyName;
	
	private String productAuthCode;
	
	private String productName;
	
	private String userId;
	
	private String userName;
	
	private Integer checkState;
	
	private Integer currentPage = 1;
	
	private Integer pageSize = 10;
	
	private Integer startNum = 0; // 分页起始下标

	private String projectId;

	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public String getProductAuthCode() {
		return productAuthCode;
	}

	public void setProductAuthCode(String productAuthCode) {
		this.productAuthCode = productAuthCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = PageValidateUtil.checkCurrentPage(currentPage);
		buildStartNum();
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = PageValidateUtil.checkPageSize(pageSize);
		buildStartNum();
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void buildStartNum(){
		if(this.currentPage != null && this.currentPage > 0 
				&& this.pageSize != null && this.pageSize > 0){
			this.startNum = (this.currentPage-1)*this.pageSize;
		}
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
