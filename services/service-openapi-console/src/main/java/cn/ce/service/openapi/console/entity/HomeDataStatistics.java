package cn.ce.service.openapi.console.entity;

/**
 * @ClassName: HomeDataStatistics
 * @Description: TODO
 * @create 2019/1/8 15:27/MKW
 * @update 2019/1/8 15:27/MKW/(说明。)....多次修改添加多个update
 */
public class HomeDataStatistics {

    private int api = 0; //api总数

    private int openApply = 0; //开放应用数量

    private int diyApply = 0;  //定制应用数量

    private int application = 0; //应用总数（含有类型为open的api的开放应用+定制应用）

    private long apiAccess = 0; //api访问总量


    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public int getApplication() {
        return application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public long getApiAccess() {
        return apiAccess;
    }

    public void setApiAccess(long apiAccess) {
        this.apiAccess = apiAccess;
    }

    public int getOpenApply() {
        return openApply;
    }

    public void setOpenApply(int openApply) {
        this.openApply = openApply;
        this.application = openApply +diyApply;
    }

    public int getDiyApply() {
        return diyApply;
    }

    public void setDiyApply(int diyApply) {
        this.diyApply = diyApply;
        this.application = openApply + diyApply;
    }
}
