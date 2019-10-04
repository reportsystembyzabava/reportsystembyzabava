package com.reportsystembyzabava.demo.controllers;


import com.reportsystembyzabava.demo.jpaRepositorys.ChatJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestFileController {
    @Autowired
    private ChatJpaRepository chatJpaRepository;

    public TestFileController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        return "Start";
    }
}
