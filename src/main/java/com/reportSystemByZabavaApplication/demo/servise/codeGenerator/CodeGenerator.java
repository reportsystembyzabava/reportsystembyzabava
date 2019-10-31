package com.reportSystemByZabavaApplication.demo.servise.codeGenerator;

import java.util.Random;

/**
 * Created by Thealeshka on 19.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise.codeGenerator
 */


public final class CodeGenerator {

    public static String generateCodeStr(int length) {
        return String.valueOf(codeGen(length));
    }

    public static int generateCodeInt(int length) {
        return codeGen(length);
    }

    private static int codeGen(int length) {
        return new Random().nextInt(Double.valueOf(Math.pow(10, length)).intValue());
    }
}
