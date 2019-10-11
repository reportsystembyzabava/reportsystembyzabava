package com.reportSystemByZabavaApplication.demo;


import com.reportSystemByZabavaApplication.demo.servise.logger.ChangeLoggerFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReportSystemByZabavaApplication {

    public static void main(String[] args) {
        ChangeLoggerFile.change();
        Logger logger = LoggerFactory.getLogger(ReportSystemByZabavaApplication.class);
        logger.info("Application starting");
        SpringApplication.run(ReportSystemByZabavaApplication.class, args);
        logger.info("Application has been started");
    }
}
