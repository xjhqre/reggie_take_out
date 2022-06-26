package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
