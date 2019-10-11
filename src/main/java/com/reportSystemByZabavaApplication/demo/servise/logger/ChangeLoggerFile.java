package com.reportSystemByZabavaApplication.demo.servise.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
                List<Path> pathList = Files.list(Paths.get(logDirectoryOld)).filter(Files::isRegularFile)
                        .sorted(Comparator.comparing(Path::getFileName)).collect(Collectors.toList());
                if (pathList.size() != 0) {
                    numberOfOldLog = Integer.parseInt(pathList.get(pathList.size() - 1).getFileName().toString()
                            .substring(pathList.get(pathList.size() - 1).getFileName().toString()
                                    .lastIndexOf("(") + 1, pathList.get(pathList.size() - 1).getFileName()
                                    .toString().lastIndexOf(")")));
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
