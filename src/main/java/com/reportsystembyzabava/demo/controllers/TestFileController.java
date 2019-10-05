package com.reportsystembyzabava.demo.controllers;


import com.reportsystembyzabava.demo.entity.FileEntity;
import com.reportsystembyzabava.demo.jpaRepositorys.ChatJpaRepository;
import com.reportsystembyzabava.demo.jpaRepositorys.FileJpaRepository;
import com.reportsystembyzabava.demo.jpaRepositorys.UserJpaRepository;
import com.reportsystembyzabava.demo.servise.fileHandler.FileHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class TestFileController {
    private ChatJpaRepository chatJpaRepository;
    private FileJpaRepository fileJpaRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public TestFileController(ChatJpaRepository chatJpaRepository, FileJpaRepository fileJpaRepository, UserJpaRepository userJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
        this.fileJpaRepository = fileJpaRepository;
        this.userJpaRepository = userJpaRepository;
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
        try {
            FileEntity fileEntity = new FileEntity().setNameForUsers(file.getOriginalFilename())
                    .setSize(file.getSize()).setCheckSum(FileHash.checkSum(file, MessageDigest.getInstance("SHA-256")))
                    .setFile(file.getBytes());
            fileJpaRepository.save(fileEntity);

        } catch (DataIntegrityViolationException e) {
            return "file already loaded";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
