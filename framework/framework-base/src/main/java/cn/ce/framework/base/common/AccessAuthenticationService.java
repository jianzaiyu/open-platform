package cn.ce.framework.base.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ggs
 * @date: 2018-10-16 00:24
 **/
public interface AccessAuthenticationService {
    boolean hasPermit(HttpServletRequest request, HttpServletResponse response, Object object);
}
