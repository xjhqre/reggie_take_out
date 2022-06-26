package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:20
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
    void removeByDishIds(@Param("dishIds") List<Long> dishIds);
}
