package cn.ce.service.openapi.console.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ggs
 * @date 2019/3/10 2:47
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exception) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print("{\n" +
                "    \"code\": \"SYS0003\",\n" +
                "    \"data\": [],\n" +
                "    \"msg\": \"权限校验未通过\",\n" +
                "    \"status\": 200\n" +
                "}");
        response.getWriter().flush();
    }
}
