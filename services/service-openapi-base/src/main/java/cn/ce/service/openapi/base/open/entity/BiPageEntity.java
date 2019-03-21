package cn.ce.service.openapi.base.open.entity;

import java.util.List;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年6月5日
*/
public class BiPageEntity {

	private Integer pageNumber;
	
	private Integer itemPerPage;
	
	private Integer totalPage;
	
	private Integer totalCount;
	
	private List<BiBoundEntity> resultList;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(Integer itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<BiBoundEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<BiBoundEntity> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return "BiPageEntity [pageNumber=" + pageNumber + ", itemPerPage=" + itemPerPage + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", resultList=" + resultList + "]";
	}
}

