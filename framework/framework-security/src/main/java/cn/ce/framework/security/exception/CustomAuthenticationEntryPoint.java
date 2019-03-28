package cn.ce.framework.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ggs
 * @date 2019/3/10 2:47
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print("{\n" +
                "    \"code\": \"SYS0003\",\n" +
                "    \"data\": [],\n" +
                "    \"msg\": \"身份校验未通过\",\n" +
                "    \"status\": 200\n" +
                "}");
        response.getWriter().flush();
    }
}
