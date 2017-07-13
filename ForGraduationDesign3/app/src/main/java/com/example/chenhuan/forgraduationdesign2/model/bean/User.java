package com.example.chenhuan.forgraduationdesign2.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lixu on 2017/4/13.
 */

public class User extends BmobObject {
    private String username;
    private String password;
    private String emailAddress;
    private String telNumber;
    private String userFaceImg;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", userFaceImg='" + userFaceImg + '\'' +
                '}';
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserFaceImg() {
        return userFaceImg;
    }

    public void setUserFaceImg(String userFaceImg) {
        this.userFaceImg = userFaceImg;
    }
}
