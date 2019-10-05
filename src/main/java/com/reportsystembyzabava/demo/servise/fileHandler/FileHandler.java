package com.reportsystembyzabava.demo.servise.fileHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileHandler {


    String saveFile(MultipartFile file);

    MultipartFile findFile(String fileName);

    List<MultipartFile> findAllFiles();

}
