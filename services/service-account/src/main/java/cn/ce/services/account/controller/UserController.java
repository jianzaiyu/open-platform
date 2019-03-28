package cn.ce.services.account.controller;

import cn.ce.framework.base.exception.BusinessException;
import cn.ce.framework.redis.support.RedisUtil;
import cn.ce.services.account.entity.User;
import cn.ce.services.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("current")
    @ApiOperation("当前登陆用户")
    public Object current(Principal principal) {
        User user = userService.selectByUserName(principal.getName());
        if(user != null){
            user.setPassword("N/A");
        }
        return user;
    }

    @PostMapping
    @ApiOperation("增加一个用户_无保护")
    public void insertSelective(@RequestBody @Valid User user, @RequestHeader String Authorization) {
        String token = redisUtil.get("email_register_" + user.getEmail());
        if (token == null || !Authorization.contains(token)) {
            throw new BusinessException("链接失效,请重新发送!");
        }
        if (userService.selectByUserName(user.getUsername()) != null) {
            throw new BusinessException("用户名重复!");
        }

        if (userService.selectByEmail(user.getEmail()) != null) {
            throw new BusinessException("邮箱地址已经被注册!");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.insertSelective(user);
    }

    @PutMapping("forget")
    @ApiOperation("忘记密码_无保护")
    public void forgetPassword(@RequestBody @Valid User user, @RequestHeader String Authorization) {
        String token = redisUtil.get("email_forget_" + user.getEmail());
        if (token == null || !Authorization.contains(token)) {
            throw new BusinessException("链接失效,请重新发送!");
        }
        if (userService.selectByUserNameAndEmail(user.getUsername(), user.getEmail()) == null) {
            throw new BusinessException("用户名,邮箱地址不匹配!");
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userService.updateByUserNameSelective(user);
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
        if (user != null) {
            user.setPassword("N/A");
        }
        return user;
    }

    @GetMapping("duplicate/username/{userName}")
    @ApiOperation("用户名验重_无保护")
    public boolean selectByUserName(@PathVariable String userName) {
        return userService.selectByUserName(userName) != null;
    }

    @GetMapping("duplicate/email/{email}")
    @ApiOperation("邮箱验重_无保护")
    public boolean selectByEmail(@PathVariable String email) {
        return userService.selectByEmail(email) != null;
    }


    @GetMapping("username/{userName}")
    @ApiOperation("按用户名称查询用户")
    public User selectUserByUserName(@PathVariable String userName) {
        User user = userService.selectByUserName(userName);
        if (user != null) {
            user.setPassword("N/A");
        }
        return user;
    }

    @GetMapping("contact/{userName}")
    @ApiOperation("按用户名称查询用户联系方式_无保护")
    public User selectUserContactByUserName(@PathVariable String userName) {
        User user = userService.selectByUserName(userName);
        User revUser = new User();
        if (user != null) {
            String email = user.getEmail();
            String telNumber = user.getTelnumber();
            if (!StringUtils.isEmpty(email)) {
                revUser.setEmail(email.substring(0, 4) + "*****" + email.substring(email.indexOf("@")));
            }
            if (!StringUtils.isEmpty(telNumber)) {
                revUser.setTelnumber(telNumber.substring(0, 3) + "*****" + telNumber.substring(telNumber.length() - 4));
            }
            revUser.setPassword("N/A");
        }
        return revUser;
    }
}
