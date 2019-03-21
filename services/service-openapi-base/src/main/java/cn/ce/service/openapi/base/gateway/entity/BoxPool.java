package cn.ce.service.openapi.base.gateway.entity;

import java.util.Date;

/**
* @Description : 沙箱资源列表实体
* @Author : makangwei
* @Date : 2018年3月29日
*/
public class BoxPool {

	private String callBack;
	private String[] msgs;
	private Pool[] data;
	private int result;
	
	
	public String getCallBack() {
		return callBack;
	}
	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}
	public String[] getMsgs() {
		return msgs;
	}
	public void setMsgs(String[] msgs) {
		this.msgs = msgs;
	}
	public Pool[] getData() {
		return data;
	}
	public void setData(Pool[] data) {
		this.data = data;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}


	public static class Pool{
		private boolean upgrade;
		private String hclusterName;
		private String hclusterNmeAlias;
		private String resourcePool;
		private int status;
		private String type;
		private boolean deleted;
		private Date createTime;
		private int id;
		public boolean isUpgrade() {
			return upgrade;
		}
		public void setUpgrade(boolean upgrade) {
			this.upgrade = upgrade;
		}
		public String getHclusterName() {
			return hclusterName;
		}
		public void setHclusterName(String hclusterName) {
			this.hclusterName = hclusterName;
		}
		public String getHclusterNmeAlias() {
			return hclusterNmeAlias;
		}
		public void setHclusterNmeAlias(String hclusterNmeAlias) {
			this.hclusterNmeAlias = hclusterNmeAlias;
		}
		public String getResourcePool() {
			return resourcePool;
		}
		public void setResourcePool(String resourcePool) {
			this.resourcePool = resourcePool;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "Pool [upgrade=" + upgrade + ", hclusterName=" + hclusterName + ", hclusterNmeAlias="
					+ hclusterNmeAlias + ", resourcePool=" + resourcePool + ", status=" + status + ", type=" + type
					+ ", deleted=" + deleted + ", createTime=" + createTime + ", id=" + id + "]";
		}
		
	}
	
}

