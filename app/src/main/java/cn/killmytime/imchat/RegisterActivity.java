package cn.killmytime.imchat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.killmytime.User.User;
import cn.killmytime.controller.RegisterSocket;
import cn.killmytime.utils.BaseActivity;

/**
 * Created by leiwe on 2018/5/17.
 * Thank you for reading, everything gonna to be better.
 */
public class RegisterActivity extends BaseActivity {
    EditText registerName,registerPassword, confirmPassword;
    Button loginBt, registerBt;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        if (getSharedPreferences("loginStatus",MODE_PRIVATE).getBoolean("isOnline",false)) registerFinish();
        setContentView(R.layout.register);
        registerName = findViewById(R.id.register_username);
        registerPassword = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.confirm_password);
        loginBt = findViewById(R.id.loginBt);
        registerBt = findViewById(R.id.registerBt);

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(registerTask).start();
            }
        });
    }
    Runnable registerTask=new Runnable() {
        @Override
        public void run() {
            RegisterSocket registerSocket=new RegisterSocket();
            try {
                User user=new User(registerName.getText().toString(),registerPassword.getText().toString(),"",0);
                registerSocket.send(new Gson().toJson(user));
                String privateKey=registerSocket.receive();
                System.out.println("****"+privateKey);
//                SharedPreferencesUtil.setPrefString("username",registerName.getText().toString());
//                SharedPreferencesUtil.setPrefString("privateKey",privateKey);
//                SharedPreferencesUtil.setPrefBoolean("isOnline",true);
                intent.putExtra("masterName",user.getUsername());
                intent.putExtra("masterPrivateKey",privateKey);
                intent.putExtra("masterStatus",true);
                System.out.println(privateKey);
                registerFinish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    private void registerFinish() {
        intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerError(int code) {
        switch (code) {
            case 0:
                new AlertDialog.Builder(RegisterActivity.this)
                        .setMessage("注册失败，用户名已被使用")
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case 1:
                new AlertDialog.Builder(RegisterActivity.this)
                        .setMessage("注册失败，密码强度不够，密码至少要8位，且需要包含数字，字母，和特殊字符")
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case 2:
                new AlertDialog.Builder(RegisterActivity.this)
                        .setMessage("注册失败，两次输入密码不一致，请检查后重新输入")
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
        }
    }
}

