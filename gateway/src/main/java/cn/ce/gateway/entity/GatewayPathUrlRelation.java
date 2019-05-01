package cn.ce.gateway.entity;

import lombok.Data;

/**
 * @author: ggs
 * @date: 2019-04-30 15:42
 **/
@Data
public class GatewayPathUrlRelation {
    private Integer id;
    private String keyUrl;
    private String targetUrl;
    private String tenantId;

}
