package cn.ce.service.openapi.base.apis.entity;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: ApiMock
 * @Description: TODO
 * @create 2018/8/23 14:57/MKW
 * @update 2018/8/23 14:57/MKW/(说明。)....多次修改添加多个update
 */
public class ApiMock {

    private String mockId;

    private String versionId;

    private String action; //对应网关的reply字段。暂时先不用。只是为了后期可扩展

    private int code;

    private Map headers;

    private Object mock;

    private String mockStr;

    private String headerStr;

    private Date createTime;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getMockStr() {
        return mockStr;
    }

    public void setMockStr(String mockStr) {
        this.mockStr = mockStr;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map getHeaders() {
        return headers;
    }

    public void setHeaders(Map headers) {
        this.headers = headers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeaderStr() {
        return headerStr;
    }

    public void setHeaderStr(String headerStr) {
        this.headerStr = headerStr;
    }

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId;
    }

    public Object getMock() {
        return mock;
    }

    public void setMock(Object mock) {
        this.mock = mock;
    }
}
