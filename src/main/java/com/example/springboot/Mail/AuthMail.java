package com.example.springboot.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableConfigurationProperties(MailProperties.class)
public class AuthMail {

    private static final String template = "mail.ftl";
    private static final int codeLength=4;
    private static final Map<String,String> checkCode=new ConcurrentHashMap<>();
    private final FreeMarkerConfigurer freeMarkerConfigurer;
    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;

    @Autowired
    public AuthMail(FreeMarkerConfigurer freeMarkerConfigurer, JavaMailSender javaMailSender, MailProperties mailProperties){
        this.freeMarkerConfigurer=freeMarkerConfigurer;
        this.javaMailSender=javaMailSender;
        this.mailProperties=mailProperties;
    }
    public void sendMail(String email) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String code=getRandomCode();
        map.put("email", email);
        map.put("code",code);
        String text = this.getTextByTemplate(map);
        this.send(email, text);
        checkCode.put(email,code);
    }
    private String getTextByTemplate(Map<String, Object> model) throws Exception {
        return FreeMarkerTemplateUtils
                .processTemplateIntoString(this.freeMarkerConfigurer.getConfiguration().getTemplate(AuthMail.template), model);
    }
    private void send(String email, String text) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        InternetAddress from = new InternetAddress();
        from.setAddress(this.mailProperties.getUsername());
        from.setPersonal("验证码", "UTF-8");
        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject("验证码邮件");
        helper.setText(text, true);
        this.javaMailSender.send(message);
    }

    private  String getRandomCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < codeLength; ++i) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkCode(String code,String email){
        String result=checkCode.get(email);
        if(result==null||!result.equals(code)){
            return false;
        }else {
            checkCode.remove(email);
            return true;
        }
    }
}
