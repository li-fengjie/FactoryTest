package com.life.test;

import com.life.utils.CommonsUtils;
import com.life.utils.Mail;
import com.life.utils.MailUtils;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class SendEmail {

    @Test
    public void sendEmail() throws IOException {
        ResourceBundle resource = ResourceBundle.getBundle("email_template");

        String host = resource.getString("host");
        String uname = resource.getString("uname");
        String password = resource.getString("password");
        String from = resource.getString("from");
        String subject = resource.getString("subject");
        subject = new String(subject.getBytes("iso-8859-1"),"gbk");
        String content = new String(resource.getString("content").getBytes("iso-8859-1"),"gbk");
        String code = CommonsUtils.getUUID();
        content = MessageFormat.format(content,code,code);//替换{0}
        System.out.println(content);
        String to = "1052125651@qq.com";
        Session session = MailUtils.createSession(host,uname,password);
        Mail mail = new Mail(from,to,subject,content);
        try {
            MailUtils.send(session,mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
