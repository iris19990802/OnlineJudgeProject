package com.iris.java.onlinejudge.web.pojo.bo;

public class RegisterBO {

    private String userName;
    private String userPasswordMd5_1; // 经过前端一次md5的 userPassword_1
    private String userPasswordMd5_2; // 经过前端一次md5的 userPassword_2
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswordMd5_1() {
        return userPasswordMd5_1;
    }

    public void setUserPasswordMd5_1(String userPasswordMd5_1) {
        this.userPasswordMd5_1 = userPasswordMd5_1;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPasswordMd5_2() {
        return userPasswordMd5_2;
    }

    public void setUserPasswordMd5_2(String userPasswordMd5_2) {
        this.userPasswordMd5_2 = userPasswordMd5_2;
    }
}
