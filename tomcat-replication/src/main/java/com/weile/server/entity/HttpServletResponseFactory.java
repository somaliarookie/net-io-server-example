package com.weile.server.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Socket;

/**
 * @Auth weile
 * @Time 2020/6/26 22:20
 * @Description servlet request 工厂类，
 * servlet request：包装http返回
 * servlet response：包装http返回结果
 **/
public class HttpServletResponseFactory {


	public static HttpServletResponse creatHttpServletRequest(Socket socket) {
		BasicHttpServletResponse servletResponse = new BasicHttpServletResponse(socket);
		return  servletResponse;

	}

}
