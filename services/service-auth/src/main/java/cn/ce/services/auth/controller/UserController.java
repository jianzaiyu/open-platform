package cn.ce.services.auth.controller;

import cn.ce.framework.base.support.IdentifierGenerateSupport;
import cn.ce.services.auth.entity.User;
import cn.ce.services.auth.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

/**
 * @author: ggs
 * @date: 2019-03-01 15:03
 **/
@Api("用户信息管理")
@Validated
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public String hello(@AuthenticationPrincipal Principal principal) {
        return "hello" + principal.getName();
    }

    @PostMapping
    public void insertSelective(@RequestBody @Valid User user){
        user.setUserCode(IdentifierGenerateSupport.genRandomUUID32());
        userService.insertSelective(user);
    }

    @PutMapping
    public void update(@RequestBody User user){
        userService.updateByPrimaryKeySelective(user);
    }

    @GetMapping
    public User selectByPrimaryKey(@RequestParam Integer id){
        return userService.selectByPrimaryKey(id);
    }
}
