package cn.ce.service.openapi.base.account.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: ggs
 * @date: 2019-05-09 15:46
 **/
@FeignClient(value = "gateway-open")
public interface GatewayOpenService {
    @PostMapping("actuator/bus-refresh")
    void doRefresh();
}
