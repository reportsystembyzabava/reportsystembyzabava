package com.reportSystemByZabavaApplication.demo.servise.jsonClasses.containers;

/**
 * Created by Thealeshka on 10.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise.jsonClasses.containers
 */


public class LoginContainer {
    private String login;
    private String password;

    public LoginContainer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginContainer() {
    }

    public String getLogin() {
        return login;
    }

    public LoginContainer setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginContainer setPassword(String password) {
        this.password = password;
        return this;
    }
}
