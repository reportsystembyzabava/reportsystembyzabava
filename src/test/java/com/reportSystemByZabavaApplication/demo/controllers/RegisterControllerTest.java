package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.containers.MailJSON;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class RegisterControllerTest {
    @Autowired
    private RegisterController sut;

    @Test
    @DisplayName("registerStepOne")
    void registerStepOne() {
        sut.registerStepOne(new User().setUserName("").setLanguage("").seteMail("sfds@gmail.com"));
    }


    @Test
    @DisplayName("registerStepTwo")
    void registerStepTwo() {
        String a = sut.registerStepOne(new User().setUserName("").setLanguage("").seteMail("sfds@gmail.com"));

        sut.get().forEach(System.out::println);
        MailJSON mailJSON = new MailJSON(new Random().nextInt(9999) + "",
                a.substring(a.indexOf("userToken") + "userToken".length() + 4, a.indexOf("}") - 2));
        System.out.println(mailJSON.getCode());
       sut.registerStepTwo(mailJSON);
    }
}