package com.example.hellolisa.my_weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellolisa.my_weibo.db.user;
import com.example.hellolisa.my_weibo.db.webook;
import com.example.hellolisa.my_weibo.util.DatetimeUtil;
import com.example.hellolisa.my_weibo.util.netUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import static android.text.TextUtils.isEmpty;

/**
 * Created by HELLOLISA on 2018/12/19.
 */


/*
*
* 发表新的微博以及相关UI
*
* */
public class send extends AppCompatActivity{

    private Button b_quit;
    private Button b_send;
    private TextView user_name;
    private EditText send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send);

        b_quit = (Button)findViewById(R.id.button_quitto_index);
        b_send = (Button)findViewById(R.id.button_send);
        user_name = (TextView)findViewById(R.id.send_name);
        send = (EditText)findViewById(R.id.edit_send);

        Intent intent2 = getIntent();
        final String username;
        username = intent2.getStringExtra("user_name");
        //从Intent当中根据key取得value
        if (intent2 != null) {
            user_name.setText(username);
            //Toast.makeText(send.this, username, Toast.LENGTH_SHORT).show();
        }

        b_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(send.this,index.class);
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });

        b_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isEmpty(send.getText().toString())) {//输入框为空
                    Toast.makeText(send.this, "微博内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    List<user> loginuser = DataSupport.where("user_name = ?", username).find(user.class);
                    webook webook = new webook();
                    webook.setHead_img(loginuser.get(0).getUser_head());
                    webook.setUser_name(username);
                    webook.setSend_time(DatetimeUtil.getNowDateTime());
                    webook.setTest(send.getText().toString());
                    webook.setGood_num(0);
                    webook.setPinglun_num(0);
                    webook.setZhuanfa_num(0);

                    /*
                    * 数据入库并返回到首页
                    * */
                    webook.save();
                    netUtil netUtil = new netUtil();
                    netUtil.uptowebook(webook);

                    Toast.makeText(send.this, "发表成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(send.this,index.class);
                    intent.putExtra("user_name", username);
                    startActivity(intent);
                }
            }
        });
    }
}
