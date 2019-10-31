package com.reportSystemByZabavaApplication.demo.servise.jsonClasses;

/**
 * Created by Thealeshka on 19.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise.jsonClasses
 */


public class MailJSON {
    private String code;
    private String userToken;

    public MailJSON(String code, String userToken) {
        this.code = code;
        this.userToken = userToken;
    }

    public MailJSON() {
    }

    public String getCode() {
        return code;
    }

    public MailJSON setCode(String code) {
        this.code = code;
        return this;
    }

    public String getUserToken() {
        return userToken;
    }

    public MailJSON setUserToken(String userToken) {
        this.userToken = userToken;
        return this;
    }
}