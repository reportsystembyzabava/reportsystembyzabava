package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.ConfirmationJpnRepository;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.UserJpaRepository;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.JSONBuilder;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.containers.LoginContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thealeshka on 10.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.controllers
 */

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConfirmationJpnRepository confirmationJpnRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public LoginController(ConfirmationJpnRepository confirmationJpnRepository, UserJpaRepository userJpaRepository) {
        this.confirmationJpnRepository = confirmationJpnRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public LoginController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody LoginContainer loginContainer) {
        logger.info("user login " + loginContainer.getLogin());
        User user = userJpaRepository.findByeMail(loginContainer.getLogin());
        if (user == null) {
            logger.info("no such user "+loginContainer.getLogin());
            return JSONBuilder.create().add("status", "false")
                    .add("error", "no such user").get();
        }
        if (user.getConfirm().isSuccess()) {
            if (user.getPassword().equals(loginContainer.getPassword())) {
                logger.info("login ok "+loginContainer.getLogin());
                return JSONBuilder.create().add("status", "true").add("userToken", user.getUserToken()).get();
            } else {
                logger.info("incorrect password "+loginContainer.getLogin());
                return JSONBuilder.create().add("status", "false")
                        .add("error", "incorrect password").get();
            }
        } else {
            logger.info("not confirm account "+loginContainer.getLogin());
            return JSONBuilder.create().add("status", "false")
                    .add("error", "not confirm account").get();
        }

    }
}
