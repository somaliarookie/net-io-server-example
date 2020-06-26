package com.weile.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auth weile
 * @Time 2020/6/23 17:33
 * @Description http 服务器
 **/

public class HTTPServer {

    public static void main(String[] args) {

        startServer();

    }

    private static void startServer() {

        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");

            while (true) {

                Socket socket = ss.accept();
//                serveClientWithOneThread(socket);
                serveClientWithMutipleThread(socket);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void serveClientWithMutipleThread(Socket socket) {
        new Thread(new Worker(socket),"http-server-worker").start();
    }

    private static void serveClientWithOneThread(ServerSocket ss) throws IOException {
        Socket s = ss.accept();
        System.out.println("客户端:" + s.getRemoteSocketAddress().toString() + "已连接到服务器");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        //读取客户端发送来的消息
        String mess = br.readLine();
        System.out.println("客户端：" + mess);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bw.write("HTTP/1.1 200 OK" + "\r\n");
        bw.flush();
        s.close();

    }

}
