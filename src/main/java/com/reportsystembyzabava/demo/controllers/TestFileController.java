package com.reportsystembyzabava.demo.controllers;


import com.reportsystembyzabava.demo.entity.FileEntity;
import com.reportsystembyzabava.demo.jpaRepositorys.ChatJpaRepository;
import com.reportsystembyzabava.demo.jpaRepositorys.FileJpaRepository;
import com.reportsystembyzabava.demo.jpaRepositorys.UserJpaRepository;
import com.reportsystembyzabava.demo.servise.fileHandler.FileHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

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

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> handlerFileDownload() {
        FileEntity fileEntity = fileJpaRepository.findAllById(Collections.singleton(1L)).get(0);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fileEntity.getFile()));
        MediaType mediaType = MediaType.valueOf("application/" + fileEntity.getNameForUsers().substring(fileEntity.getNameForUsers().indexOf('.')));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileEntity.getNameForUsers())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(fileEntity.getSize()) //
                .body(resource);
    }

//    @RequestMapping(value = "download", method = RequestMethod.POST)
//    public String handlerFileDownload() {
//        return "Upload";
//    }


    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String handlerFileUpload() {
        return "Upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public @ResponseBody
    String handlerFileUpload(@ModelAttribute("file") MultipartFile file) {
        System.out.println(file.getClass());
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
