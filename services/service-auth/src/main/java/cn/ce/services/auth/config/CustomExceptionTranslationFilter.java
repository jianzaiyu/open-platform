package cn.ce.services.auth.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ggs
 * @date: 2019-03-15 18:28
 **/
public class CustomExceptionTranslationFilter extends ExceptionTranslationFilter {
    public CustomExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationEntryPoint);
        setAccessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Override
    protected void sendStartAuthentication(HttpServletRequest request,
                                           HttpServletResponse response, FilterChain chain,
                                           AuthenticationException reason) throws ServletException, IOException {

//        boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
//
//        if (isAjax) {
//
//            String jsonObject = "{\"message\":\"Please login first.\"," + "\"access-denied\":true,\"cause\":\"AUTHENTICATION_FAILURE\"}";
//            String contentType = "application/json";
//            resp.setContentType(contentType);
//            PrintWriter out = resp.getWriter();
//            out.print(jsonObject);
//            out.flush();
//            out.close();
//            return;
//        }

        super.sendStartAuthentication(request, response, chain, reason);
    }
}
