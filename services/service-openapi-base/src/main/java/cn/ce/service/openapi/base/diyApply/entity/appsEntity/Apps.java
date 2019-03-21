/**
  * Copyright 2017 bejson.com 
  */
package cn.ce.service.openapi.base.diyApply.entity.appsEntity;

/**
 * Auto-generated: 2017-10-13 14:32:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Apps {

    private Data data;
    private String msg;
    private String status;
    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Apps [data=" + data + ", msg=" + msg + ", status=" + status + "]";
	}

  

}