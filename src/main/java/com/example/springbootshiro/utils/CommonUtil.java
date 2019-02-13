package com.example.springbootshiro.utils;

import java.util.Random;

/**
 * 工具库
 */
public class CommonUtil {

    /**
     * 获取指定位数的随机数
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
