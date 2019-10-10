package com.reportSystemByZabavaApplication.demo.servise.fileGetHashSum;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class Hash {

    public static String checkSum(MultipartFile file, MessageDigest md) throws IOException {
        return checkSumIn(file.getInputStream(), md);
    }

    public static String checkSum(File file, MessageDigest md) throws IOException {
        return checkSumIn(new FileInputStream(file), md);
    }

    public static String checkSum(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        return checkSumIn(file.getInputStream(), MessageDigest.getInstance("SHA-256"));
    }

    public static String checkSum(File file) throws IOException, NoSuchAlgorithmException {
        return checkSumIn(new FileInputStream(file), MessageDigest.getInstance("SHA-256"));
    }

    public static String checkSum(String string, MessageDigest md) throws IOException {
        return checkSumIn(new ByteArrayInputStream(string.getBytes()), md);
    }

    private static String checkSumIn(InputStream inputStream, MessageDigest messageDigest) {
        try (DigestInputStream dis = new DigestInputStream(inputStream, messageDigest)) {
            while (dis.read() != -1) ;//empty loop to clear the data
            messageDigest = dis.getMessageDigest();
            //bytes to hex
            StringBuilder result = new StringBuilder();
            for (byte b : messageDigest.digest()) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
