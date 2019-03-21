package cn.ce.service.openapi.base.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Title: ListToPageUtil.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月23日 time上午9:46:13
 *
 **/
public class ListToPageUtil {
	// 分页
	public static ArrayList<Object> doPagination(int pagesize, int index, ArrayList<?> list) {
		ArrayList<Object> list2 = new ArrayList<Object>();
		try {
			int i = pagesize * (index - 1);
			while (i < pagesize * index) {
				if (i <= (list.size() - 1)) {
					Object dto = list.get(i);
					// 根据条件查询
					list2.add(dto);
				} else {
					break;
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list2;
	}
}
