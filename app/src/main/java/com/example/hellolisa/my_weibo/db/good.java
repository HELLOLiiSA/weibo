package com.example.hellolisa.my_weibo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by HELLOLISA on 2018/12/17.
 */

public class good extends DataSupport {
    private int good_id;//赞的ID

    private int wb_id;//被点赞微博的ID

    private String send_user_name;//点赞人

    private String send_time;//点赞时间

    private String besend_user_name;//被点人

}
