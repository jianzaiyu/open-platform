package cn.ce.service.openapi.base.newgateway.entity;

import java.io.Serializable;
import lombok.Data;

/**
*@auther GGs
*@date 2019-04-29 10:10:29
*/
@Data
public class GatewayApiDefine implements Serializable {
    private Integer id;

    private String path;

    private String serviceId;

    private String url;

    private Boolean retryable;

    private Boolean enabled;

    private Byte stripPrefix;

    private String apiName;

    private static final long serialVersionUID = 1L;
}