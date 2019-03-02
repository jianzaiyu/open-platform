package cn.ce.framework.base.pojo;



/**
 * @author ggs
 * @date 2018/8/5 11:09
 */

public enum ResultCode {

    //系统相关状态码
    SYS0000("系统正常")
    ,SYS0001("系统错误")
    ,SYS0002("业务错误");

    private String desc;

    ResultCode(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

    public String getCode(){
        return this.toString();
    }
}
