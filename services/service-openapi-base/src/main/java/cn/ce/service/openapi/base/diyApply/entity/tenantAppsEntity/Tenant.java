/**
  * Copyright 2017 bejson.com 
  */
package cn.ce.service.openapi.base.diyApply.entity.tenantAppsEntity;

import java.util.List;

/**
 * Auto-generated: 2017-10-19 14:44:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tenant {

	private String id;
	private String name;
	private String tenantCode;
	private String tennatCode;
	private String cdnFlag;
	private String language;
	private String productDomain;
	private String mobileProductDomain;
	private String mobileMakeDomain;
	private String domain;
	private String makeDomain;
	private String mobileDomain;
	private String mobileStatus;
	private String pcPublishTime;
	private String mobilePublishTime;
	private String productInstanceId;
	private String appList;
	private ProductInstance productInstance;

	public ProductInstance getProductInstance() {
		return productInstance;
	}

	public void setProductInstance(ProductInstance productInstance) {
		this.productInstance = productInstance;
	}

	public List<InstanceList> getInstanceList() {
		return instanceList;
	}

	public void setInstanceList(List<InstanceList> instanceList) {
		this.instanceList = instanceList;
	}

	private List<InstanceList> instanceList;
	private String productAttrId;
	private Attr attr;

	private String productInstanceCode;
	private String unittype;
	private String templateCode;
	private String youyiProductName;
	private List<Object> domains;
	private String ningkerDomain;
	private String ningkerCusdomain;
	private String hotelDomain;
	private String hotelCusdomain;
	private String domainStatus;
	private String status;
	private String domainInfo;
	private boolean ceYun;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTennatCode(String tennatCode) {
		this.tennatCode = tennatCode;
	}

	public String getTennatCode() {
		return tennatCode;
	}

	public void setCdnFlag(String cdnFlag) {
		this.cdnFlag = cdnFlag;
	}

	public String getCdnFlag() {
		return cdnFlag;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setProductDomain(String productDomain) {
		this.productDomain = productDomain;
	}

	public String getProductDomain() {
		return productDomain;
	}

	public void setMobileProductDomain(String mobileProductDomain) {
		this.mobileProductDomain = mobileProductDomain;
	}

	public String getMobileProductDomain() {
		return mobileProductDomain;
	}

	public void setMobileMakeDomain(String mobileMakeDomain) {
		this.mobileMakeDomain = mobileMakeDomain;
	}

	public String getMobileMakeDomain() {
		return mobileMakeDomain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public void setMakeDomain(String makeDomain) {
		this.makeDomain = makeDomain;
	}

	public String getMakeDomain() {
		return makeDomain;
	}

	public void setMobileDomain(String mobileDomain) {
		this.mobileDomain = mobileDomain;
	}

	public String getMobileDomain() {
		return mobileDomain;
	}

	public void setMobileStatus(String mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public String getMobileStatus() {
		return mobileStatus;
	}

	public void setPcPublishTime(String pcPublishTime) {
		this.pcPublishTime = pcPublishTime;
	}

	public String getPcPublishTime() {
		return pcPublishTime;
	}

	public void setMobilePublishTime(String mobilePublishTime) {
		this.mobilePublishTime = mobilePublishTime;
	}

	public String getMobilePublishTime() {
		return mobilePublishTime;
	}

	public void setProductInstanceId(String productInstanceId) {
		this.productInstanceId = productInstanceId;
	}

	public String getProductInstanceId() {
		return productInstanceId;
	}

	public void setAppList(String appList) {
		this.appList = appList;
	}

	public String getAppList() {
		return appList;
	}

	public void setProductAttrId(String productAttrId) {
		this.productAttrId = productAttrId;
	}

	public String getProductAttrId() {
		return productAttrId;
	}

	public void setAttr(Attr attr) {
		this.attr = attr;
	}

	public Attr getAttr() {
		return attr;
	}

	public void setProductInstanceCode(String productInstanceCode) {
		this.productInstanceCode = productInstanceCode;
	}

	public String getProductInstanceCode() {
		return productInstanceCode;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getUnittype() {
		return unittype;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setYouyiProductName(String youyiProductName) {
		this.youyiProductName = youyiProductName;
	}

	public String getYouyiProductName() {
		return youyiProductName;
	}

	public List<Object> getDomains() {
		return domains;
	}

	public void setDomains(List<Object> domains) {
		this.domains = domains;
	}

	public void setNingkerDomain(String ningkerDomain) {
		this.ningkerDomain = ningkerDomain;
	}

	public String getNingkerDomain() {
		return ningkerDomain;
	}

	public void setNingkerCusdomain(String ningkerCusdomain) {
		this.ningkerCusdomain = ningkerCusdomain;
	}

	public String getNingkerCusdomain() {
		return ningkerCusdomain;
	}

	public void setHotelDomain(String hotelDomain) {
		this.hotelDomain = hotelDomain;
	}

	public String getHotelDomain() {
		return hotelDomain;
	}

	public void setHotelCusdomain(String hotelCusdomain) {
		this.hotelCusdomain = hotelCusdomain;
	}

	public String getHotelCusdomain() {
		return hotelCusdomain;
	}

	public void setDomainStatus(String domainStatus) {
		this.domainStatus = domainStatus;
	}

	public String getDomainStatus() {
		return domainStatus;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDomainInfo(String domainInfo) {
		this.domainInfo = domainInfo;
	}

	public String getDomainInfo() {
		return domainInfo;
	}

	public void setCeYun(boolean ceYun) {
		this.ceYun = ceYun;
	}

	public boolean getCeYun() {
		return ceYun;
	}

}