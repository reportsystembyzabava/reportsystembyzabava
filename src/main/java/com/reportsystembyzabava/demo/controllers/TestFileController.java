package com.reportsystembyzabava.demo.controllers;


import com.reportsystembyzabava.demo.entity.FileEntity;
import com.reportsystembyzabava.demo.entity.UserEntity;
import com.reportsystembyzabava.demo.jpaRepositorys.ChatJpaRepository;
import com.reportsystembyzabava.demo.jpaRepositorys.FileJpaRepository;
import com.reportsystembyzabava.demo.jpaRepositorys.UserJpaRepository;
import com.reportsystembyzabava.demo.servise.fileHandler.FileHandlerImp;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        System.out.println(new FileHandlerImp().checkSum(file));
        FileEntity fileEntity = new FileEntity(new FileHandlerImp().checkSum(file));

        try {
            userJpaRepository.save(new UserEntity(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileJpaRepository.save(fileEntity);
            fileJpaRepository.save(fileEntity.setNameForUsers(file.getOriginalFilename())
                    .setnameInStorage(new FileHandlerImp().saveFile(file)).setSize(file.getSize()));
        } catch (DataIntegrityViolationException e) {
            return "file already loaded";
        } catch (Exception e) {
            if (e.getClass() != (PSQLException.class)) {
                System.out.println(e.getClass());
                throw e;
            } else {
                return "file already loaded";
            }
        }

        return "ok";
    }

}
