package cn.ce.framework.mail.service;

import cn.ce.framework.mail.entity.Mail;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: ggs
 * @date: 2019-03-14 14:54
 **/
@Slf4j
@Service
public class MailService {
    @Value("${spring.mail.sender}")
    private String MAIL_SENDER; //邮件发送者

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration; //freemarker

    /**
     * 发送一个简单格式的邮件
     *
     * @param mail
     */
    @Async
    public void sendSimpleMail(Mail mail) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送人
        simpleMailMessage.setFrom(MAIL_SENDER);
        //邮件接收人
        simpleMailMessage.setTo(mail.getReceiver());
        //邮件主题
        simpleMailMessage.setSubject(mail.getSubject());
        //邮件内容
        simpleMailMessage.setText(mail.getContent());
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送一个HTML格式的邮件
     *
     * @param mailBean
     */
//    public void sendHTMLMail(Mail mailBean) throws MailException, MessagingException {
//        MimeMessage mimeMailMessage = null;
//        mimeMailMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//        mimeMessageHelper.setFrom(MAIL_SENDER);
//        mimeMessageHelper.setTo(mailBean.getRecipient());
//        mimeMessageHelper.setSubject(mailBean.getSubject());
//        StringBuilder sb = new StringBuilder();
//        sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
//                .append("\"<p style='color:#F00'>你是真的太棒了！</p>")
//                .append("<p style='text-align:right'>右对齐</p>");
//        mimeMessageHelper.setText(sb.toString(), true);
//        javaMailSender.send(mimeMailMessage);
//    }

    /**
     * 发送带附件格式的邮件
     *
     * @param mailBean
     */
//    public void sendAttachmentMail(Mail mailBean) throws MailException, MessagingException {
//        MimeMessage mimeMailMessage = null;
//        mimeMailMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//        mimeMessageHelper.setFrom(MAIL_SENDER);
//        mimeMessageHelper.setTo(mailBean.getRecipient());
//        mimeMessageHelper.setSubject(mailBean.getSubject());
//        mimeMessageHelper.setText(mailBean.getContent());
//        //文件路径
//        FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
//        mimeMessageHelper.addAttachment("mail.png", file);
//
//        javaMailSender.send(mimeMailMessage);
//    }

    /**
     * 发送带静态资源的邮件
     *
     * @param mailBean
     */
//    public void sendInlineMail(Mail mailBean) throws MailException, MessagingException {
//        MimeMessage mimeMailMessage = null;
//        mimeMailMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//        mimeMessageHelper.setFrom(MAIL_SENDER);
//        mimeMessageHelper.setTo(mailBean.getRecipient());
//        mimeMessageHelper.setSubject(mailBean.getSubject());
//        mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
//        //文件路径
//        FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
//        mimeMessageHelper.addInline("picture", file);
//
//        javaMailSender.send(mimeMailMessage);
//    }

    /**
     * 发送基于Freemarker模板的邮件
     *
     * @param mailBean
     */
//    public void sendTemplateMail(Mail mailBean) throws MailException, MessagingException, IOException, TemplateException {
//        MimeMessage mimeMailMessage = null;
//        mimeMailMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//        mimeMessageHelper.setFrom(MAIL_SENDER);
//        mimeMessageHelper.setTo(mailBean.getRecipient());
//        mimeMessageHelper.setSubject(mailBean.getSubject());
//
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("content", mailBean.getContent());
//        model.put("title", "标题Mail中使用了FreeMarker");
//        Template template = configuration.getTemplate("mail.ftl");
//        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//        mimeMessageHelper.setText(text, true);
//
//        javaMailSender.send(mimeMailMessage);
//
//    }
}