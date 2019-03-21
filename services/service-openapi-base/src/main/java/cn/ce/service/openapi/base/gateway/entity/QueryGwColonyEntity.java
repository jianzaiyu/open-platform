package cn.ce.service.openapi.base.gateway.entity;

import cn.ce.service.openapi.base.util.PageValidateUtil;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月30日
*/
public class QueryGwColonyEntity {
	
	private String colId; //集群id
	private String colName;	//集群名称	
	private String colUrl;	//集群代理域名(内网)
	private String wColUrl; //集群代理域名(外网)
	private Integer colStatus;	//集群状态 1可用，0禁用
	
	private Integer currentPage = 1;
	
	private Integer pageSize = 10;
	
	private Integer startNum = 0;

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColUrl() {
		return colUrl;
	}

	public void setColUrl(String colUrl) {
		this.colUrl = colUrl;
	}

	public String getwColUrl() {
		return wColUrl;
	}

	public void setwColUrl(String wColUrl) {
		this.wColUrl = wColUrl;
	}

	public Integer getColStatus() {
		return colStatus;
	}

	public void setColStatus(Integer colStatus) {
		this.colStatus = colStatus;
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
	
	public void buildStartNum(){
		if(this.currentPage != null && this.currentPage > 0 
				&& this.pageSize != null && this.pageSize > 0){
			this.startNum = (this.currentPage-1)*this.pageSize;
		}
	}
	
}
