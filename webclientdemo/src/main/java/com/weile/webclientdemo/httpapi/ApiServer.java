package com.weile.webclientdemo.httpapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auth weile
 * @Time 2019/12/8 19:54
 * @Description TODO
 **/
@Target(ElementType.TYPE) //类注解
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface ApiServer {

	String value()default "";
}
