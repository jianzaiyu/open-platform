package cn.ce.service.openapi.base.gateway.entity;

import cn.ce.service.openapi.base.util.PageValidateUtil;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月30日
*/
public class QueryGwNodeEntity {
	
	private String nodeId;
	private String nodeName;
	private String nodeUrl;
	private String colId; //所属集群的id
	private Integer nodeStatus;
	
	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private Integer startNum = 0;
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeUrl() {
		return nodeUrl;
	}
	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}
	public String getColId() {
		return colId;
	}
	public void setColId(String colId) {
		this.colId = colId;
	}
	public Integer getNodeStatus() {
		return nodeStatus;
	}
	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
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
