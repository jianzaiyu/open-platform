package cn.ce.service.openapi.base.account.service;

import cn.ce.framework.base.pojo.Result;
import cn.ce.service.openapi.base.account.entity.OauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author: ggs
 * @date: 2019-03-29 18:20
 **/
@FeignClient(value = "service-account")
public interface AccountService {
    @GetMapping("user/current/detail")
    Result selectUserDetailByUserName(@RequestHeader("Authorization") String Authorization);

    @PostMapping("client/appClient")
    Result insertClientInfo(String clientId,String secret);
}
