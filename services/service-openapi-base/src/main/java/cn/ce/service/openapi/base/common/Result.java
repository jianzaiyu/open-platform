package cn.ce.service.openapi.base.common;

import cn.ce.framework.base.common.CloudResult;

/**
 *
 * @author makangwei
 * 2017-8-1
 */
public class Result <T> implements CloudResult {

	private T data;
	
	private String status = Status.FAILED;
	
	private String message;
	
	private ErrorCodeNo errorCode = ErrorCodeNo.SYS001;

	public Result(){
		super();
	}
	
	public Result( String message, ErrorCodeNo errorCode, T data, String status){
		this.message = message;
		this.errorCode = errorCode;
		this.data =data;
		this.status = status;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setErrorMessage(String message){
		this.message = message;
		this.status = Status.FAILED;
		if(this.errorCode == null ){
			this.errorCode = ErrorCodeNo.SYS001;
		}
	}
	
	public void setErrorMessage(String message,ErrorCodeNo errorCodeNo){
		this.message = message;
		this.status = Status.FAILED;
		this.errorCode = errorCodeNo;
	}
	
	public Result<T> returnErrorMessage(String message,ErrorCodeNo errorCodeNo){
		this.message = message;
		this.status = Status.FAILED;
		this.errorCode = errorCodeNo;
		return this;
	}
	
	public void setSuccessMessage(String message){
		this.message = message;
		this.status = Status.SUCCESS;
		this.errorCode = ErrorCodeNo.SYS000;
	}
	
	public Result<T> returnSuccessMessage(String message){
		this.message = message;
		this.status = Status.SUCCESS;
		this.errorCode = ErrorCodeNo.SYS000;
		return this;
	}

	
	public void setSuccessData(T data){
		this.data = data;
		this.status = Status.SUCCESS;
		this.errorCode = ErrorCodeNo.SYS000;
	}

	public ErrorCodeNo getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodeNo errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "CloudResult [data=" + data + ", status=" + status + ", message="
				+ message + ", errorCode=" + errorCode + "]";
	}
	
	/**
	 * @Title: errorResult
	 * @Description: 快速拼装返回对象
	 * @author: makangwei 
	 * @date:   2017年10月20日 下午8:57:34 
	 * @param : @param message消息
	 * @param : @param errorCodeNo错误码，如果传入null则设置为SYS001
	 * @param : @param data 泛型数据
	 * @param : @param success or failed
	 */
	public static <T> Result<T> errorResult(String message, ErrorCodeNo errorCodeNo, T data, String status){
		Result<T> result = new Result<T>();
		result.setMessage(message);
		result.setData(data);
		if(errorCodeNo == null){
			result.setErrorCode(ErrorCodeNo.SYS001);
		}else{
			result.setErrorCode(errorCodeNo);
		}
		if(Status.SUCCESS == status){
			result.setStatus(Status.SUCCESS);
		}else{
			result.setStatus(Status.FAILED);
		}
		return result;
	}
	
	public static <T> Result<T> successResult(String message, T data){
		Result<T> result = new Result<T>();
		result.setMessage(message);
		result.setData(data);
		result.setStatus(Status.SUCCESS);
		result.setErrorCode(ErrorCodeNo.SYS000);
		return result;
	}
	
}
