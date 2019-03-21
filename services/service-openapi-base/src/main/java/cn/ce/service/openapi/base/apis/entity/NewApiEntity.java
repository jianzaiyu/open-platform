package cn.ce.service.openapi.base.apis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月22日
*/
public class NewApiEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private String id;
	/**
	 * 所属功能分组的标识 
	 * important : 这里存放的是会员中心返回来的他们维护的开放应用的主键
	 */
	private String openApplyId;
	
	private String userId;
	
	private String userName;
	
	private String apiChName;
	
	/*所属开放应用的code码*/
	private String appCode;

	
	/** 接口地址  创建api的时候定义该地址。当saas-id找不到真实地址的时候就会访问该地址 */
	private String defaultTargetUrl;
	
	private String listenPath;
	
	private String protocol; //协议，http或者https
	
	private String orgPath; //回原地址，非必填
	/** http方法GET或POST */
	private String httpMethod;
	
	private List<ApiHeaderEntity> headers;
	private List<ApiArgEntity> args;
	private List<ApiArgEntity> queryArgs;
	private List<ApiResultEntity> result;
	private ApiResultExampleEntity retExample;
	private List<ApiCodeEntity> errCodes;
	
	/** api接口版本信息 */
//	private ApiVersion apiVersion;
	private String versionId; //一组版本号不同其他相同的api拥有同一个versionId
	private String version ;//api版本号
	private String versionRemark;//版本说明
	private boolean newVersion;//是否为当前最新版本 true为是 false为否 
	
	/** 开放OPEN，保留RETAIN*/
	private String apiType;
	
	/** 接口描述 */
	private String desc;
	
	/** 状态是否可用  默认为0,禁用为1*/
	private int state;
	
	 /** 审核状态0:初始，1:提交审核，2:通过，3:未通过*/
    private Integer checkState;
        
    /** 审核备注  */
    private String checkMem;
    
    /** 最大配额*/
    private int quotaMax;
    
    /** 最大配额 重置周期 */
    private int quotaRenewalRate;
    
    /** 频次  */
    private int rate;
    
    /** 访问频次计数周期  */
    private int per;
    
    private Date createTime;
    
    /**
     * api来源 0,null代表提供者录入
     * 1代表文件导入
     * */
    private Integer apiSource; 
    
    private String enterpriseName;
    
    
    /***
     * 资源类型
     */
    private String resourceType;
    
    /***
     * 资源类型名称
     */
    private String resourceTypeName;

    
    private String requestBodyType;  //请求body类型
    
    private String responseBodyType; //返回body类型
    /**mysql做查询时候的临时字段。只用来做查询时候用的，不具有业务意义*/
    private int count;
    
    public NewApiEntity(){
    	super();
    }
    
	public NewApiEntity(String id, String openApplyId, String userId, String userName, String apiChName, String appCode,
			String defaultTargetUrl, String listenPath, String httpMethod, List<ApiHeaderEntity> headers,
			List<ApiArgEntity> args, List<ApiResultEntity> result, ApiResultExampleEntity retExample,
			List<ApiCodeEntity> errCodes, String versionId, String version, String versionRemark, boolean newVersion,
			String apiType, String desc, int state, Integer checkState, String checkMem, int quotaMax,
			int quotaRenewalRate, int rate, int per, Date createTime, Integer apiSource, String enterpriseName,
			String resourceType, String resourceTypeName, int count) {
		super();
		this.id = id;
		this.openApplyId = openApplyId;
		this.userId = userId;
		this.userName = userName;
		this.apiChName = apiChName;
		this.appCode = appCode;
		this.defaultTargetUrl = defaultTargetUrl;
		this.listenPath = listenPath;
		this.httpMethod = httpMethod;
		this.headers = headers;
		this.args = args;
		this.result = result;
		this.retExample = retExample;
		this.errCodes = errCodes;
		this.versionId = versionId;
		this.version = version;
		this.versionRemark = versionRemark;
		this.newVersion = newVersion;
		this.apiType = apiType;
		this.desc = desc;
		this.state = state;
		this.checkState = checkState;
		this.checkMem = checkMem;
		this.quotaMax = quotaMax;
		this.quotaRenewalRate = quotaRenewalRate;
		this.rate = rate;
		this.per = per;
		this.createTime = createTime;
		this.apiSource = apiSource;
		this.enterpriseName = enterpriseName;
		this.resourceType = resourceType;
		this.resourceTypeName = resourceTypeName;
		this.count = count;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenApplyId() {
		return openApplyId;
	}

	public void setOpenApplyId(String openApplyId) {
		this.openApplyId = openApplyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApiChName() {
		return apiChName;
	}

	public void setApiChName(String apiChName) {
		this.apiChName = apiChName;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

	public String getListenPath() {
		return listenPath;
	}

	public void setListenPath(String listenPath) {
		this.listenPath = listenPath;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public List<ApiHeaderEntity> getHeaders() {
		return headers;
	}

	public void setHeaders(List<ApiHeaderEntity> headers) {
		this.headers = headers;
	}

	public List<ApiArgEntity> getArgs() {
		return args;
	}

	public void setArgs(List<ApiArgEntity> args) {
		this.args = args;
	}

	public List<ApiResultEntity> getResult() {
		return result;
	}

	public void setResult(List<ApiResultEntity> result) {
		this.result = result;
	}

	public ApiResultExampleEntity getRetExample() {
		return retExample;
	}

	public void setRetExample(ApiResultExampleEntity retExample) {
		this.retExample = retExample;
	}

	public List<ApiCodeEntity> getErrCodes() {
		return errCodes;
	}

	public void setErrCodes(List<ApiCodeEntity> errCodes) {
		this.errCodes = errCodes;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionRemark() {
		return versionRemark;
	}

	public void setVersionRemark(String versionRemark) {
		this.versionRemark = versionRemark;
	}

	public boolean isNewVersion() {
		return newVersion;
	}

	public void setNewVersion(boolean newVersion) {
		this.newVersion = newVersion;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public String getCheckMem() {
		return checkMem;
	}

	public void setCheckMem(String checkMem) {
		this.checkMem = checkMem;
	}

	public int getQuotaMax() {
		return quotaMax;
	}

	public void setQuotaMax(int quotaMax) {
		this.quotaMax = quotaMax;
	}

	public int getQuotaRenewalRate() {
		return quotaRenewalRate;
	}

	public void setQuotaRenewalRate(int quotaRenewalRate) {
		this.quotaRenewalRate = quotaRenewalRate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getApiSource() {
		return apiSource;
	}

	public void setApiSource(Integer apiSource) {
		this.apiSource = apiSource;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ApiArgEntity> getQueryArgs() {
		return queryArgs;
	}

	public void setQueryArgs(List<ApiArgEntity> queryArgs) {
		this.queryArgs = queryArgs;
	}

	public String getRequestBodyType() {
		return requestBodyType;
	}

	public void setRequestBodyType(String requestBodyType) {
		this.requestBodyType = requestBodyType;
	}

	public String getResponseBodyType() {
		return responseBodyType;
	}

	public void setResponseBodyType(String responseBodyType) {
		this.responseBodyType = responseBodyType;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	@Override
	public String toString() {
		return "NewApiEntity [id=" + id + ", openApplyId=" + openApplyId + ", userId=" + userId + ", userName="
				+ userName + ", apiChName=" + apiChName + ", appCode=" + appCode + ", defaultTargetUrl="
				+ defaultTargetUrl + ", listenPath=" + listenPath + ", protocol=" + protocol + ", orgPath=" + orgPath
				+ ", httpMethod=" + httpMethod + ", headers=" + headers + ", args=" + args + ", queryArgs=" + queryArgs
				+ ", result=" + result + ", retExample=" + retExample + ", errCodes=" + errCodes + ", versionId="
				+ versionId + ", version=" + version + ", versionRemark=" + versionRemark + ", newVersion=" + newVersion
				+ ", apiType=" + apiType + ", desc=" + desc + ", state=" + state + ", checkState=" + checkState
				+ ", checkMem=" + checkMem + ", quotaMax=" + quotaMax + ", quotaRenewalRate=" + quotaRenewalRate
				+ ", rate=" + rate + ", per=" + per + ", createTime=" + createTime + ", apiSource=" + apiSource
				+ ", enterpriseName=" + enterpriseName + ", resourceType=" + resourceType + ", resourceTypeName="
				+ resourceTypeName + ", requestBodyType=" + requestBodyType + ", responseBodyType=" + responseBodyType
				+ ", count=" + count + "]";
	}

}
