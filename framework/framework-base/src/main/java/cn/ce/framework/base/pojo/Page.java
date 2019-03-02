package cn.ce.framework.base.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ggs
 * @date: 2018-08-13 14:20
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -5586133114941493116L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    protected long total;
    //结果集
    protected List list = new ArrayList<>();

    public Page(List<T> list) {
        fill(list);
    }

    public void fill(List list) {
        if (list != null) {
            this.list = list;
            if (list instanceof com.github.pagehelper.Page) {
                com.github.pagehelper.Page page = (com.github.pagehelper.Page) list;
                this.total = page.getTotal();
                this.pageNum = page.getPageNum();
                this.pageSize = page.getPageSize();
            } else {
                this.total = list.size();
                int start = (this.pageNum - 1) * this.pageSize;
                int end = this.pageNum * this.pageSize;
                if (start < 0) {
                    start = 0;
                }
                if (end < 0) {
                    end = 0;
                }
                if (start > list.size()) {
                    start = list.size();
                }
                if (end > list.size()) {
                    end = list.size();
                }
                this.list = list.subList(start, end);
            }
        }
    }

    public String RowKeyStart() {
        if (this.pageNum >= 1 && this.pageSize >= 1) {
            return hbaseRowKey((this.pageNum - 1) * this.pageSize + 1);
        } else {
            return "000000001";
        }
    }

    public String RowKeyend() {
        if (this.pageNum >= 1 && this.pageSize >= 1) {
            return hbaseRowKey(this.pageNum * this.pageSize + 1);
        } else {
            return "000000001";
        }
    }

    private String hbaseRowKey(int num) {
        String startStr = String.valueOf(num);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9 - startStr.length(); i++) {
            stringBuilder.append("0");
        }
        return stringBuilder.append(startStr).toString();
    }
}
