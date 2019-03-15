package cn.ce.framework.mail.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Mail implements Serializable {
    private static final long serialVersionUID = 6579334657898283071L;
    @Email
    private String receiver; //邮件接收人
    @NotBlank
    private String subject; //邮件主题
    @NotBlank
    private String content; //邮件内容
}