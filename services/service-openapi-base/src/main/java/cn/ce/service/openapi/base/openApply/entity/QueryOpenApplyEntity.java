package cn.ce.service.openapi.base.openApply.entity;

import cn.ce.service.openapi.base.util.PageValidateUtil;

public class QueryOpenApplyEntity {
	
	private String appName; //做兼容处理的字段，mysql上线后需要去掉，全部使用applyName

	private String applyName; //applyName
	
	private String userName;
	
	private String enterpriseName;
	
	private Integer checkState;

	private Integer currentPage = 1;
	
	private Integer pageSize = 10;
	
	private Integer startIndex = 0;
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		// set applyName
		this.applyName = appName;
	}

	public String getApplyName() {
		// return applyName
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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
		this.currentPage =PageValidateUtil.checkCurrentPage(currentPage);
		buildStartNum();
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = PageValidateUtil.checkPageSize(pageSize);
		buildStartNum();
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
	public void buildStartNum(){
		if(this.currentPage != null && this.currentPage > 0 
				&& this.pageSize != null && this.pageSize > 0){
			this.startIndex = (this.currentPage-1)*this.pageSize;
		}
	}
	
}
