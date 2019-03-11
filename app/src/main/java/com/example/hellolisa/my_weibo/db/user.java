package com.example.hellolisa.my_weibo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by HELLOLISA on 2018/12/17.
 */

public class user extends DataSupport{
    private int id;//用户id

    private int user_head;//用户头像

    private String user_name;//用户名

    private String user_password;//密码

    private String user_sex;//性别

    private String user_birth;//生日

    private String user_address;//地址

    private String user_self;//个人简介

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public String getUser_address() {
        return user_address;
    }

    public String getUser_self() {
        return user_self;
    }

    public int getUser_head() {
        return user_head;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public void setUser_self(String user_self) {
        this.user_self = user_self;
    }

    public void setUser_head(int user_head) {
        this.user_head = user_head;
    }
}
