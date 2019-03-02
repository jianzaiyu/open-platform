package cn.ce.framework.base.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: ggs
 * @date: 2018-09-14 15:28
 * 打印request请求日志
 **/
@Slf4j
public class RequestLogSupport {

    public static void handleLog(HttpServletRequest request) {
        log.info("  request method=" + request.getMethod() +
                "  url=" + request.getRequestURL() +
                "  request params=" +
                JSON.toJSONString(request.getParameterMap(), SerializerFeature.WriteMapNullValue,
                        SerializerFeature.DisableCircularReferenceDetect));
    }

    public static void handleLog(HttpServletRequest request, Exception ex) {
        log.error("  request method=" + request.getMethod() +
                "  url=" + request.getRequestURL() +
                "  request params=" +
                JSON.toJSONString(request.getParameterMap(), SerializerFeature.WriteMapNullValue,
                        SerializerFeature.DisableCircularReferenceDetect), ex);
    }
}
