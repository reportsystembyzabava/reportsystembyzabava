package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.entity.userExtraData.Confirmation;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.ConfirmationJpnRepository;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.UserJpaRepository;
import com.reportSystemByZabavaApplication.demo.servise.fileGetHashSum.Hash;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.JSONBuilder;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.containers.MailJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

/**
 * Created by Thealeshka on 09.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.controllers
 */

@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    private final static Long waitingForCode = 600000l;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
    public @ResponseBody
    String registerStepOne(@RequestBody User user) {
        try {
            userJpaRepository.save(user);
            user.setConfirm(confirmationJpnRepository.save(new Confirmation()).setSuccess(false).setDataSentEMail(new Date()))
                    .getConfirm().setCode(sentEMail(user.geteMail(), "test", "test", codeGen()));
            user.setUserType(User.UserType.Student);
            if (user.getUserToken() == null) {
                user.setUserToken(Hash.checkSum(user.toString(), MessageDigest.getInstance("SHA-256")));
                userJpaRepository.save(user);
                return JSONBuilder.create().add("status", "true").add("userToken",
                        user.getUserToken()).get();
            } else {
                return JSONBuilder.create().add("status", "true").get();
            }
        } catch (DataIntegrityViolationException e) {
            return JSONBuilder.create().add("status", "false")
                    .add("error", "email is already in use").get();
        } catch (IOException | NoSuchAlgorithmException e) {
            return JSONBuilder.create().add("status", "false")
                    .add("error", "server exception").get();
        }
    }

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public @ResponseBody
    String registerStepTwo(@RequestBody MailJSON json) {
        User user = userJpaRepository.findByUserToken(json.getUserToken());
        if (user == null) {
            return JSONBuilder.create().add("status", "false")
                    .add("error", "no such user").get();
        }
        System.out.println(user.getConfirm().getDataSentEMail().toString());
        System.out.println(new Date().toString());
        System.out.println(new Date().getTime() - user.getConfirm().getDataSentEMail().getTime());
        if ((new Date().getTime() - user.getConfirm().getDataSentEMail().getTime()) < waitingForCode) {
            if (user.getConfirm().getCode().equals(json.getCode())) {
                user.getConfirm().setSuccess(true).setCode(new Date().toString());
                userJpaRepository.save(user);
                return JSONBuilder.create().add("status", "true").get();
            }
        } else {
            return JSONBuilder.create().add("status", "false")
                    .add("error", "code life time is over").get();
        }
        return JSONBuilder.create().add("status", "false")
                .add("error", "wrong code").get();
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
        int code = 0;
        while (code < 1000) {
            code = new Random().nextInt(9999);
        }
        return String.valueOf(code);
    }


}
