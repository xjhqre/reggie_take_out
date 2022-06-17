package com.itheima.reggie.common;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 17:16
 */

/**
 * 基于ThreadLocal封装的工具类，保存和获取当前登陆用户的id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
