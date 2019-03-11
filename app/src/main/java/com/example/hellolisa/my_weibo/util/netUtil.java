package com.example.hellolisa.my_weibo.util;

import android.os.Message;
import android.util.Log;

import com.example.hellolisa.my_weibo.db.pinglun;
import com.example.hellolisa.my_weibo.db.webook;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HELLOLISA on 2018/12/22.
 */

public class netUtil {

    public ArrayList<webook>  _data = new ArrayList<webook>();

    public netUtil() {
    }

    public String request_String;

    String nothing;

    android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(android.os.Message msg)
        {
            Log.i("====",request_String);
            ArrayList<String> data = StringUtil.getXmls(request_String, "WEBOOK");

            for(String string: data)
            {
                webook nbook = new webook();
                nbook.setHead_img(StringUtil.getXmlInt(string, "HEAD_IMG"));
                nbook.setUser_name(StringUtil.getXml(string, "USER_NAME"));
                nbook.setSend_time(StringUtil.getXml(string, "SEND_TIME"));
                nbook.setTest(StringUtil.getXml(string, "TEST"));;
                nbook.setGood_num(StringUtil.getXmlInt(string, "GOOD_NUM"));
                nbook.setPinglun_num(StringUtil.getXmlInt(string, "PINGLUN_NUM"));
                nbook.setZhuanfa_num(StringUtil.getXmlInt(string, "ZHUANGFA_NUM"));

                List<webook> webooki = DataSupport.where("user_name = ? and test = ?" , StringUtil.getXml(string, "USER_NAME"), StringUtil.getXml(string, "TEST")).find(webook.class);

                if(webooki.isEmpty())
                {
                    nbook.save();

                }

                _data.add(nbook);
            }
            Collections.reverse(_data);

        }
    };

    public void sendRequestWithOkHttp( String table) {
        final String table_name = table;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://iwxyi.com/weibo/download_" + table_name + ".php")
                            .build();
                    Response response = client.newCall(request).execute();
                    request_String = response.body().string();
                    Message msg = Message.obtain();
                    msg.obj = request;
                    handler.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }





    public void sendRequestWithOkHttpup(String table, webook webook1){
        final String table_name = table;
        final webook webook = webook1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://iwxyi.com/weibo/upload_" + table_name + ".php?id=0&head_img=" + webook.getHead_img() +
                                    "&user_name=" + webook.getUser_name() + "&test="+webook.getTest() + "&send_time="+webook.getSend_time() +
                                    "&good_num=" + 0+ "&pinglun_num=" + 0+ "&good_num=" + 0)
                            .build();
                    Response response = client.newCall(request).execute();
                    nothing = response.body().string();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void AddsendRequestWithOkHttpup(String table){
        Log.i("====", "进入");
        final String table_name = table;
        final Add add = new Add();
        Log.i("====", "歇了");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 26; i < 50 ; i++) {
                        webook webook = new webook();
                       // webook.setHead_img(add.img2[i]);
                        webook.setUser_name(add.username2[i]);
                        Log.i("====", webook.getUser_name());
                        webook.setTest(add.test[i]);
                        webook.setSend_time(add.sandtime[i]);
                        int min=0;
                        int max=40;
                        Random random = new Random();
                        int num = random.nextInt(max)%(max-min+1) + min;
                        webook.setGood_num(num);
                        webook.setZhuanfa_num(0);
                        webook.setPinglun_num(0);
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://iwxyi.com/weibo/upload_webook.php?id=0&head_img=" + 0 +
                                        "&user_name=" + webook.getUser_name() + "&test=" + webook.getTest() + "&send_time=" + webook.getSend_time() +
                                        "&good_num=" + webook.getGood_num() + "&pinglun_num=" + webook.getPinglun_num() + "&good_num=" + webook.getZhuanfa_num())
                                .build();
                        Response response = client.newCall(request).execute();
                        nothing = response.body().string();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }



    public void downloadwebook(String user_name){
        sendRequestWithOkHttp("webook");


    }

    public void uptowebook(webook webook){
        sendRequestWithOkHttpup("webook", webook);

        Log.i("====",webook.getTest());

    }

    public void uptopinglun(pinglun pinglun1){
        sendRequestWithOkHttpup("pinglun", pinglun1);

    }

    public void sendRequestWithOkHttpup(String table, pinglun pinglun1){
        final String table_name = table;
        final pinglun webook = pinglun1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://iwxyi.com/weibo/upload_" + table_name + ".php?pinglun_id=0&wb_id=0&send_user_name=" + webook.getSend_user_name() +
                                    "&test="+webook.getTest() + "&send_time="+webook.getSend_time() +
                                    "&be_test="+ webook.getBe_test() + "&besend_user_name"+ webook.getBesend_user_name())
                            .build();
                    Response response = client.newCall(request).execute();
                    nothing = response.body().string();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
