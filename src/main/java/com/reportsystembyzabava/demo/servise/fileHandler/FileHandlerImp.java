package com.reportsystembyzabava.demo.servise.fileHandler;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @Override
    public String checkSum(MultipartFile file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return checkSum(file, digest);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String checkSum(MultipartFile file, MessageDigest md) throws IOException {

        //file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(file.getInputStream(), md)) {
            while (dis.read() != -1) ;//empty loop to clear the data
            md = dis.getMessageDigest();
        }

        //bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

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
        try {
            return checkSum(file, MessageDigest.getInstance("SHA-256")) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
