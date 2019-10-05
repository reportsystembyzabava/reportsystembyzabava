package com.reportsystembyzabava.demo.servise.fileHandler;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class FileHash {

    public static String checkSum(MultipartFile file, MessageDigest md) throws IOException {
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

    public static String checkSum(File file, MessageDigest md) throws IOException {
        //file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md)) {
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

    public static String checkSum(MultipartFile file) throws IOException, NoSuchAlgorithmException {

        //file hashing with DigestInputStream
        MessageDigest md = MessageDigest.getInstance("SHA-256");
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
    public static String checkSum(File file) throws IOException, NoSuchAlgorithmException {
        //file hashing with DigestInputStream
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md)) {
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
}
