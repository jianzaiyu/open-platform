package cn.ce.services.account.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

/**
 * @author ggs
 * @date 2019/3/6 22:40
 */
@ApiIgnore
@RestController
public class ExampleController {

    @GetMapping("hello")
    public String hello(@AuthenticationPrincipal Principal principal) {
        return "hello" + principal.getName();
    }

}
