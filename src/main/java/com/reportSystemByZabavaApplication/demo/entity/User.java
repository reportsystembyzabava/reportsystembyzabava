package com.reportSystemByZabavaApplication.demo.entity;

import com.reportSystemByZabavaApplication.demo.entity.userExtraData.Confirmation;

import javax.persistence.*;

@Entity
@Table(name = "users_table", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String userSurname;
    private String userToken;
    @Column(unique = true)
    private String eMail;
    private String password;
    private String city;
    private String course;
    private String groupName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confirm_id")
    private Confirmation confirm;

    private UserType userType;


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

    public String getGroupName() {
        return groupName;
    }

    public User setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public Confirmation getConfirm() {
        return confirm;
    }

    public User setConfirm(Confirmation confirm) {
        this.confirm = confirm;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public User setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public String getUserToken() {
        return userToken;
    }

    public User setUserToken(String userToken) {
        this.userToken = userToken;
        return this;
    }

    public enum UserType {
        Admin, Student, Teacher
    }

}
