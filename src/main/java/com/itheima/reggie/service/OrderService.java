package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.Orders;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/15 16:48
 */
public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
