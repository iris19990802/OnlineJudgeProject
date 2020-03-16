package com.iris.java.onlinejudge.web.pojo.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    @Id
    @Column(name = "user_id")
    private String userId;


    @Column(name = "user_name")
    private String userName;


    @Column(name = "user_password")
    private String userPasssord;


    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_register_date")
    private Date userRegisterDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasssord() {
        return userPasssord;
    }

    public void setUserPasssord(String userPasssord) {
        this.userPasssord = userPasssord;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(Date userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }
}