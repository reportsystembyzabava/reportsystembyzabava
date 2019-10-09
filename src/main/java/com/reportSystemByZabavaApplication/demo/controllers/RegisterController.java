package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thealeshka on 09.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.controllers
 */

@RestController
//@RequestMapping(name = "register")
public class RegisterController {
    private UserJpaRepository userJpaRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public RegisterController(UserJpaRepository userJpaRepository, JavaMailSender javaMailSender) {
        this.userJpaRepository = userJpaRepository;
        this.javaMailSender = javaMailSender;
    }

    public RegisterController() {
    }

    @RequestMapping(value = "/start",method = RequestMethod.POST)
    public String registerStepOne(@RequestBody User user) {

        userJpaRepository.save(user);

        return "test:test";
    }

    private void sentEMail(String eMail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(eMail);
        message.setSubject(subject);
        message.setText(text);
        this.javaMailSender.send(message);

    }
}
