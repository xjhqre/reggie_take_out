package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/15 16:47
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
