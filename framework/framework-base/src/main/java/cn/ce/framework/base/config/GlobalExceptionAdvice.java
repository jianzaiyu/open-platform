package cn.ce.framework.base.config;


import cn.ce.framework.base.exception.BusinessException;
import cn.ce.framework.base.pojo.Result;
import cn.ce.framework.base.pojo.ResultCode;
import cn.ce.framework.base.support.RequestLogSupport;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author ggs
 * @date 2018/8/5 13:09
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 前端传递参数 不符合规则抛出异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class,
            BindException.class, MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleParamsException(HttpServletRequest request,
                                        Exception ex) {
        RequestLogSupport.handleLog(request, ex);
        StringBuilder stringBuilder = new StringBuilder();
        BindingResult bindingResult = null;
        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        }
        if (ex instanceof BindException) {
            bindingResult = ((BindException) ex).getBindingResult();
        }
        if (bindingResult != null && bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage()).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            stringBuilder.append(ex.getMessage());
        }
        return new Result<>(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), stringBuilder.toString());
    }

    /**
     * 平台业务异常处理
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleBusinessException(HttpServletRequest request,
                                          BusinessException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Result handleSecurityException(HttpServletRequest request,
                                          AccessDeniedException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.UNAUTHORIZED, ResultCode.SYS0003, new JSONArray(), ex.getMessage());
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleMailExceptionException(HttpServletRequest request,
                                               MailException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Result NoHandlerFoundException(HttpServletRequest request,
                                          NoHandlerFoundException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.NOT_FOUND, ResultCode.SYS0000, new JSONArray(), ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleHttpRequestMethodNotSupportedException(HttpServletRequest request,
                                                               HttpRequestMethodNotSupportedException ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleAllException(HttpServletRequest request,
                                     Exception ex) {
        RequestLogSupport.handleLog(request, ex);
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.SYS0001, new JSONArray());
    }
}

