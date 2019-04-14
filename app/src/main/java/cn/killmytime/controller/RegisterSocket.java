package cn.killmytime.controller;

/**
 * Created by leiwe on 2018/6/6.
 * Thank you for reading, everything gonna to be better.
 */

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * 套接字客户端，
 * 1、先生成JSON对象
 * 2、将JSON对象转成JSON字符串
 * 3、将JSON字符串转成字节数组写入套接字输出流
 */
public class RegisterSocket {
    private Socket ss;
    private PrintWriter out;
    private String username,password;
    private SharedPreferences pref;
    private Gson gson = new Gson();
    public RegisterSocket() {
        try {
            ss = new Socket("10.0.2.2", 2333);
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ss.getOutputStream())), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向服务器发送消息
    public void send(String identify) throws IOException {
        try {

            System.out.println("send successfully !! :"+identify);
            out.write(identify);
            out.flush();
            ss.shutdownOutput();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //接收来自服务器的消息
    public String receive() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        StringBuilder msg = new StringBuilder();
        String privateKey="******";
        String line;
        while (!((line = br.readLine()) == null)) {
            msg.append(line);
        }
        System.out.println("Received Json String is : "+msg.toString());
        System.out.println(msg.length());
            privateKey= String.valueOf(msg);
            System.out.println("*****"+privateKey+"******");

        System.out.println("Register Successfully!!");
        return privateKey;
    }

    public void close() throws IOException{
        out.close();
        ss.close();
    }
}