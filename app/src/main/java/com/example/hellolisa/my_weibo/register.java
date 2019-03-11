package com.example.hellolisa.my_weibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellolisa.my_weibo.db.user;

import org.litepal.crud.DataSupport;

import java.util.List;

import static android.text.TextUtils.isEmpty;


/*
        * 注册
        * */
public class register extends AppCompatActivity {
    private Button button_regiser;
    private Button button_quit;

    private EditText edit_rname;
    private EditText edit_rpassword;
    private EditText edit_address;
    private EditText edit_sex;
    private EditText edit_year;
    private EditText edit_month;
    private EditText edit_day;
    private EditText edit_introduce;
    private EditText edit_confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button_regiser = (Button)findViewById(R.id.button_register);
        button_quit = (Button)findViewById(R.id.button_quit);

        edit_rname = (EditText) findViewById(R.id.edit_rname);
        edit_rpassword = (EditText) findViewById(R.id.edit_rpassword);
        edit_confirmpassword = (EditText) findViewById(R.id.edit_confirmpassword);
        edit_address = (EditText) findViewById(R.id.edit_address);
        edit_sex = (EditText) findViewById(R.id.edit_sex);
        edit_year= (EditText) findViewById(R.id.edit_year);
        edit_month = (EditText) findViewById(R.id.edit_month);
        edit_day = (EditText) findViewById(R.id.edit_day);
        edit_introduce = (EditText) findViewById(R.id.edit_introduce);

        button_regiser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                List<user> loginuser = DataSupport.where("user_name = ?", edit_rname.getText().toString()).find(user.class);
                for(user user:loginuser){
                    Log.d("start", "username is "+user.getUser_name());
                }
                if (isEmpty(edit_rname.getText().toString())) {//用户名为空
                    Toast.makeText(register.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(edit_rname.getText())){
                    Toast.makeText(register.this, "该用户名已存在", Toast.LENGTH_SHORT).show();
                }else if (isEmpty(edit_rpassword.getText().toString())) {//密码为空
                    //editCode.requestFocus();//把输入焦点放在该控件上
                    Toast.makeText(register.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if (isEmpty(edit_confirmpassword.getText().toString())) {//确认密码为空
                    //editCode.requestFocus();//把输入焦点放在该控件上
                    Toast.makeText(register.this, "请再次确认密码", Toast.LENGTH_SHORT).show();
                    //return;
                }else if (!edit_confirmpassword.getText().toString().equals(edit_rpassword.getText().toString())) { //密码不一致
                    Toast.makeText(register.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
                else {
                    //入库
                    Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    user nuser = new user();
                    nuser.setUser_head(R.drawable.add_selector);
                    nuser.setUser_name(edit_rname.getText().toString());
                    nuser.setUser_password(edit_rpassword.getText().toString());
                    nuser.setUser_sex(edit_sex.getText().toString());
                    nuser.setUser_birth(edit_year.getText().toString() + edit_month.getText().toString() + edit_day.getText().toString());
                    nuser.setUser_address(edit_address.getText().toString());
                    nuser.setUser_self(edit_introduce.getText().toString());
                    nuser.save();
                }
                List<user> users = DataSupport.findAll(user.class);
                for(user user:users){
                    Toast.makeText(register.this, "user name is " + user.getUser_name(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
