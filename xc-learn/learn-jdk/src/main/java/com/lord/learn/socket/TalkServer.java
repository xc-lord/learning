package com.lord.learn.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xj_xiaocheng on 2015/1/21.
 */
public class TalkServer {

    public static void main(String[] args) throws Exception {
        String name = "小明";
        System.out.println("我是" + name + "，服务端");
        //创建一个ServerSocket在端口5700监听客户请求
        ServerSocket server = new ServerSocket(5700);

        //使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
        Socket socket = server.accept();

        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(new TalkBackgroundThread("小亮", is)).start();

        //由Socket对象得到输入流，并构造相应的BufferedReader对象
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        //由Socket对象得到输出流，并构造PrintWriter对象
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

        //在标准输出上打印从客户端读入的字符串
        String line;
        line = sin.readLine();

        //从标准输入读入一字符串
        while (!line.equals("bye")) {
            //如果该字符串为 "bye"，则停止循环
            os.println(line);
            //向客户端输出该字符串
            os.flush();
            //刷新输出流，使Client马上收到该字符串
            System.out.println("我说:" + line);
            //从Client读入一字符串，并打印到标准输出上
            line = sin.readLine();
            //从系统标准输入读入一字符串
        } //继续循环

        os.close(); //关闭Socket输出流
        is.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
        server.close(); //关闭ServerSocket
    }
}
