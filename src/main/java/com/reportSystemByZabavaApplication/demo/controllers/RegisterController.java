package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.ConfirmationJpaRepository;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.UserJpaRepository;
import com.reportSystemByZabavaApplication.demo.servise.MailSender;
import com.reportSystemByZabavaApplication.demo.servise.SetUserConfirmation;
import com.reportSystemByZabavaApplication.demo.servise.codeGenerator.CodeGenerator;
import com.reportSystemByZabavaApplication.demo.servise.getHashSum.Hash;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.JSONBuilder;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.MailJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by Thealeshka on 09.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.controllers
 */

@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    /**
     * Access code lifetime
     *
     * @see {@link #waitingForCode}
     */
    private final static Long waitingForCode = 600000L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConfirmationJpaRepository confirmationJpaRepository;
    private UserJpaRepository userJpaRepository;
    private JavaMailSender javaMailSender;
    /**
     * text and subject files URLs
     *
     * @see {@link #textEmailRegisterStart}
     * @see {@link #textEmailRegisterFinish}
     * @see {@link #subjectEmailRegister}
     * @see {@link #bonusFileURL}
     */
    private final String textEmailRegisterStart = "textEmailRegisterStart.txt";
    private final String textEmailRegisterFinish = "textEmailRegisterFinish.txt";
    private final String subjectEmailRegister = "subjectEmailRegister.txt";
    private final String bonusFileURL = "Bonus.pdf";

    /**
     * constructor for Autowired jpaRepositories
     *
     * @param ConfirmationJpaRepository
     * @param userJpaRepository
     * @param javaMailSender
     */
    @Autowired
    public RegisterController(ConfirmationJpaRepository ConfirmationJpaRepository, UserJpaRepository userJpaRepository, JavaMailSender javaMailSender) {
        this.confirmationJpaRepository = ConfirmationJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.javaMailSender = javaMailSender;
    }


    /**
     * spring constructor
     */
    public RegisterController() {
    }


    /**
     * @param user in Json from client
     * @return JSON with result creating new account (status plus error/userToken)
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public @ResponseBody
    String registerStepOne(@RequestBody User user) {
        if (user == null) {
            return JSONBuilder.create().add("status", "false").add("error", "get null").get();
        }
        String result;
        logger.info("user wanna register " + user.geteMail());
        try {
            userJpaRepository.save(user.setUserType("Student"));
            confirmationJpaRepository.save(SetUserConfirmation.setUserConfirmation(user).getConfirm());
            MailSender.sendCode(user.geteMail(), user.getLanguage(), subjectEmailRegister, textEmailRegisterStart,
                    javaMailSender, user.getUserName(),user.getConfirm().getCode());
            if (user.getUserToken() == null) {
                user.setUserToken(Hash.checkSum(user.toString(), MessageDigest.getInstance("SHA-256")));
                userJpaRepository.save(user);
                logger.info("user register ok, token generate");
                result = JSONBuilder.create().add("status", "true").add("userToken",
                        user.getUserToken()).get();
            } else {
                logger.info("user register ok");
                result = JSONBuilder.create().add("status", "true").get();
            }
        } catch (DataIntegrityViolationException e) {
            logger.info("user register false, email already used");
            result = JSONBuilder.create().add("status", "false")
                    .add("error", "email is already in use").get();
        } catch (IOException | NoSuchAlgorithmException e) {
            logger.warn(e.getMessage());
            result = JSONBuilder.create().add("status", "false")
                    .add("error", "server exception").get();
        }
        /**@see result return statement (JSON with status and error/userToken*/
        return result;
    }

    /**
     * @param mailJSON token + code
     * @return JSON with result (status + error/noting)
     */
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public @ResponseBody
    String registerStepTwo(@RequestBody MailJSON mailJSON) {
        User user = userJpaRepository.findByUserToken(mailJSON.getUserToken());
        if (user == null) {
            logger.warn("unknown user sent activation code");
            return JSONBuilder.create().add("status", "false")
                    .add("error", "no such user").get();
        }
        logger.info("user " + user.geteMail() + " try activate account");
        if ((new Date().getTime() - user.getConfirm().getDataSentEMail().getTime()) < waitingForCode) {
            if (user.getConfirm().getCode().equals(mailJSON.getCode())) {
                user.getConfirm().setSuccess(true).setCode(new Date().toString());
                userJpaRepository.save(user);
                logger.info("user " + user.geteMail() + " activation completed success");
                MailSender.sendRegisterPass(user.geteMail(), user.getLanguage(), subjectEmailRegister,
                        textEmailRegisterFinish, javaMailSender, bonusFileURL);
                return JSONBuilder.create().add("status", "true").get();
            }
        } else {
            logger.info("user " + user.geteMail() + " activation code life time is over");
            return JSONBuilder.create().add("status", "false")
                    .add("error", "code life time is over").get();
        }
        logger.info("user " + user.geteMail() + " wrong activation code");
        return JSONBuilder.create().add("status", "false")
                .add("error", "wrong code").get();
    }

    private String generateText(User.Language language, String fileURL) {
        try {
            StringBuilder result = new StringBuilder();
            Files.readAllLines(Paths.get("data/" + language + "/" + fileURL)).forEach(n -> result.append(n + "\n"));
            try {
                result.replace(result.indexOf("*+|+*"), "*+|+*".length() + result.indexOf("*+|+*"), "\n" + CodeGenerator.generateCodeStr(4));
            } catch (StringIndexOutOfBoundsException e) {
            }
            logger.info("text generate OK");
            return result.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "Sorry we have technical problems";
    }

    private String generateSubject(User.Language language) {
        try {
            StringBuilder result = new StringBuilder();
            Files.readAllLines(Paths.get("data/" + language + "/subjectEmailRegister.txt")).forEach(n -> result.append(n));
            logger.info("subject generate OK");
            return result.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "Sorry we have technical problems";
    }

    private void sentEMailStepTwo(String eMail, String subject, String text, ByteArrayResource resource) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(eMail);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("Your_bonus.pdf", resource);
            javaMailSender.send(message);
            logger.info("activation code sent to user " + eMail);
        } catch (MailException e) {
            logger.warn(e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private ByteArrayResource sendFile(User.Language language) {
        try {
            return new ByteArrayResource(Files.readAllBytes(Paths.get("data/" + language + "/" + "Bonus.pdf")));
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ByteArrayResource("".getBytes());
        }
    }


}
