package com.weile.server;

import com.weile.server.entity.HttpServletRequestFactory;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * @Auth weile
 * @Time 2020/6/23 19:22
 * @Description TODO
 **/
public class Worker implements Runnable {

    private final Socket socket;

    public Worker(Socket socket) {

        this.socket = socket;
    }


    @Override
    public void run() {

        System.out.println("客户端:" + socket.getRemoteSocketAddress().toString() + "已连接到服务器");

        try {


            byte[] bytes = socket.getInputStream().readAllBytes();


            //读取客户端发送来的消息
            String msg = new String(bytes);
            System.out.println("客户端：" + msg);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("HTTP/1.1 200 \r\n");
            bw.write("Server: nginx/1.15.6\r\n");
            bw.write("Date: Wed, 24 Jun 2020 07:55:00 GMT\r\n");
            bw.write("Content-Type: application/json\r\n");
            bw.write("Last-Modified: Wed, 5 August 1996 15:55:28 GMT" + "\r\n");
            bw.write("Server: Apache 0.84\r\n");
            bw.write("\r\n");
            bw.write("{\"data\":123}\r\n");

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
