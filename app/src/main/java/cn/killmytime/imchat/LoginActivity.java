package cn.killmytime.imchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.killmytime.User.User;
import cn.killmytime.controller.LoginSocket;
import cn.killmytime.utils.BaseActivity;
import cn.killmytime.utils.MD5Utils;

/**
 * Created by leiwe on 2018/5/16.
 * Thank you for reading, everything gonna to be better.
 */
//ToDO 登录注册这些模块应用http更好一些，因为之前没接触过安卓权限部分，真的坑了
public class LoginActivity extends BaseActivity{
    private static final String TAG="LoginActivity";
    SharedPreferences pref;
    Intent intent;
    EditText loginName,loginPassword;
    Button loginBt,registerBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >=21){
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        if (getSharedPreferences("loginStatus",MODE_PRIVATE).getBoolean("isOnline",false)) loginFinish();
        setContentView(R.layout.login);
        loginName=findViewById(R.id.login_name);
        loginPassword=findViewById(R.id.login_password);
        loginBt=findViewById(R.id.loginBt);
        registerBt=findViewById(R.id.registerBt);
        pref= getSharedPreferences("loginStatus",MODE_PRIVATE);
        boolean isOnline=pref.getBoolean("isOnline",false);
        String usename=pref.getString("name","");
        String cookie=pref.getString("cookie","");
        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFinish();
            }
        });
    }
    Runnable registerTask=new Runnable() {
        @Override
        public void run() {
            LoginSocket loginSocket=new LoginSocket();
            try {
                User user=new User(loginName.getText().toString(), MD5Utils.MD5Encode(loginPassword.getText().toString(),true),"",0);
                loginSocket.send(new Gson().toJson(user));
                String privateKey=loginSocket.receive();
                System.out.println("****"+privateKey);
                intent.putExtra("masterStatus",true);
                System.out.println(privateKey);
                loginFinish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * jump to MainActivityC if successfully login
     */
    private void loginFinish(){
            intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
    }

    /**
     * if error in login
     */
    private void loginError(){
        new AlertDialog.Builder(LoginActivity.this)
                .setMessage("登录失败，请检查用户名和密码")
                .setPositiveButton("确定", null)
                .create()
                .show();
    }
}
