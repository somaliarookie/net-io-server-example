package com.weile.server;

import com.weile.server.entity.HttpServletRequestFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
                serveClientWithOneThread(socket);
//                                serveClientWithMutipleThread(socket);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void serveClientWithMutipleThread(Socket socket) {
        new Thread(new Worker(socket), "http-server-worker").start();
    }

    private static void serveClientWithOneThread(Socket socket) throws IOException {
        System.out.println("客户端:" + socket.getRemoteSocketAddress().toString() + "已连接到服务器");

        byte[] inputBytes = new byte[socket.getInputStream().available()];
        socket.getInputStream().read(inputBytes);
        //读取客户端发送来的消息

        HttpServletRequest httpServletRequest = HttpServletRequestFactory.creatHttpServletRequest(inputBytes);

        System.out.println(httpServletRequest.getContextPath());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("HTTP/1.1 200 OK" + "\r\n");
        bw.flush();
        socket.close();

    }

}
