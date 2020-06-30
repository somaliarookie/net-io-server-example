package com.weile.server.utils;

/**
 * @Auth weile
 * @Time 2020/6/30 20:08
 * @Description TODO
 **/
public class StringUtils {



    public static String byteArrayToStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        String str = new String(byteArray);
        return str;
    }
}
