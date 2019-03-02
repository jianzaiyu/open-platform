package cn.ce.services.account.controller;

import cn.ce.services.account.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ggs
 * @date: 2019-03-01 15:03
 **/
@Api("账户管理")
@RestController
public class AccountController {
    @Autowired
    private UserService userService;


}
