package com.weile.server.entity;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auth weile
 * @Time 2020/6/26 22:20
 * @Description servlet request 工厂类，
 * servlet request：包装http请求
 * servlet response：包装http返回结果
 **/
public class HttpServletRequestFactory {



	public static HttpServletRequest creatHttpServletRequest(byte[] bytes) {


		BasicHttpServletRequest servletRequest = new BasicHttpServletRequest(bytes);

		return  servletRequest;

	}

}
