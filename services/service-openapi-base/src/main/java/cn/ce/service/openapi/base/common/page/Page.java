package cn.ce.service.openapi.base.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: Page
 * @Description: 分页类型
 * @author dingjia@300.cn 
 *
 * @param <T> 实例
 */
public class Page<T> implements Serializable {  
      
	private static final long serialVersionUID = 428978435560977887L;
	
    /** 每页显示条数 */  
    private Integer pageSize = 10;  
  
    /** 当前页 */  
    private Integer currentPage = 1;  
  
    /** 总页数 */  
    private Integer totalPage = 1;  
  
    /** 查询到的总数据量 */  
    private Integer totalNumber = 0;  
  
    /** 数据集 */  
    private List<T> items;  
  
    public Page(int currentPaget, int totalNumbert, int pageSizet) {
        
        this.setCurrentPage(currentPaget);
        this.setTotalNumber(totalNumbert);
        this.setPageSize(pageSizet);
        this.totalPage=this.totalNumber%this.pageSize == 0 ? 
        		this.totalNumber/this.pageSize : this.totalNumber/this.pageSize+1;
    }
    
    public Page(int currentPaget, int totalNumbert, int pageSizet, List<T> items) {
        
        this.setCurrentPage(currentPaget);
        this.setTotalNumber(totalNumbert);
        this.setPageSize(pageSizet);
        this.items = items;
        this.totalPage=this.totalNumber%this.pageSize == 0 ? 
        		this.totalNumber/this.pageSize : this.totalNumber/this.pageSize+1;
    }

    public Integer getPageSize() {  
  
        return pageSize;  
    }  
 
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
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

    public List<T> getItems() {
        return items;
    }

    public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public void build(List<T> titems) {  
        this.setItems(titems);  
        int count =  this.getTotalNumber();  
        int divisor = count / this.getPageSize();  
        int remainder = count % this.getPageSize();  
        this.setTotalPage(remainder == 0 ? divisor == 0 ? 1 : divisor : divisor + 1);  
    }

	public Page<T> buildPage(Integer currentPage, Integer pageSize, Integer totalNum, List<T> items) {
		
		// TODO 
		return null;
	}
	
}
