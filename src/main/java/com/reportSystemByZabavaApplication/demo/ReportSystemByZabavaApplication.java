package com.reportSystemByZabavaApplication.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReportSystemByZabavaApplication {
    private final static Logger logger = LoggerFactory.getLogger(ReportSystemByZabavaApplication.class);

    public static void main(String[] args) {
        logger.info("Application starting");
        SpringApplication.run(ReportSystemByZabavaApplication.class, args);
        logger.info("Application has been started");
    }
}
