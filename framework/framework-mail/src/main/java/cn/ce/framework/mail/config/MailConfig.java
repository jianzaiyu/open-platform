package cn.ce.framework.mail.config;

import cn.ce.framework.mail.service.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ggs
 * @date: 2019-03-14 16:18
 **/
@Configuration
public class MailConfig {

    @Bean
    public MailService mailService(){
        return new MailService();
    }
}
