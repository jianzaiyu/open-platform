package cn.ce.services.auth.controller;

import cn.ce.services.auth.entity.User;
import cn.ce.services.auth.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author: ggs
 * @date: 2019-03-11 16:51
 **/
@ApiIgnore
@RestController
public class OauthCustomEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    ConsumerTokenServices tokenServices;

    @GetMapping("/oauth/current")
    @ApiOperation("查询用户信息")
    public User selectCurrent(Principal principal) {
        User user = userService.selectByUserName(principal.getName());
        user.setPassword("");
        return user;
    }


    @DeleteMapping("/oauth/current")
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && StringUtils.startsWithIgnoreCase(authorization, "Bearer")) {
            String tokenId = authorization.substring(7);
            tokenServices.revokeToken(tokenId);
        }
    }
}
