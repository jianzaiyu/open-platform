package cn.ce.services.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author ggs
 * @date 2019/3/6 22:40
 */
@ApiIgnore
@RestController
public class ExampleController {

    @GetMapping("hello")
    public String hello(HttpServletRequest request) {
        return "hello" + request.getRequestedSessionId();
    }

    @GetMapping("hello1")
    public String hello1(Principal principal) {
        return "hello";
    }

}
