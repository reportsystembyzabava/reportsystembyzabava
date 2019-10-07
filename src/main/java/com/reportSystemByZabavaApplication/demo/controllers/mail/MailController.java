package com.reportSystemByZabavaApplication.demo.controllers.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public @ResponseBody
    String sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("Alexfenin1234@gmail.com");
        message.setSubject("Обращение по поводу поломки");
        message.setText("не работает система обмена сообщениями???");
        this.javaMailSender.send(message);

        return "Email Sent!";
    }
}
