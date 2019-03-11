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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellolisa.my_weibo.db.user;
import com.example.hellolisa.my_weibo.db.webook;
import com.example.hellolisa.my_weibo.util.StringUtil;
import com.example.hellolisa.my_weibo.util.netUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.hellolisa.my_weibo.R.id.lstWB;

public class index extends AppCompatActivity {

    // list控件
    RecyclerView _lstWB;
    // 数据
    public ArrayList<webook> _data = new ArrayList<webook>();

    public String request_String;

    netUtil netUtil = new netUtil();
    //侧滑栏
    DrawerLayout mDrawerLayout;

    Button b_quit;

    Button b_write;

    Button b_massage;

    Button b_pinglun;

    Button b_index;
    //侧滑栏的用户名
    TextView user_name;
    //侧滑栏的头像
    ImageView user_head;
    //登陆的用户名
    String username;
    //一个适配器
    WBAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_index);

        //绑定侧滑栏
        mDrawerLayout =(DrawerLayout) findViewById(R.id.draw);
        //绑定各个控件
        user_name = (TextView) findViewById(R.id.tuser_name);

        user_head = (ImageView)findViewById(R.id.imguser);

        b_quit = (Button)findViewById(R.id.button_quitto_login);

        b_write = (Button)findViewById(R.id.button_write);

        b_massage = (Button)findViewById(R.id.button_info) ;

        b_pinglun = (Button)findViewById(R.id.button_massage) ;

        b_index = (Button)findViewById(R.id.button_index);

        Intent intent = getIntent();
        username = intent.getStringExtra("user_name");
        user_name.setText(username);

        List<user> loginuser = DataSupport.where("user_name = ?", username).find(user.class);
        user_head.setImageResource(loginuser.get(0).getUser_head());
        //这里开始事侧滑栏的各个按钮的响应
        //退出
        b_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index.this,start.class);
                startActivity(intent);
                Toast.makeText(index.this, "退出登陆", Toast.LENGTH_SHORT).show();
            }
        });
        //发表新微博
        b_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index.this,send.class);
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });
        //进入首页
        b_index.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index.this,index.class);
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });
        //查看个人信息
        b_massage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index.this,usermessage.class);
                intent.putExtra("user_name", username);
                intent.putExtra("look_user_name", "NULL");
                startActivity(intent);
            }
        });
        //发表评论
        b_pinglun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index.this,pinglun.class);
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });

        //以下是主页面list的布局
        _lstWB = (RecyclerView)findViewById(lstWB);
        // 初始化数据

        // 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);

        _lstWB.setLayoutManager(manager);
        // 适配器
        initData("WEBOOK");
       /*adapter = new WBAdapter(netUtil._data);

        _lstWB.setAdapter(adapter);*/


    }

    private class WBHolder extends RecyclerView.ViewHolder{

        View _wbView; // 代表列表的整体
        // 用户名
        TextView _tvname;
        //头像
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

        //绑定控件

        public WBHolder(View view) {
            super(view);
            _wbView = view;
            // 标题
            _imgwb = (ImageView)view.findViewById(R.id.imgWB);

            _tvname = (TextView)view.findViewById(R.id.tvname);
            // 最新发言
            _tvtest = (TextView)view.findViewById(R.id.tvtext);
            // 发言时间
            _tvtime = (TextView)view.findViewById(R.id.tvtime);
            // 未读的数量
            _tvgood = (TextView)view.findViewById(R.id.tvgood);
            // 未读的数量
            _tvpinglun = (TextView)view.findViewById(R.id.tvpinglun);
            // 未读的数量
            _tvzhuanfa = (TextView)view.findViewById(R.id.tvzhuanfa);
        }


    }

    // 适配器
    private class WBAdapter extends RecyclerView.Adapter<WBHolder>{
        // 数据
        private List<webook> _data;

        public WBAdapter(List<webook> _data) {
            this._data = _data;
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }

        // 创建视图
        @Override
        public WBHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_index_list, parent, false);
            final WBHolder holder = new WBHolder(view);
            // View代表界面

            return holder;
        }


        // 绑定数据
        @Override
        public void onBindViewHolder(WBHolder holder,final int position) {
            final webook info = _data.get(position);
            holder._imgwb.setImageResource(info.getHead_img());
            holder._tvname.setText(info.getUser_name());
            holder._tvtime.setText(info.getSend_time());
            holder._tvtest.setText(info.getTest());
            holder._tvgood.setText("点赞："+info.getGood_num());
            holder._tvpinglun.setText("评论："+info.getPinglun_num());
            holder._tvzhuanfa.setText("转发："+info.getZhuanfa_num());

            /*
            * 给每条微博一个查看详情的点击事件
            *
            * 跳转到显示单独微博的weibo页面
            * */
            holder._wbView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent2 = new Intent(index.this, weibo.class);
                    intent2.putExtra("username", username);
                    intent2.putExtra("user_head", info.getHead_img());
                    intent2.putExtra("user_name", info.getUser_name());
                    intent2.putExtra("send_time", info.getSend_time());
                    intent2.putExtra("test", info.getTest());


                    startActivity(intent2);
                }
            });

            /*查看别人的信息
            *
            *
            */
            holder._tvname.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(index.this, usermessage.class);
                    intent2.putExtra("username", username);
                    intent2.putExtra("look_user_name", info.getUser_name());

                    startActivity(intent2);
                }
            });

        }



    }

    android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(android.os.Message msg)
        {
           //log.i("====",request_String);
            ArrayList<String> data = StringUtil.getXmls(request_String, "WEBOOK");

            for(String string: data)
            {
                webook nbook = new webook();
                nbook.setUser_name(StringUtil.getXml(string, "USER_NAME"));
                List<user> userr = DataSupport.where("user_name = ?", nbook.getUser_name())
                        .find(user.class);
                if(!userr.isEmpty()) {
                    nbook.setHead_img(userr.get(0).getUser_head());
                }
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

            adapter = new WBAdapter(_data);

            _lstWB.setAdapter(adapter);
        }
    };

    private void initData(String table){
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
}
