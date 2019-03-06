package cn.ce.services.account.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("helloadmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloadmin() {
        return "hello";
    }

    @GetMapping("hellouser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String hellouser() {
        return "hello";
    }
}
