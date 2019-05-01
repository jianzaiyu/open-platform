package cn.ce.gateway.entity;

import lombok.Data;

/**
 * @author: ggs
 * @date: 2019-04-30 15:11
 **/
@Data
public class TargetUrlAndTenantIdEntity {
    public TargetUrlAndTenantIdEntity(String targetUrl, String tenantId) {
        this.targetUrl = targetUrl;
        this.tenantId = tenantId;
    }

    private String targetUrl;
    private String tenantId;
}
