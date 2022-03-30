package com.glodon.mycommunity;

import com.glodon.mycommunity.utils.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = MycommunityApplication.class)
public class MailTests {
    @Autowired
    MailUtil mailUtil;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendHtmlMail(){
        Context context = new Context();
        context.setVariable("username","caigr");

        String content = templateEngine.process("/mail/demo",context);
        mailUtil.sendEmail("1764681315@qq.com","html",content);
    }

    @Test
    public void sendMailTest(){
        mailUtil.sendEmail("1764681315@qq.com","test","hello!");
    }


}
