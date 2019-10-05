package com.reportsystembyzabava.demo.controllers;


import com.reportsystembyzabava.demo.jpaRepositorys.ChatJpaRepository;
import com.reportsystembyzabava.demo.servise.fileHandler.FileHandler;
import com.reportsystembyzabava.demo.servise.fileHandler.FileHandlerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestFileController {
    private ChatJpaRepository chatJpaRepository;

    @Autowired
    public TestFileController(ChatJpaRepository chatJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
    }

    public TestFileController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        return "Start";
    }

    @RequestMapping(value = "/")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable("fileName") String fileName) {

    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String handlerFileUpload() {
        return "Upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public @ResponseBody
    String handlerFileUpload(@ModelAttribute("file") MultipartFile file) {


        return new FileHandlerImp().saveFile(file);
    }

}
