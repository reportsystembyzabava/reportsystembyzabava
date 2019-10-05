package com.reportsystembyzabava.demo.servise.fileHandler;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FileHandlerImp implements FileHandler {

    private final String directoryName = "storage";

    public FileHandlerImp() {
    }

    {
        checkForDirectory();
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String fileName = createFileName(file);
                Files.write(Paths.get(directoryName + "/" + fileName), file.getBytes());
                return fileName;
            } else {
                return "File is empty";
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public MultipartFile findFile(String fileName) {

        return null;
    }

    @Override
    public List<MultipartFile> findAllFiles() {
        return null;
    }

    private void checkForDirectory() {
        try {
            if (!Files.isDirectory(Paths.get(directoryName))) {
                Files.createDirectory(Paths.get(directoryName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createFileName(MultipartFile file) {
        return file.toString().hashCode() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
    }


}
