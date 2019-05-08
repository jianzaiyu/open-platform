package cn.ce.services.auth.config;

import cn.ce.services.auth.entity.User;
//import cn.ce.services.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

//    @Autowired
//    private UserService userService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
//        User user = userService.selectByUserName(authentication.getName());
//        user.setPassword("N/A");
//        response.put("user_name", user);
        return response;
    }
}