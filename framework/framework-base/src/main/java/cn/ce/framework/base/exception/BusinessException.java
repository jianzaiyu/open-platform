package cn.ce.framework.base.exception;

/**
 * @author: ggs
 * @date: 2018-09-27 16:12
 **/
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5012744468450606818L;

    public BusinessException(String message) {
        super(message);
    }
}
