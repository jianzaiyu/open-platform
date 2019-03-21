package cn.ce.service.openapi.base.diyApply.entity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年3月8日
*/
public class RetMenu extends Menu{

	private Integer sort_des;
	private Integer type;
	private Integer parentType;
	private String logicalId;
	private String locicalParentId;
	private String unit;
	private String action;
	public Integer getSort_des() {
		return sort_des;
	}
	public void setSort_des(Integer sort_des) {
		this.sort_des = sort_des;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentType() {
		return parentType;
	}
	public void setParentType(Integer parentType) {
		this.parentType = parentType;
	}
	public String getLogicalId() {
		return logicalId;
	}
	public void setLogicalId(String logicalId) {
		this.logicalId = logicalId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return "RetMenu [sort_des=" + sort_des + ", type=" + type + ", parentType=" + parentType + ", logicalId="
				+ logicalId + ", locicalParentId=" + locicalParentId + ", unit=" + unit + ", action=" + action + "]";
	}
}
