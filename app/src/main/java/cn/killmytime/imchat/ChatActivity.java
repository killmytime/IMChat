package cn.killmytime.imchat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by leiwe on 2018/5/17.
 * Thank you for reading, everything gonna to be better.
 */



public class ChatActivity extends Activity {
    private TextView text_chat;
    private EditText edit_chat;
    private ImageView img_x;
    private String str_text, name, password, friend, sendMsg;
    private ChatHandler handler;
    private SimpleDateFormat sf = new SimpleDateFormat("HH:mm");//获取系统时间自定义格式


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);//加载布局文件

        text_chat = findViewById(R.id.chat_text);
        img_x = findViewById(R.id.img_x);
        edit_chat = findViewById(R.id.edit_chat);


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                //接收用户发来的消息

//                con.getChatManager().addChatListener(new ChatManagerListener() {
//
//                    @Override
//                    public void chatCreated(Chat chat, boolean arg1) {
//                        chat.addMessageListener(new MessageListener() {
//
//                            @Override
//                            public void processMessage(Chat chat, Message msg) {
//                                //截取发来信息的用户的用户名
//                                friend = msg.getFrom();
//                                friend = friend.substring(0, friend.indexOf("@"));
//
//                                //处理消息内容，实际上msg.getBody就是消息内容，为了显示美观，做了简单处理
//                                if (msg.getBody() != null) {
//                                    if (str_text == null) {
//                                        str_text = "(" + sf.format(new Date()) + ")" + friend + "对你说:" + msg.getBody() + "\n";
//                                        android.os.Message m = new android.os.Message();
//                                        Bundle b = new Bundle();
//                                        b.putString("msg", str_text);
//                                        m.setData(b);
//                                        handler.sendMessage(m);//将获取的消息内容发送给handler
//
//                                    } else {
//                                        str_text = str_text + "(" + sf.format(new Date()) + ")" + friend + "对你说:" + msg.getBody() + "\n";
//                                        android.os.Message m = new android.os.Message();
//                                        Bundle b = new Bundle();
//                                        b.putString("msg", str_text);
//                                        m.setData(b);
//                                        handler.sendMessage(m);
//                                    }
//                                }
//
//                            }
//                        });
//                    }
//                });

            }

        });

        thread.start();//启动接收消息的副线程
        handler = new ChatHandler();//实例化Handler


    }


    public class ChatHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);

            Bundle b = msg.getData();
            String ChatHandler = b.getString("msg");

            text_chat.setText(ChatHandler);//将接收到的消息设置到主界面
        }
    }
}

