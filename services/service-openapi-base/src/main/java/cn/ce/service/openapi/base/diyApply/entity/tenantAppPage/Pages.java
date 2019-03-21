/**
  * Copyright 2017 bejson.com 
  */
package cn.ce.service.openapi.base.diyApply.entity.tenantAppPage;

import java.util.List;

/**
 * Auto-generated: 2017-10-20 11:35:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Pages {

	private String pageSize;
	private String currentPage;
	private String totalPages;
	private String totalCount;
	private List<APPList> list;
	private Params params;

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setList(List<APPList> list) {
		this.list = list;
	}

	public List<APPList> getList() {
		return list;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public Params getParams() {
		return params;
	}

}