package com.reportSystemByZabavaApplication.demo.servise;

import com.reportSystemByZabavaApplication.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Thealeshka on 19.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise
 */


public class MailSender {
    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    private static void sendEMail(String eMail, String subject, String text, JavaMailSender javaMailSender) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(eMail);
            helper.setSubject(subject);
            helper.setText(text);
            //javaMailSender.send(message);
            //logger.info("activation code sent to user " + eMail);
        } catch (MailException e) {
            //logger.warn(e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sentEMail(String eMail, String subject, String text, ByteArrayResource resource,
                                  JavaMailSender javaMailSender) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(eMail);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("Your_bonus.pdf", resource);
            //javaMailSender.send(message);
            logger.info("activation code sent to user " + eMail);
        } catch (MailException e) {
            logger.warn(e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static ByteArrayResource sendFile(User.Language language, String bonusFileURL) {
        try {
            return new ByteArrayResource(Files.readAllBytes(Paths.get("data/" + language + "/" + bonusFileURL)));
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ByteArrayResource("".getBytes());
        }
    }

    public static void sendRegisterPass(String eMail, User.Language language, String subject, String fileURL,
                                        JavaMailSender javaMailSender, String bonusFileURL) {
        sentEMail(eMail, generateSubject(language, subject), generateText(language, fileURL),
                sendFile(language, bonusFileURL), javaMailSender);
    }


    public static void sendCode(String eMail, User.Language language, String subject, String fileURL,
                                JavaMailSender javaMailSender, String name, String code) {
        sendEMail(eMail, generateSubject(language, subject), replaceName(generateTextWithCode(language, fileURL, code), name, language),
                javaMailSender);
    }

    private static String generateText(User.Language language, String fileURL) {
        try {
            StringBuilder result = new StringBuilder();
            Files.readAllLines(Paths.get("data/" + language + "/" + fileURL)).forEach(n -> result.append(n + "\n"));
            logger.info("text generate OK");
            return result.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "Sorry we have technical problems";
    }

    private static String generateTextWithCode(User.Language language, String fileURL, String code) {
        try {
            StringBuilder result = new StringBuilder();
            Files.readAllLines(Paths.get("data/" + language + "/" + fileURL)).forEach(n -> result.append(n + "\n"));
            try {
                result.replace(result.indexOf("*+|+*"), "*+|+*".length() + result.indexOf("*+|+*"), "\n" + code);
            } catch (StringIndexOutOfBoundsException e) {
            }
            logger.info("text generate OK");
            return result.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "Sorry we have technical problems";
    }

    private static String generateSubject(User.Language language, String fileURL) {
        try {
            StringBuilder result = new StringBuilder();
            Files.readAllLines(Paths.get("data/" + language + "/" + fileURL)).forEach(n -> result.append(n));
            logger.info("subject generate OK");
            return result.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "Sorry we have technical problems";
    }

    private static String replaceName(String text, String name, User.Language language) {
        switch (language) {
            case RU:
                return text.replaceAll("друг", name);
            case ENG:
                return text.replaceAll("friend", name);
            case UA:
                return text.replaceAll("друже", name);
            default:
                return text;
        }
    }
}
