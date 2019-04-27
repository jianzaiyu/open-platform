package cn.ce.services.account.controller;

import cn.ce.services.account.entity.OauthClientDetails;
import cn.ce.services.account.service.OauthClientDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: ggs
 * @date: 2019-04-26 15:43
 **/
@Api(description = "client账户管理")
@Validated
@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @PostMapping
    public void insertSelective(@Valid @RequestBody OauthClientDetails oauthClientDetails) {
        oauthClientDetailsService.insertSelective(oauthClientDetails);
    }

    @PostMapping("appClient")
    public void insertAppClientSelective(@RequestParam String clientId,@RequestParam String secret) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setClientId(clientId);
        oauthClientDetails.setClientSecret("{bcrypt}"+new BCryptPasswordEncoder().encode(secret));
        oauthClientDetails.setScope(clientId);
        oauthClientDetails.setAuthorizedGrantTypes("client_credentials,refresh_token");
        oauthClientDetails.setAccessTokenValidity(3600);
        oauthClientDetails.setRefreshTokenValidity(36000);
        oauthClientDetails.setAutoapprove("true");
        oauthClientDetails.setClientType((byte)2);
        oauthClientDetailsService.insertSelective(oauthClientDetails);
    }


    @DeleteMapping("{clientId}")
    public void deleteByPrimaryKey(@PathVariable String clientId) {
        oauthClientDetailsService.deleteByPrimaryKey(clientId);
    }

    @PutMapping
    public void update(@Valid @RequestBody OauthClientDetails oauthClientDetails) {
        oauthClientDetailsService.updateByPrimaryKeySelective(oauthClientDetails);
    }

    @GetMapping("{clientId}")
    public OauthClientDetails selectByPrimaryKey(@PathVariable String clientId) {
        return oauthClientDetailsService.selectByPrimaryKey(clientId);
    }

    @GetMapping("type/{clientType}")
    public List<OauthClientDetails> selectByClientType(@PathVariable String clientType) {
        return oauthClientDetailsService.selectByClientType(clientType);
    }
}
