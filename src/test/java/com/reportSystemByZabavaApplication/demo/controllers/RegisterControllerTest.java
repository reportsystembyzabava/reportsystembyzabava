package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    }
}