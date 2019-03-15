package cn.ce.services.account.controller;

import cn.ce.framework.mail.entity.Mail;
import cn.ce.framework.mail.service.MailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: ggs
 * @date: 2019-03-14 15:47
 **/
@Api(description = "邮件服务")
@Validated
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping
    public void postMail(@RequestBody @Valid Mail mail) {
        mailService.sendSimpleMail(mail);
    }
}
