package cn.ce.services.auth.controller;

import cn.ce.services.auth.entity.User;
import cn.ce.services.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author: ggs
 * @date: 2019-03-01 15:03
 **/
@Api(description = "用户信息管理")
@Validated
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    @ApiOperation("增加一个用户")
    public void insertSelective(@RequestBody @Valid User user) {
        userService.insertSelective(user);
    }

    @PutMapping
    @ApiOperation("修改用户信息")
    public void updateByPrimaryKeySelective(@RequestBody User user) {
        userService.updateByPrimaryKeySelective(user);
    }

    @GetMapping
    @ApiOperation("查询用户信息")
    public User selectByPrimaryKey(@RequestParam Integer id) {
        return userService.selectByPrimaryKey(id);
    }
}
