package cn.ce.services.auth.config;

import cn.ce.framework.base.pojo.Result;
import cn.ce.framework.base.pojo.ResultCode;
import cn.ce.framework.base.support.ResponseWriteSupport;
import com.alibaba.fastjson.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author ggs
 * @date 2019/3/10 4:00
 */
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        HttpServletResponse response =
                ((ServletRequestAttributes) Objects.requireNonNull
                        (RequestContextHolder.getRequestAttributes(), "request属性参数为null")).getResponse();
        ResponseWriteSupport.writeJson(response, new Result<>(HttpStatus.OK, ResultCode.SYS0003, new JSONArray(), e.getMessage()));
        return null;
    }
}
