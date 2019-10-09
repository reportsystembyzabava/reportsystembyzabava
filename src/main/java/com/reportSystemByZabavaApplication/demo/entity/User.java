package com.reportSystemByZabavaApplication.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "usersgdfgdfgdf_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private String userSurname;
    private String eMail;
    private String password;
    private String city;
    private String course;
    private String groupName;
//    @OneToOne
//    private Confirmation confirm;

   // private UserType userType;

//
//    {
//        confirm = new Confirmation();
//    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public User setUserSurname(String userSurname) {
        this.userSurname = userSurname;
        return this;
    }

    public String geteMail() {
        return eMail;
    }

    public User seteMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCourse() {
        return course;
    }

    public User setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getGroup() {
        return groupName;
    }

    public User setGroup(String group) {
        this.groupName = group;
        return this;
    }



    enum UserType {
        Admin, Student, Teacher
    }

}
