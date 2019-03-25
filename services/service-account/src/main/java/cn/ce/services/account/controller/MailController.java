package cn.ce.services.account.controller;

import cn.ce.framework.base.support.IdentifierGenerateSupport;
import cn.ce.framework.mail.entity.Mail;
import cn.ce.framework.mail.service.MailService;
import cn.ce.framework.redis.support.RedisUtil;
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
@Api(description = "邮件服务_无保护")
@Validated
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("register")
    public void postRegisterMail(@RequestBody @Valid Mail mail) {
        String token = IdentifierGenerateSupport.genRandomUUID8();
        redisUtil.setForTimeMIN("email_register_" + mail.getReceiver()
                , token, 2);
        mail.setContent(mail.getContent() + "&token=" + token);
        mailService.sendSimpleMail(mail);
    }

    @PostMapping("forget")
    public void postForgetMail(@RequestBody @Valid Mail mail) {
        String token = IdentifierGenerateSupport.genRandomUUID8();
        redisUtil.setForTimeMIN("email_forget_" + mail.getReceiver()
                , token, 2);
        mail.setContent(mail.getContent() + "&token=" + token);
        mailService.sendSimpleMail(mail);
    }
}
