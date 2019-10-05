package com.reportsystembyzabava.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class ReportsystembyzabavaApplication {

    public static void main(String[] args) throws IOException {
        //Files.createDirectory(Paths.get("test"));
        SpringApplication.run(ReportsystembyzabavaApplication.class, args);
    }

}
