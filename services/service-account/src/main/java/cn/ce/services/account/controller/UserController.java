package cn.ce.services.account.controller;

import cn.ce.framework.base.exception.BusinessException;
import cn.ce.services.account.entity.User;
import cn.ce.services.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
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
        if (userService.selectByUserName(user.getUsername()) == null) {
            if (!StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
            userService.insertSelective(user);
        } else {
            throw new BusinessException("用户名重复");
        }
    }

    @PutMapping
    @ApiOperation("修改用户信息")
    public void updateByPrimaryKeySelective(@RequestBody User user) {
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userService.updateByPrimaryKeySelective(user);
    }

    @GetMapping("{id}")
    @ApiOperation("查询用户信息")
    public User selectByPrimaryKey(@PathVariable Integer id) {
        User user = userService.selectByPrimaryKey(id);
        user.setPassword("");
        return user;
    }

    @GetMapping("duplicate/{userName}")
    @ApiOperation("用户名验重")
    public boolean selectByUserName(@PathVariable String userName) {
        return userService.selectByUserName(userName) != null;
    }

    @GetMapping
    @ApiOperation("查询用户By_userName")
    public User selectUserByUserName(@RequestParam String userName) {
        return userService.selectByUserName(userName);
    }
}
