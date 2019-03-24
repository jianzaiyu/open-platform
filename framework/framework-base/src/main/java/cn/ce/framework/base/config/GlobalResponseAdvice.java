package cn.ce.framework.base.config;


import cn.ce.framework.base.common.CloudResult;
import cn.ce.framework.base.pojo.Result;
import cn.ce.framework.base.pojo.ResultCode;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ggs
 * @date 2018/8/6 0:59
 */
@ControllerAdvice("cn.ce")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return aClass.isAssignableFrom(FastJsonHttpMessageConverter.class)
                ||aClass.isAssignableFrom(StringHttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (!(o instanceof CloudResult)) {//HttpMessageConverter引起的BUG 用FastJsonHttpMessageConverter
            return new Result<>(HttpStatus.OK, ResultCode.SYS0000, o == null ? new JSONArray() : o);
        }
        return o;//支持直接返回Result
    }
}
