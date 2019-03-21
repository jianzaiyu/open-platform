package cn.ce.service.openapi.base.apis.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: ErrorCodeEntity
 * @Description: 错误代码类型
 * @author dingjia@300.cn
 *
 */
public class ErrorCodeEntity implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 名称 */
    private String errname;
    /** 说明 */
    private String desc;
    
    public String getErrname() {
        return errname;
    }

    public void setErrname(String errname) {
        this.errname = errname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

	@Override
	public String toString() {
		return "ErrorCodeEntity [errname=" + errname + ", desc=" + desc + "]";
	}
    
}
