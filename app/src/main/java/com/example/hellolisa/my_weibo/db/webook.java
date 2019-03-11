package com.example.hellolisa.my_weibo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by HELLOLISA on 2018/12/17.
 */

public class webook extends DataSupport {

    private int id;//微博ID

    private int head_img;//用户头像

    private String user_name;//用户名

    private String test;//微博内容

    private String send_time;//发送时间

    private int good_num;//点赞数

    private int pinglun_num;//评论数

    private int zhuanfa_num;//转发数

    public webook(){


    }

    public webook(int head_img, String user_name, String test, String send_time, int good_num, int pinglun_num, int zhuanfa_num) {
        this.head_img = head_img;
        this.user_name = user_name;
        this.test = test;
        this.send_time = send_time;
        this.good_num = good_num;
        this.pinglun_num = pinglun_num;
        this.zhuanfa_num = zhuanfa_num;
    }



    public int getHead_img() { return head_img; }

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getTest() {
        return test;
    }

    public String getSend_time() {
        return send_time;
    }

    public int getGood_num() {
        return good_num;
    }

    public int getPinglun_num() {
        return pinglun_num;
    }

    public int getZhuanfa_num() {
        return zhuanfa_num;
    }

    public void setHead_img(int head_img) { this.head_img = head_img; }

   public void setId(int id) {this.id = id;}

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public void setGood_num(int good_num) {
        this.good_num = good_num;
    }

    public void setPinglun_num(int pinglun_num) {
        this.pinglun_num = pinglun_num;
    }

    public void setZhuanfa_num(int zhuanfa_num) {
        this.zhuanfa_num = zhuanfa_num;
    }
}
