package cn.killmytime.controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Created by leiwe on 2018/6/6.
 * Thank you for reading, everything gonna to be better.
 */



public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.0.2.2", 9999);
            //开启一个线程接收信息，并解析
            ClientThread thread=new ClientThread(socket);
            thread.setName("Client1");
            thread.start();
            //主线程用来发送信息
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            while(true)
            {
                String s=br.readLine();
                out.println(s);
                //out.write(s+"\n");
                out.flush();
            }
        }catch(Exception e){
            System.out.println("服务器异常");
        }
    }
}
