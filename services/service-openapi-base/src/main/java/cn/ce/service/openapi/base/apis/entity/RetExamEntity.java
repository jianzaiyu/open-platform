package cn.ce.service.openapi.base.apis.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: RetExamEntity
 * @Description: 返回值示例类型
 * @author dingjia@300.cn
 *
 */
public class RetExamEntity implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 示例名称  */
    private String exName;
    /** 类型 */
    private String exType;
    /** 状态码 */
    private String stateCode;
    /** 值  */
    private String exValue;
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public String getExType() {
		return exType;
	}
	public void setExType(String exType) {
		this.exType = exType;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getExValue() {
		return exValue;
	}
	public void setExValue(String exValue) {
		this.exValue = exValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RetExamEntity [exName=" + exName + ", exType=" + exType + ", stateCode=" + stateCode + ", exValue="
				+ exValue + "]";
	}
    
}
