package com.example.hellolisa.my_weibo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by HELLOLISA on 2018/12/17.
 */

public class pinglun extends DataSupport {
    private int pinglun_id;//评论的ID

    private int wb_id;//被评论的微薄的ID

    private String send_user_name;//发送评论者的用户名

    private String be_test;//被评论的微博的内容

    private String send_time;//发表评论的时间

    private String test;//评论的内容

    private String besend_user_name;//被评论者的用户名

    public int getPinglun_id() {
        return pinglun_id;
    }

    public int getWb_id() {
        return wb_id;
    }

    public String getSend_user_name() {
        return send_user_name;
    }

    public String getSend_time() {
        return send_time;
    }

    public String getBe_test() {
        return be_test;
    }

    public String getTest() {
        return test;
    }

    public String getBesend_user_name() {
        return besend_user_name;
    }

    public void setPinglun_id(int pinglun_id) {
        this.pinglun_id = pinglun_id;
    }

    public pinglun(String send_user_name, String send_time, String test) {
        this.send_user_name = send_user_name;
        this.send_time = send_time;
        this.test = test;
    }

    public void setBe_test(String be_test) {
        this.be_test = be_test;
    }

    public pinglun() {
    }

    public void setWb_id(int wb_id) {
        this.wb_id = wb_id;
    }

    public void setSend_user_name(String send_user_name) {
        this.send_user_name = send_user_name;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setBesend_user_name(String besend_user_name) {
        this.besend_user_name = besend_user_name;
    }
}

