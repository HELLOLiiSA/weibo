package com.example.hellolisa.my_weibo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellolisa.my_weibo.db.pinglun;
import com.example.hellolisa.my_weibo.db.user;
import com.example.hellolisa.my_weibo.db.webook;
import com.example.hellolisa.my_weibo.util.DatetimeUtil;
import com.example.hellolisa.my_weibo.util.StringUtil;
import com.example.hellolisa.my_weibo.util.netUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HELLOLISA on 2018/12/19.
 */

/*
        * 现实显示微博单独页面
        * */

public class weibo extends AppCompatActivity{

    // list控件
    RecyclerView _lstPL;

    String request_String;
    // 数据
    ArrayList<pinglun> _data = new ArrayList<pinglun>();

    PLAdapter adapter;

    ImageView wb_img;
    TextView wb_name;
    TextView wb_time;
    TextView wb_test;
    Button givegood;
    Button givepinglun;
    Button zhuanfa;
    Button back;
    EditText tvpltext;

    String username;

    String user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webook);

        wb_img = (ImageView)findViewById(R.id.wb_imgWB);
        wb_name = (TextView)findViewById(R.id.wb_tvname);
        wb_time = (TextView)findViewById(R.id.wb_tvtime);
        wb_test = (TextView)findViewById(R.id.wb_tvtext);

        givegood = (Button)findViewById(R.id.tvgoodbutton);
        givepinglun = (Button)findViewById(R.id.tvplbutton);
        zhuanfa = (Button)findViewById(R.id.tvzfbutton);
        back = (Button)findViewById(R.id.tvbackpl);

        tvpltext = (EditText)findViewById(R.id.tvpltext);

        Intent intent = getIntent();
        username = intent.getStringExtra("user_name");
        final String time = intent.getStringExtra("send_time");
        final String test = intent.getStringExtra("test");
        user_name = intent.getStringExtra("username");
        final int img = intent.getIntExtra("user_head", R.drawable.add_selector);

        wb_img.setImageResource(img);
        wb_name.setText(username);
        wb_time.setText(time);
        wb_test.setText(test);
        List<webook> webooki = DataSupport.where("user_name = ? and test = ?" , username, test).find(webook.class);
        givegood.setText("点赞："+ webooki.get(0).getGood_num());

        givegood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(weibo.this, "点赞成功", Toast.LENGTH_SHORT).show();
                List<webook> webooki = DataSupport.where("user_name = ? and test = ?" , username, test).find(webook.class);
                webooki.get(0).setGood_num(webooki.get(0).getGood_num() + 1);
                webooki.get(0).updateAll("user_name = ? and test = ?" , username, test);

                Intent intent2 = new Intent(weibo.this, weibo.class);
                intent2.putExtra("username", user_name);
                intent2.putExtra("user_head", img);
                intent2.putExtra("user_name", username);
                intent2.putExtra("send_time", time);
                intent2.putExtra("test", test);


                startActivity(intent2);

            }
        });

        /*
        * 点击用户名可以查看该用户的信息
        * */
        wb_name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(weibo.this, usermessage.class);
                intent2.putExtra("user_name", username);
                intent2.putExtra("look_user_name", user_name);

                startActivity(intent2);
            }
        });

        /*
        * 走了
        * */
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(start.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(weibo.this, index.class);
                intent2.putExtra("user_name", user_name);


                startActivity(intent2);
            }
        });

        /*
        * 评论
        * */
        givepinglun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(start.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                pinglun npinglun = new pinglun();
                npinglun.setBe_test(test);
                npinglun.setTest(tvpltext.getText().toString());
                npinglun.setBesend_user_name(username);
                npinglun.setSend_user_name(user_name);
                npinglun.setSend_time(DatetimeUtil.getNowDateTime());
                npinglun.save();

                Toast.makeText(weibo.this, "评论成功", Toast.LENGTH_SHORT).show();

                List<webook> webooki = DataSupport.where("user_name = ? and test = ?" , username, test).find(webook.class);
                webooki.get(0).setPinglun_num(webooki.get(0).getPinglun_num() + 1);
                webooki.get(0).updateAll("user_name = ? and test = ?" , username, test);

                netUtil netUtil = new netUtil();
                netUtil.uptopinglun(npinglun);

                Intent intent2 = new Intent(weibo.this, weibo.class);
                intent2.putExtra("username", user_name);
                intent2.putExtra("user_head", img);
                intent2.putExtra("user_name", username);
                intent2.putExtra("send_time", time);
                intent2.putExtra("test", test);


                startActivity(intent2);
            }
        });


        /*
        * 转发
        * 内容为 输入框内容+//@ + 被转发用户名： + 被转发微博内容
        * */
        zhuanfa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                webook webook = new webook();
                webook.setHead_img(R.drawable.logo_s);
                webook.setUser_name(user_name);
                webook.setSend_time(DatetimeUtil.getNowDateTime());
                webook.setTest(tvpltext.getText().toString() + "//@"+username+ ":" +test);
                webook.setGood_num(0);
                webook.setPinglun_num(0);
                webook.setZhuanfa_num(0);

                webook.save();

                netUtil netUtil = new netUtil();
                netUtil.uptowebook(webook);

                Toast.makeText(weibo.this, "转发成功", Toast.LENGTH_SHORT).show();
                List<webook> webooki = DataSupport.where("user_name = ? and test = ?" , username, test).find(webook.class);
                webooki.get(0).setZhuanfa_num(webooki.get(0).getZhuanfa_num() + 1);
                webooki.get(0).updateAll("user_name = ? and test = ?" , username, test);
                Intent intent = new Intent(weibo.this,index.class);
                intent.putExtra("user_name", user_name);
                startActivity(intent);
            }
        });


        _lstPL = (RecyclerView)findViewById(R.id.lstPL);
        // 初始化数据
        initData();

        // 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);

        _lstPL.setLayoutManager(manager);
        // 适配器

    }

    private class PLHolder extends RecyclerView.ViewHolder{

        View _wbView; // 代表列表的整体
        // 用户名

        ImageView _ivpl;
        TextView _tvname;

        TextView _tvtime;
        //正文
        TextView _tvtest;
        // 点赞

        public PLHolder(View view) {
            super(view);
            _wbView = view;
            // 标题


            _ivpl = (ImageView)view.findViewById(R.id.imgpluser) ;
            _tvname = (TextView)view.findViewById(R.id.wb_tvplname);
            // 最新发言
            _tvtest = (TextView)view.findViewById(R.id.tvpinglun);
            // 发言时间
            _tvtime = (TextView)view.findViewById(R.id.wb_tvpltime);


        }


    }

    // 适配器
    private class PLAdapter extends RecyclerView.Adapter<weibo.PLHolder> {
        // 数据
        private List<pinglun> _data;

        public PLAdapter(List<pinglun> _data) {
            this._data = _data;
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }

        // 创建视图
        @Override
        public weibo.PLHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_webook_list, parent, false);
            final weibo.PLHolder holder = new weibo.PLHolder(view);
            // View代表界面


            return holder;
        }


        //跳转到整个微博界面


        // 绑定数据
        @Override
        public void onBindViewHolder(weibo.PLHolder holder, final int position) {
            final pinglun info = _data.get(position);

            List<user> loginuser = DataSupport.where("user_name = ?", info.getSend_user_name()).find(user.class);

            holder._tvname.setText(info.getSend_user_name());
            holder._tvtime.setText(info.getSend_time());
            holder._tvtest.setText(info.getTest());
            if(!loginuser.isEmpty()) {
                holder._ivpl.setImageResource(loginuser.get(0).getUser_head());
            }


        }
    }





    /*// 初始化数据
    private void initData(String username, String test){
        _data = new ArrayList<pinglun>();
        List<pinglun> pingluns = DataSupport.where("besend_user_name = ? and be_test = ?", username, test).find(pinglun.class);
        for(pinglun pinglun:pingluns)
        {

            _data.add(pinglun);

        }
        Collections.reverse(_data);

    }*/

    android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(android.os.Message msg)
        {
            ArrayList<String> data = StringUtil.getXmls(request_String, "PINGLUN");

            for(String string: data)
            {
                Log.i("====", request_String);
                pinglun npinglun = new pinglun();
                npinglun.setSend_user_name(StringUtil.getXml(string, "SNED_USER_NAME"));
                npinglun.setBe_test(StringUtil.getXml(string, "BE_TEST"));
                npinglun.setSend_time(StringUtil.getXml(string, "SEND_TIME"));
                npinglun.setTest(StringUtil.getXml(string, "TEST"));;


                    _data.add(npinglun);

            }
            Collections.reverse(_data);

            adapter = new weibo.PLAdapter(_data);

            _lstPL.setAdapter(adapter);
        }
    };

    private void initData(){
        final String table_name = "pinglun";
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

}


