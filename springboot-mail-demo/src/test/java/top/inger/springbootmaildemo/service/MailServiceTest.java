package top.inger.springbootmaildemo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    //纯文本邮件test
    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail("1454400577@qq.com","first mail of text","test...................");
    }

    //html邮件test
    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content="<html>\n"+"<body>\n"+"<h4>This is h4 test title.</h4>\n"+"</body>\n"+"</html>";
        mailService.sendHtmlMail("1454400577@qq.com","first mail of html",content);
    }

    //附件邮件test
    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        String filePath="D:\\PhotoData\\Social\\1.jpg";
        mailService.sendAttachmentsMail("1454400577@qq.com","first mail of file","file",filePath);
    }

    //图片邮件test
    @Test
    public void sendInlineResourceMailTest() {
        String imgPath="D:\\PhotoData\\Social\\1.jpg";
        String rscId="img002";
        String content="<html><body>图片附件：<img src=\'cid:"+rscId+"\'><img></body></html>";
        mailService.sendInlineResourceMail("1454400577@qq.com","first mail of image",content,imgPath,rscId);
    }

    //模板邮件test
    @Test
    public void TemplateMailTest() throws MessagingException {
        Context context=new Context();
        context.setVariable("id","006");
        String emailContent=templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("1454400577@qq.com","first mail of template",emailContent);
    }

}