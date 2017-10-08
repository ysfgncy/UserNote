package com.samatya.yusuf.models;

public class User {
    int id;
    String userName;
    String passWord;

    public User() {
    }

    public User(int id, String userName, String passWord) {
        this.id=id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
