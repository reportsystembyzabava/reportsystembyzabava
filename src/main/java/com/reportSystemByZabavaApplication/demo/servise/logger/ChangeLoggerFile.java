package com.reportSystemByZabavaApplication.demo.servise.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Thealeshka on 11.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise.logger
 */


public class ChangeLoggerFile {
    private static final String logDirectory = "log";
    private static final String logFileName = "logs.log";
    private static final String logDirectoryOld = logDirectory + "/old";

    public static void change() {
        try {
            if (Files.exists(Paths.get(logDirectory + "/" + logFileName))) {
                int numberOfOldLog = 0;
                if (!Files.exists(Paths.get(logDirectoryOld))) {
                    Files.createDirectory(Paths.get(logDirectoryOld));
                }
                while (Files.exists(Paths.get(logDirectoryOld + "/" +
                        "logOld(" + numberOfOldLog + ").log"))) {
                    numberOfOldLog++;
                }
                Files.copy(Paths.get(logDirectory + "/" + logFileName), Paths.get(logDirectoryOld + "/" +
                        "logOld(" + numberOfOldLog + ").log"));
                Files.delete(Paths.get(logDirectory + "/" + logFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
