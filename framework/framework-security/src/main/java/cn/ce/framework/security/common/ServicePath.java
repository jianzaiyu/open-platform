package cn.ce.framework.security.common;

import lombok.Data;

/**
 * @author: ggs
 * @date: 2019-04-28 18:33
 **/
@Data
public class ServicePath {
    private String[] swaggerUrl;
    private String[] httpAllMethod;
    private String[] httpGet;
    private String[] httpPost;
    private String[] httpPut;
    private String[] httpDelete;
}
