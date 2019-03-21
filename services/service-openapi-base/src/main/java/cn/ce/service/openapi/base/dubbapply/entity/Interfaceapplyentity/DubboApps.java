/**
  * Copyright 2018 bejson.com 
  */
package cn.ce.service.openapi.base.dubbapply.entity.Interfaceapplyentity;

import java.util.List;

/**
 * Auto-generated: 2018-03-13 15:51:1
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DubboApps {

	private List<Data> data;
	private String msg;
	private String status;

	public void setData(List<Data> data) {
		this.data = data;
	}

	public List<Data> getData() {
		return data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}