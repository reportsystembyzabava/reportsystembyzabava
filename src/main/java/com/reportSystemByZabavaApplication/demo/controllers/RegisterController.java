package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.entity.userExtraData.Confirmation;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.ConfirmationJpnRepository;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by Thealeshka on 09.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.controllers
 */

@RestController
//@RequestMapping(name = "register")
public class RegisterController {
    private ConfirmationJpnRepository confirmationJpnRepository;
    private UserJpaRepository userJpaRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public RegisterController(ConfirmationJpnRepository confirmationJpnRepository, UserJpaRepository userJpaRepository, JavaMailSender javaMailSender) {
        this.confirmationJpnRepository = confirmationJpnRepository;
        this.userJpaRepository = userJpaRepository;
        this.javaMailSender = javaMailSender;
    }


    public RegisterController() {
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String registerStepOne(@RequestBody User user) {
        userJpaRepository.save(user);
        user.setConfirm(confirmationJpnRepository.save(new Confirmation()).setSuccess(false).setDataSentEMail(new Date().toString()))
                .getConfirm().setCode(sentEMail(user.geteMail(), "test", "test", codeGen()));
        user.setUserType(User.UserType.Student);
        userJpaRepository.save(user);
        return "test:test";
    }

    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public String registerStepTwo(@RequestBody String string) {

        return "test:test";
    }

    private String sentEMail(String eMail, String subject, String text, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(eMail);
        message.setSubject(subject);
        message.setText(text + " " + code);
        //this.javaMailSender.send(message);
        return code;
    }

    private String codeGen() {
        return String.valueOf(new Random().nextInt(9999));
    }
}
