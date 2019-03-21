package cn.ce.service.openapi.base.apis.entity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年6月5日
*/
public class ApiEntityVO {

	private String apiChName;
	
	private String version;
	
	private Integer checkState;
	
	private String apiType; // 开放OPEN，保留RETAI
	
	private Integer apiSource; //api来源 0,null代表提供者录入1代表文件导入
	
	private String resourceType;
	
	private String resourceTypeName;
	
	private String desc;
	
	private Long count; //访问量

	public String getApiChName() {
		return apiChName;
	}

	public void setApiChName(String apiChName) {
		this.apiChName = apiChName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public Integer getApiSource() {
		return apiSource;
	}

	public void setApiSource(Integer apiSource) {
		this.apiSource = apiSource;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceTypeName() {
		return resourceTypeName;
	}

	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}