package cn.ce.service.openapi.base.util.http;


import org.apache.http.Header;

/**
 * 
* @ClassName: HttpResult  
* @Description: http请求返回结果封装
* @create 2018年5月7日/makangwei  
* @update 2018年5月7日/makangwei/(说明。)....多次修改添加多个update   
*
 */
public class HttpResult {

	//http请求返回消息
	private String body;
	//http请求返回状态码
	private Integer status;
	//http请求返回头
	private Header[] headers;
	
	public HttpResult(){
		super();
	}
	
	public HttpResult(String body, Integer status){
		this.body = body;
		this.status = status;
	}
	
	public HttpResult(String body, Integer status,Header[] headers){
		this.body = body;
		this.status = status;
		this.headers = headers;
	}	
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	
}
