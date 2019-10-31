package com.reportSystemByZabavaApplication.demo.controllers;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.jpaRepositorys.UserJpaRepository;
import com.reportSystemByZabavaApplication.demo.servise.jsonClasses.JSONBuilder;
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
    /**
     *
     */
    private UserJpaRepository userJpaRepository;

    /**
     * @param userJpaRepository
     */
    @Autowired
    public LoginController(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     *
     */
    public LoginController() {
    }

    /**
     * @param userLogin
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public @ResponseBody
    String login(@RequestBody User userLogin) {
        String result;
        logger.info("user login " + userLogin.geteMail());
        User user = userJpaRepository.findByeMail(userLogin.geteMail());
        if (user == null) {
            logger.info("no such user " + userLogin.geteMail());
            result = JSONBuilder.create().add("status", "false")
                    .add("error", "no such user").get();
        } else if (user.getConfirm().isSuccess()) {
            if (userLogin.getPassword().equals(user.getPassword())) {
                logger.info("login ok " + user.geteMail());
                result = JSONBuilder.create().add("status", "true").add("userToken", user.getUserToken()).get();
            } else {
                logger.info("incorrect password " + userLogin.geteMail());
                result = JSONBuilder.create().add("status", "false")
                        .add("error", "incorrect password").get();
            }
        } else {
            logger.info("not confirm account " + userLogin.geteMail());
            result = JSONBuilder.create().add("status", "false")
                    .add("error", "not confirm account").get();
        }
        return result;
    }
}
