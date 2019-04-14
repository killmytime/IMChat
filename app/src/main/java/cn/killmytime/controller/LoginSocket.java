package cn.killmytime.controller;

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
 * Created by leiwe on 2018/6/6.
 * Thank you for reading, everything gonna to be better.
 */
public class LoginSocket {
    private Socket ss;
    private PrintWriter out;
    private String username,password;
    private SharedPreferences pref;
    private Gson gson = new Gson();
    public LoginSocket() {
        try {
            ss = new Socket("10.0.2.2", 2334);
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
        String line;
        while (!((line = br.readLine()) == null)) {
            msg.append(line);
        }
        System.out.println("Received Json String is : "+msg.toString());
        return msg.toString();
    }

    public void close() throws IOException{
        out.close();
        ss.close();
    }
}
