package com.example.hellolisa.my_weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellolisa.my_weibo.db.user;
import com.example.hellolisa.my_weibo.util.Add;

import org.litepal.crud.DataSupport;

import java.util.List;


/*
* 干干淡淡的登陆页面
* */
public class start extends AppCompatActivity {
    private Button button_login;
    private Button button_quit;
    private Button button_register;
    private EditText edit_name;
    private EditText edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        /*
        * 一般绑定事件
        * */
        button_login = (Button) findViewById(R.id.button_login);
        button_quit = (Button) findViewById(R.id.button_quit);
        button_register = (Button) findViewById(R.id.button_register);

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_password = (EditText)findViewById(R.id.edit_password);

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                *这里是登录逻辑代码
                 */
                //Toast.makeText(start.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                List<user> loginuser = DataSupport.where("user_name = ?", edit_name.getText().toString()).find(user.class);
                if(TextUtils.isEmpty(edit_name.getText())){
                    /*
                    * 用户名为空
                    * */
                    Toast.makeText(start.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }
                /*
                    * 用户名不存在
                    * */
                else if(loginuser.isEmpty()){
                    Toast.makeText(start.this, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
                    //提示该用户名不存在
                }
                /*
                    * 登入成功
                    * */
                else if(loginuser.get(0).getUser_password().equals(edit_password.getText().toString())){
                    //登陆成功
                    Toast.makeText(start.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(start.this, index.class);
                    intent2.putExtra("user_name",edit_name.getText().toString());
                    startActivity(intent2);
                }
                /*
                    * 密码和用户名不匹配
                    * */
                else{
                    Toast.makeText(start.this, "用户名或者密码不正确", Toast.LENGTH_SHORT).show();
                    //密码错误
                }
            }
        });
        /*
        * 摸了
        * */
        button_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(start.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                Add add = new Add();
                //netUtil netUtil = new netUtil();
                add.addtouser();
                //add.addtowebook();
               // Toast.makeText(start.this, "写入", Toast.LENGTH_SHORT).show();
                //netUtil.AddsendRequestWithOkHttpup("webook");
                //add.addpinglun();
                //finish();
            }
        });

        /*
        * 去注册
        * */
        button_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v){
                Intent intent = new Intent(start.this,register.class);
                startActivity(intent);
            }
        });
    }
}
