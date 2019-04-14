package cn.killmytime.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * Created by leiwe on 2018/6/6.
 * Thank you for reading, everything gonna to be better.
 */


public class ClientThread extends Thread {

    private Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            try {
                // 信息的格式：(login||logout||say),发送人,收发人,信息体
                while (true) {
                    String msg=br.readLine();
                    System.out.println(msg);
                    String[] str = msg.split(",");
                    switch (str[0]) {
                        case "say":
                            System.out.println(str[2] + " 对   " + str[1] + " say:  "
                                    + str[3]);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}