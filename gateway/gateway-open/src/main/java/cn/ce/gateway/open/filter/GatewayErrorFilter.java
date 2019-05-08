package cn.ce.gateway.open.filter;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ggs
 * @date 2018/11/25 19:30
 */
@Slf4j
public class GatewayErrorFilter extends SendErrorFilter{

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            ExceptionHolder exception = this.findZuulException(context.getThrowable());
            log.error("进入系统异常拦截", exception);

            HttpServletResponse response = context.getResponse();
            response.setContentType("application/json; charset=utf8");
            response.setStatus(exception.getStatusCode());
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print("{code:"+ exception.getStatusCode() +",message:\""+ exception.getErrorCause() +"\"}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(writer!=null){
                    writer.close();
                }
            }

        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
        }

        return null;
    }
}
