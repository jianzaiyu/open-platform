package cn.ce.services.account.controller;

import cn.ce.framework.base.exception.BusinessException;
import cn.ce.framework.base.support.IdentifierGenerateSupport;
import cn.ce.framework.mail.entity.Mail;
import cn.ce.framework.mail.service.MailService;
import cn.ce.framework.redis.support.RedisUtil;
import cn.ce.services.account.entity.User;
import cn.ce.services.account.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public void postRegisterMail(@RequestBody @Valid Mail mail) {
        String token = IdentifierGenerateSupport.genRandomUUID8();
        redisUtil.setForTimeMIN("email_register_" + mail.getReceiver()
                , token, 2);
        mail.setContent(mail.getContent() + "&token=" + token);
        mailService.sendSimpleMail(mail);
    }

    @PostMapping("forget/{userName}")
    public void postForgetMail(@PathVariable String userName, @RequestBody @Valid Mail mail) {
        User user = userService.selectByUserName(userName);
        if (user != null && !StringUtils.isEmpty(user.getEmail())) {
            mail.setReceiver(user.getEmail());
        } else {
            throw new BusinessException("用户还未注册,或邮箱为空");
        }
        String token = IdentifierGenerateSupport.genRandomUUID8();
        redisUtil.setForTimeMIN("email_forget_" + mail.getReceiver()
                , token, 2);
        String template = "发送人 ：中企开放平台@300.cn\n" +
                "你好!\n" +
                "感谢你使用中企开放平台。 \n" +
                "要重置密码，请单击下面的链接。\n" +
                "#link#\n" +
                "如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入中企开放平台。 （该链接在48小时内有效，48小时后需要重新注册）\n" +
                "                致敬！\n" +
                "中企开放平台团队\n" +
                "----------------------------- \n" +
                "www.open.300.cn";
        String link = mail.getContent() + "&token=" + token;
        mail.setContent(template.replaceAll("#link#", link));
        mailService.sendSimpleMail(mail);
    }
}
