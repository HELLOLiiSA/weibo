package com.example.hellolisa.my_weibo;

import android.content.Intent;
import android.os.Bundle;
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

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HELLOLISA on 2018/12/20.
 */


/*
* 显示当前用户收到的消息
* */
public class pinglun extends AppCompatActivity{
    // list控件
    RecyclerView _lstPL;
    // 数据
    List<com.example.hellolisa.my_weibo.db.pinglun> _data;

    pinglun.PLAdapter adapter;


    /*
    * 声明
    * */
    String user_name;

    Button b_quit;

    Button b_write;

    Button b_massage;

    Button b_pinglun;

    Button b_index;

    DrawerLayout mDrawerLayout;
    //侧滑栏的用户名
    TextView username;
    //侧滑栏的头像
    ImageView user_head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);

        /*
        * 所有控件的绑定
        * */

        mDrawerLayout =(DrawerLayout) findViewById(R.id.pdraw);

        b_quit = (Button)findViewById(R.id.pbutton_quitto_login);

        b_write = (Button)findViewById(R.id.pbutton_write);

        b_massage = (Button)findViewById(R.id.pbutton_info) ;

        b_pinglun = (Button)findViewById(R.id.pbutton_massage) ;

        b_index = (Button)findViewById(R.id.pbutton_index);

        username = (TextView) findViewById(R.id.ptuser_name);

        user_head = (ImageView)findViewById(R.id.pimguser);

        /*
        *
        * */
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        if (intent != null) {
            username.setText(user_name);
        }


        //这里开始事侧滑栏的各个按钮的响应
        b_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pinglun.this,start.class);
                startActivity(intent);
                Toast.makeText(pinglun.this, "退出登陆", Toast.LENGTH_SHORT).show();
            }
        });

        b_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pinglun.this,send.class);
                intent.putExtra("user_name", user_name);
                startActivity(intent);
            }
        });

        b_index.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pinglun.this,index.class);
                intent.putExtra("user_name", user_name);
                startActivity(intent);
            }
        });

        b_massage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pinglun.this,usermessage.class);
                intent.putExtra("user_name", user_name);
                intent.putExtra("look_user_name", "NULL");
                startActivity(intent);
            }
        });

        b_pinglun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pinglun.this,pinglun.class);
                intent.putExtra("user_name", user_name);
                startActivity(intent);
            }
        });



        _lstPL = (RecyclerView)findViewById(R.id.lstmPL);
        // 初始化数据
        initData(user_name);

        // 布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);

        _lstPL.setLayoutManager(manager);
        // 适配器
        adapter = new pinglun.PLAdapter(_data);

        _lstPL.setAdapter(adapter);

    }

    private class PLHolder extends RecyclerView.ViewHolder{

        View _wbView; // 代表列表的整体
        // 头像
        ImageView _lvpl;
        //用户名
        TextView _tvname;
        //发表时间
        TextView _tvtime;
        //正文
        TextView _tvtest;
        //被评论用户名
        TextView _tvbename;
        //被评论
        TextView _tvbetest;

        public PLHolder(View view) {
            super(view);
            _wbView = view;
            // 标题




            _lvpl = (ImageView)view.findViewById(R.id.imgpluser) ;
            _tvname = (TextView)view.findViewById(R.id.wb_tvplname);
            // 最新发言
            _tvtest = (TextView)view.findViewById(R.id.tvpinglun);
            // 发言时间
            _tvtime = (TextView)view.findViewById(R.id.wb_tvpltime);

            _tvbename = (TextView)view.findViewById(R.id.wb_tvbeplname);
            // 最新发言
            _tvbetest = (TextView)view.findViewById(R.id.tvbepinglun);
            // 发言时间
        }


    }

    // 适配器
    private class PLAdapter extends RecyclerView.Adapter<pinglun.PLHolder> {
        // 数据
        private List<com.example.hellolisa.my_weibo.db.pinglun> _data;

        public PLAdapter(List<com.example.hellolisa.my_weibo.db.pinglun> _data) {
            this._data = _data;
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }

        // 创建视图
        @Override
        public pinglun.PLHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_webook_list, parent, false);
            final pinglun.PLHolder holder = new pinglun.PLHolder(view);
            // View代表界面


            return holder;
        }


        //跳转到整个微博界面


        // 绑定数据
        @Override
        public void onBindViewHolder(pinglun.PLHolder holder, final int position) {
            final com.example.hellolisa.my_weibo.db.pinglun info = _data.get(position);

            /*
            * 给评论读取用户头像
            * */
            List<user> loginuser = DataSupport.where("user_name = ?", info.getSend_user_name()).find(user.class);

            holder._tvname.setText(info.getSend_user_name());
            holder._tvtime.setText(info.getSend_time());
            holder._tvtest.setText(info.getTest());
            holder._tvbename.setText("To:" + info.getBesend_user_name());
            holder._tvbetest.setText(info.getBe_test());

            holder._lvpl.setImageResource(loginuser.get(0).getUser_head());


        }
    }





    // 初始化数据
    private void initData(String username){
        _data = new ArrayList<com.example.hellolisa.my_weibo.db.pinglun>();
        List<com.example.hellolisa.my_weibo.db.pinglun> pingluns = DataSupport.where("besend_user_name = ?" , username).find(com.example.hellolisa.my_weibo.db.pinglun.class);
        for(com.example.hellolisa.my_weibo.db.pinglun pinglun:pingluns)
        {

            _data.add(pinglun);

        }
        Collections.reverse(_data);

    }
}
