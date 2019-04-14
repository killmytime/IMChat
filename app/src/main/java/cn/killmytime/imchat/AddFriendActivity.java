package cn.killmytime.imchat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import cn.killmytime.utils.BaseActivity;

/**
 * Created by leiwe on 2018/5/17.
 * Thank you for reading, everything gonna to be better.
 */
public class AddFriendActivity extends BaseActivity {
    private Button backBt,searchBt;
    private EditText searchField;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        setContentView(R.layout.add_friend);
        backBt=findViewById(R.id.back_add_friend);
        searchBt=findViewById(R.id.search);
        searchField=findViewById(R.id.search_name);
        listView=findViewById(R.id.search_results);
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDO query By Name,show on ListView

            }
        });
    }

}
