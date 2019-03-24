package cn.ce.services.auth.endpoint;

import cn.ce.framework.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author: ggs
 * @date: 2019-03-11 16:51
 **/
@ApiIgnore
@RestController
public class OauthCustomEndpoint {

    @Autowired
    ConsumerTokenServices tokenServices;


    @DeleteMapping("oauth/revoke/{accessToken}")
    public void revokeToken(@PathVariable String accessToken) {
//        String authorization = request.getHeader("Authorization");
//        if (!StringUtils.isEmpty(authorization) && StringUtils.startsWithIgnoreCase(authorization, "Bearer")) {
//            String tokenId = authorization.substring(7);
//            tokenServices.revokeToken(tokenId);
//        }
        if(!tokenServices.revokeToken(accessToken)){
            throw new BusinessException("令牌不存在!");
        }
    }

}
