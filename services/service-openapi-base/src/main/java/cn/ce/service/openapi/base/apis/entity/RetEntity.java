package cn.ce.service.openapi.base.apis.entity;

import java.io.Serializable;


/**
 * 
 * @ClassName: RetEntity
 * @Description: 返回描述
 * @author dingjia@300.cn
 *
 */
public class RetEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 返回值名称 */
    private String retName;
    /** 类型 */
    private String retType;
    /** 示例 */
    private String example;
    /** 说明  */
    private String desc;
	public String getRetName() {
		return retName;
	}
	public void setRetName(String retName) {
		this.retName = retName;
	}
	public String getRetType() {
		return retType;
	}
	public void setRetType(String retType) {
		this.retType = retType;
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
		return "RetEntity [retName=" + retName + ", retType=" + retType + ", example=" + example + ", desc=" + desc
				+ "]";
	}
  
}
