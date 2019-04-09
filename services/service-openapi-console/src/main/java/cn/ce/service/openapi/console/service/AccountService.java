package cn.ce.service.openapi.console.service;

import cn.ce.framework.base.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author: ggs
 * @date: 2019-03-29 18:20
 **/
@FeignClient(value = "service-account")
public interface AccountService {
    @GetMapping("user/userDetail/username/{userName}")
    Result selectUserDetailByUserName(@PathVariable("userName") String userName, @RequestHeader("Authorization") String Authorization);
}
