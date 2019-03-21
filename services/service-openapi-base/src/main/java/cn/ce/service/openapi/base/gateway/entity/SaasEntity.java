package cn.ce.service.openapi.base.gateway.entity;

import java.util.Date;

/**
 * 
 * @ClassName:  SaasEntity   
 * @Description:路由实体。将saas数据同步网关的saas数据
 * @author: makangwei 
 * @date:   2018年3月31日 上午10:01:49   
 * @Copyright: 2018 中企动力科技股份有限公司  1999-2017 300.cn 

 All Rights Reserved
 *
 */
public class SaasEntity {

	
	private String routeId;
	private String saasId;
	private String resourceType;
	private String targetUrl;
	private String sandboxId; //
	
	private Date createDate;
	
	private Date updateDate;
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public SaasEntity() {
		super();
	}
	
	public SaasEntity(String saasId, String resourceType, String targetUrl) {
		super();
		this.saasId = saasId;
		this.resourceType = resourceType;
		this.targetUrl = targetUrl;
	}
	
	public SaasEntity(String routeId, String saasId, String resourceType, String targetUrl) {
		super();
		this.routeId=routeId;
		this.saasId = saasId;
		this.resourceType = resourceType;
		this.targetUrl = targetUrl;
	}
	
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getSandboxId() {
		return sandboxId;
	}

	public void setSandboxId(String sandboxId) {
		this.sandboxId = sandboxId;
	}
	
}
