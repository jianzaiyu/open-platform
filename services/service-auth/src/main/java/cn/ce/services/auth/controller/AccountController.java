package cn.ce.services.auth.controller;

import cn.ce.services.auth.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author: ggs
 * @date: 2019-03-01 15:03
 **/
@Api("账户管理")
@RestController
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public String hello(@AuthenticationPrincipal Principal principal) {
        return "hello" + principal.getName();
    }
}
