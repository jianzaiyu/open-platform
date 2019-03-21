package cn.ce.framework.base.support;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ggs
 * @date: 2019-03-12 14:20
 **/
public class ResponseWriteSupport {

    public static void writeJson(HttpServletResponse response, Object object) throws IOException {
        if (response != null) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(object));
            response.getWriter().flush();
        }
    }
}
