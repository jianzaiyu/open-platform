package cn.ce.service.openapi.base.account.service;

import cn.ce.framework.base.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ggs
 * @date: 2019-03-29 18:20
 **/
@FeignClient(value = "service-account")
public interface AccountService {
    @GetMapping("user/detail/{userName}")
    Result selectUserDetailByUserName(@PathVariable String userName);

    @PostMapping("client/appClient")
    Result insertClientInfo(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);
}
