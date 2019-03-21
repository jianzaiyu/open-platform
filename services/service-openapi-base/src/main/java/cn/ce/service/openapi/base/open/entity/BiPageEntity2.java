package cn.ce.service.openapi.base.open.entity;

import java.util.ArrayList;
import java.util.List;

/**
* @Description : 将biBiPageEntity中的字段转化为开放平台对应字段
* @Author : makangwei
* @Date : 2018年6月5日
*/
public class BiPageEntity2 {

	private Integer currentPage;
	
	private Integer pageSize;
	
	private Integer totalPage;
	
	private Integer totalNumber;
	
	private List<BiBoundEntity2> resultList;

	public BiPageEntity2(){
		super();
	}
	
	/**
	 * 
	 * @Title: BiPageEntity2
	 * @Description: 将BiBoundEntity 集合转化为 BiBoundEntity2，为了和开放平台数据字段统一
	 * @author: makangwei 
	 * @date:   2018年6月5日 下午1:28:28 
	 * @param : @param biPage
	 * @throws
	 */
	public BiPageEntity2(BiPageEntity biPage){
		if(null == biPage){
			return ;
		}
		this.currentPage = biPage.getPageNumber();
		this.pageSize = biPage.getItemPerPage();
		this.totalNumber = biPage.getTotalCount();
		this.totalPage = biPage.getTotalPage();
		
		List<BiBoundEntity2> biBound2 = new ArrayList<BiBoundEntity2>();
		List<BiBoundEntity> biBound = biPage.getResultList();
		if(null != biBound && !biBound.isEmpty()){
			for (BiBoundEntity bd : biBound) {
				BiBoundEntity2 bd2 = new BiBoundEntity2();
				bd2.setVersionId(bd.getApiID());
				bd2.setOpenApplyId(bd.getAppCode());
				bd2.setClientId(bd.getAppKey());
				bd2.setNumber(bd.getNumber());
				bd2.setTimeStamp(bd.getTimeStamp());
				biBound2.add(bd2);
			}
		}
		this.resultList = biBound2;
	}
	
	public List<String> getVersionIds(){
		List<String> versions = new ArrayList<String>();
		if(null != resultList && resultList.size() > 0 ){
			for (BiBoundEntity2 biBoundEntity2 : resultList) {
				versions.add(biBoundEntity2.getVersionId());
			}
		}
		return versions;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List<BiBoundEntity2> getResultList() {
		return resultList;
	}

	public void setResultList(List<BiBoundEntity2> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return "BiPageEntity2 [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", totalNumber=" + totalNumber + ", resultList=" + resultList + "]";
	}
	
}

