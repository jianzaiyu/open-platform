package cn.ce.service.openapi.base.open.entity;

/**
* @Description : BiBoundEntity对应开放平台实体
* @Author : makangwei
* @Date : 2018年6月5日
*/
public class BiBoundEntity2 {
	private String openApplyId; //对应开放应用id
	private String clientId; //对应定制应用clientId
	private String versionId; //对应api的versionid
	private Long number; //访问量
	private String timeStamp; //时间戳
	public String getOpenApplyId() {
		return openApplyId;
	}
	public void setOpenApplyId(String openApplyId) {
		this.openApplyId = openApplyId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "BiBoundEntity2 [openApplyId=" + openApplyId + ", clientId=" + clientId + ", versionId=" + versionId
				+ ", number=" + number + ", timeStamp=" + timeStamp + "]";
	}
}

