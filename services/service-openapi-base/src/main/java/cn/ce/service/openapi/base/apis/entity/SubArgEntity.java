package cn.ce.service.openapi.base.apis.entity;

import java.io.Serializable;


/**
 * 
 * @ClassName: SubArgEntity
 * @Description: 应用参数类型
 * @author dingjia@300.cn
 *
 */
public class SubArgEntity  implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 名称 */
    private String argName;
    /** 类型 */
    private String argType;
    /** 是否必须 */
    private boolean required;
    /** 示例值 */
    private String example;
    /** 说明 */
    private String desc;
	public String getArgName() {
		return argName;
	}
	public void setArgName(String argName) {
		this.argName = argName;
	}
	public String getArgType() {
		return argType;
	}
	public void setArgType(String argType) {
		this.argType = argType;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SubArgEntity [argName=" + argName + ", argType=" + argType + ", required=" + required + ", example="
				+ example + ", desc=" + desc + "]";
	}
    
}
