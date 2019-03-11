package com.example.hellolisa.my_weibo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellolisa.my_weibo.db.user;
import com.example.hellolisa.my_weibo.db.webook;
import com.example.hellolisa.my_weibo.util.StringUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HELLOLISA on 2018/12/20.
 */


/*
        * 个人信息页
        * */
public class usermessage extends AppCompatActivity{


    String request_String;
    // list控件
    RecyclerView _lstmWB;
    // 数据
    public ArrayList<webook> _data = new ArrayList<webook>();
    //控件们

    TextView user_name;

    EditText user_sex;

    EditText user_address;

    EditText user_self;

    EditText user_birth;
    //侧滑栏的头像
    ImageView user_head;

    String username;

    String look_user_name;

    String send_uaer_name;

    usermessage.mWBAdapter adapter;

    Button b_quit;

    Button b_write;

    Button b_massage;

    Button b_pinglun;

    Button b_index;

    Button b_updata;

    DrawerLayout mDrawerLayout;
    //侧滑栏的用户名
    TextView dusername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_massage);

        mDrawerLayout =(DrawerLayout) findViewById(R.id.mdraw);

        user_name = (TextView) findViewById(R.id.tmuser_name);

        user_sex = (EditText) findViewById(R.id.medit_sex);

        user_birth = (EditText) findViewById(R.id.medit_birth);

        user_address = (EditText) findViewById(R.id.medit_address);

        user_self = (EditText) findViewById(R.id.medit_self);

        user_head = (ImageView)findViewById(R.id.mimguser);

        b_quit = (Button)findViewById(R.id.mbutton_quitto_login);

        b_write = (Button)findViewById(R.id.mbutton_write);

        b_massage = (Button)findViewById(R.id.mbutton_info) ;

        b_pinglun = (Button)findViewById(R.id.mbutton_massage) ;

        b_index = (Button)findViewById(R.id.mbutton_index);

        b_updata = (Button)findViewById(R.id.button_updata);

        dusername = (TextView) findViewById(R.id.mtuser_name);


        /*
        * 从intent读取user_name和look_user_name
        * 如果lun = NULL
        * 则说明是自己浏览自己的信息，则edit框正常，可以修改基本信息
        * 反之，则是浏览他人信息，edit框不可修改
        * 把当前用户user_name赋予send_user_name
        * */
        Intent intent = getIntent();
        username = intent.getStringExtra("user_name");
        look_user_name = intent.getStringExtra("look_user_name");
        send_uaer_name = username;
        if(!look_user_name.equals("NULL")){
            send_uaer_name = look_user_name;

            user_sex.setFocusableInTouchMode(false);

            user_birth.setFocusableInTouchMode(false);

            user_self.setFocusableInTouchMode(false);

            user_address.setFocusableInTouchMode(false);
        }
        //从Intent当中根据key取得value

        user_name.setText(username);
        dusername.setText(send_uaer_name) ;
        user_head.setImageResource(R.drawable.add_selector);
        //Toast.makeText(index.this, username, Toast.LENGTH_SHORT).show();

        final List<user> loginuser = DataSupport.where("user_name = ?", username).find(user.class);

        user_head.setImageResource(loginuser.get(0).getUser_head());

        user_sex.setText(loginuser.get(0).getUser_sex()+ "  ");

        user_birth.setText(loginuser.get(0).getUser_birth()+ "  ");

        user_self.setText(loginuser.get(0).getUser_self()+ "  ");

        user_address.setText(loginuser.get(0).getUser_address());


        /*
        * 更新个人信息
        * */
        b_updata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user ouser = new user();
                ouser.setUser_name(username);
                ouser.setUser_head(loginuser.get(0).getUser_head());
                ouser.setUser_address(user_address.getText().toString());
                ouser.setUser_sex(user_sex.getText().toString());
                ouser.setUser_birth(user_birth.getText().toString());
                ouser.setUser_self(user_self.getText().toString());

                ouser.updateAll("user_name = ?", username);

                Intent intent = new Intent(usermessage.this,usermessage.class);
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });

        //这里开始事侧滑栏的各个按钮的响应
        b_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermessage.this,start.class);
                startActivity(intent);
                Toast.makeText(usermessage.this, "退出登陆", Toast.LENGTH_SHORT).show();
            }
        });

        b_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermessage.this,send.class);
                intent.putExtra("user_name", send_uaer_name);
                startActivity(intent);
            }
        });

        b_massage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermessage.this,usermessage.class);
                intent.putExtra("user_name", send_uaer_name);
                intent.putExtra("look_user_name", "NULL");
                startActivity(intent);
            }
        });

        b_index.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermessage.this,index.class);
                intent.putExtra("user_name", send_uaer_name);
                startActivity(intent);
            }
        });

        b_pinglun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(usermessage.this,pinglun.class);
                intent.putExtra("user_name", send_uaer_name);
                startActivity(intent);
            }
        });


        //以下是主页面list的布局
        _lstmWB = (RecyclerView)findViewById(R.id.lstmWB);
        // 初始化数据
        initData(username);

        // 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);

        _lstmWB.setLayoutManager(manager);
        // 适配器


    }

    private class mWBHolder extends RecyclerView.ViewHolder{

        View _wbView; // 代表列表的整体
        // 用户名
        TextView _tvname;

        ImageView _imgwb;
        // 发表时间
        TextView _tvtime;
        //正文
        TextView _tvtest;
        // 点赞
        TextView _tvgood;
        // 评论
        TextView _tvpinglun;
        //转发
        TextView _tvzhuanfa;

        public mWBHolder(View view) {
            super(view);
            _wbView = view;
            // 标题
            _imgwb = (ImageView)view.findViewById(R.id.imgmWB);

            _tvname = (TextView)view.findViewById(R.id.tvmname);
            // 最新发言
            _tvtest = (TextView)view.findViewById(R.id.tvmtext);
            // 发言时间
            _tvtime = (TextView)view.findViewById(R.id.tvmtime);
            // 未读的数量
            _tvgood = (TextView)view.findViewById(R.id.tvmgood);
            // 未读的数量
            _tvpinglun = (TextView)view.findViewById(R.id.tvmpinglun);
            // 未读的数量
            _tvzhuanfa = (TextView)view.findViewById(R.id.tvmzhuanfa);
        }


    }

    // 适配器
    private class mWBAdapter extends RecyclerView.Adapter<usermessage.mWBHolder>{
        // 数据
        private List<webook> _data;

        public mWBAdapter(List<webook> _data) {
            this._data = _data;
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }

        // 创建视图
        @Override
        public usermessage.mWBHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activicy_massage_list, parent, false);
            final usermessage.mWBHolder holder = new usermessage.mWBHolder(view);
            // View代表界面


            return holder;
        }


        //跳转到整个微博界面


        // 绑定数据
        @Override
        public void onBindViewHolder(final usermessage.mWBHolder holder, final int position) {
            final webook info = _data.get(position);
            holder._imgwb.setImageResource(info.getHead_img());
            holder._tvname.setText(info.getUser_name());
            holder._tvtime.setText(info.getSend_time());
            holder._tvtest.setText(info.getTest());
            holder._tvgood.setText("点赞："+info.getGood_num());
            holder._tvpinglun.setText("评论："+info.getPinglun_num());
            holder._tvzhuanfa.setText("转发："+info.getZhuanfa_num());

           holder._wbView.setOnLongClickListener(new View.OnLongClickListener(){
               @Override
               public boolean onLongClick(View v) {
                   DataSupport.deleteAll(webook.class, "user_name = ? and test = ?", info.getUser_name(), info.getTest());
                   _data.remove(info);
                   adapter.notifyDataSetChanged();
                   Toast.makeText(usermessage.this, "我删完了", Toast.LENGTH_SHORT).show();

                   return true;
               }
           });




        }



            //跳转到整个微博界面




    }

    android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(android.os.Message msg)
        {
            //log.i("====",request_String);
            ArrayList<String> data = StringUtil.getXmls(request_String, "WEBOOK");

            for(String string: data)
            {
                if(username.equals(StringUtil.getXml(string, "USER_NAME"))) {
                    webook nbook = new webook();
                    List<user> user = DataSupport.where("user_name = ?", username)
                            .find(com.example.hellolisa.my_weibo.db.user.class);
                    nbook.setHead_img(user.get(0).getUser_head());
                    nbook.setUser_name(username);
                    nbook.setSend_time(StringUtil.getXml(string, "SEND_TIME"));
                    nbook.setTest(StringUtil.getXml(string, "TEST"));
                    ;
                    nbook.setGood_num(StringUtil.getXmlInt(string, "GOOD_NUM"));
                    nbook.setPinglun_num(StringUtil.getXmlInt(string, "PINGLUN_NUM"));
                    nbook.setZhuanfa_num(StringUtil.getXmlInt(string, "ZHUANGFA_NUM"));

                    List<webook> webooki = DataSupport.where("user_name = ? and test = ?", StringUtil.getXml(string, "USER_NAME"), StringUtil.getXml(string, "TEST")).find(webook.class);

                    if (webooki.isEmpty()) {
                        nbook.save();
                    }

                    _data.add(nbook);
                }
            }
            Collections.reverse(_data);

            adapter = new usermessage.mWBAdapter(_data);

            _lstmWB.setAdapter(adapter);
        }
    };

    // 初始化数据
    private void initData(String username){
        //final String table_name = table;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://iwxyi.com/weibo/download_" + "webook" + ".php")
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



