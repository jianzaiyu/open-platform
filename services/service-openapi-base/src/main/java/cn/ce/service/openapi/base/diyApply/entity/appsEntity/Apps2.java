package cn.ce.service.openapi.base.diyApply.entity.appsEntity;

import java.util.List;

/**
 * @ClassName: Apps2
 * @Description: TODO
 * @create 2019/1/21 11:34/MKW
 * @update 2019/1/21 11:34/MKW/(说明。)....多次修改添加多个update
 */
public class Apps2 {
    private List<AppList> data;
    private String msg;
    private String status;
    public void setData(List<AppList> data) {
        this.data = data;
    }
    public List<AppList> getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Apps [data=" + data + ", msg=" + msg + ", status=" + status + "]";
    }

}
