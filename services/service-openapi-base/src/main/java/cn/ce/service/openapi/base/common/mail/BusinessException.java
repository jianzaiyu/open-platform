package cn.ce.service.openapi.base.common.mail;

/**
 * 
 * @类描述: 业务异常基类
 * @作者 dingjia@300.cn
 * @日期 2015年11月27日 上午9:52:50
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -3505230851638640792L;
	
	public BusinessException(String message) {
		super(message);
	}
	public BusinessException(String message,Throwable e){
		super(message, e);
	}
}
