/**
  * Copyright 2017 bejson.com 
  */
package cn.ce.service.openapi.base.diyApply.entity.appsEntity;

import java.util.List;

/**
 * Auto-generated: 2017-10-13 14:32:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

	private String pageSize;
	private String currentPage;
	private String totalPages;
	private String totalCount;
	private List<AppList> list;
	private Params params;

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public void setList(List<AppList> list) {
		this.list = list;
	}

	public List<AppList> getList() {
		return list;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public Params getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "Data [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalPages=" + totalPages
				+ ", totalCount=" + totalCount + ", list=" + list + ", params=" + params + "]";
	}
	
	

}