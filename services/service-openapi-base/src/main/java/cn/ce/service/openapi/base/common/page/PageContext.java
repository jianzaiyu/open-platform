package cn.ce.service.openapi.base.common.page;

/**
 * @ClassName:  PageContext   
 * @Description:
 * @author: makangwei 
 * @date:   2017年10月12日 下午7:32:34   
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 */
public class PageContext {

    /** 当前页 */
	public static ThreadLocal<Integer> cuurentPage = new ThreadLocal<Integer>();
	/** 页大小  */
	public static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/** 默认当前页编号 */
	public static final int defualtCuurentPage = 1; 
	/** 默认页大小 */
	public static final int defualtPageSize = 8; 
	
	public static Integer getCuurentPage() {
		if (cuurentPage.get() == null) {
			return defualtCuurentPage;
		}
		return cuurentPage.get();
	}
	
	public static Integer getPageSize() {
		if (pageSize.get() == null) {
			return defualtPageSize;
		}
		return pageSize.get();
	}
	
	public static void setCuurentPage(Integer value) {
		if (value == null) {
			cuurentPage.set(defualtCuurentPage);
			return;
		}
		cuurentPage.set(value);
	}
	
	public static void setPageSize(Integer value) {
		if (value == null) {
			pageSize.set(defualtPageSize);
			return;
		}
		pageSize.set(value);
	}

	public static void init(Integer cuurentPage2, Integer pageSize2) {
		setCuurentPage(cuurentPage2);
		setPageSize(pageSize2);
	}
}
