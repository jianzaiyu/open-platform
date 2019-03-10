package cn.ce.services.auth.config;

import cn.ce.framework.base.pojo.Result;
import cn.ce.framework.base.pojo.ResultCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.http.HttpStatus;
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
        response.getWriter().print(JSON.toJSONString(new Result(HttpStatus.OK, ResultCode.SYS0002, new JSONArray(), "权限校验未通过")));
        response.getWriter().flush();
    }
}
