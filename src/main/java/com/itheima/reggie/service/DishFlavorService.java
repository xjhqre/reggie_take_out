package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;

import java.util.List;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:21
 */
public interface DishFlavorService extends IService<DishFlavor> {

    /**
     * 逻辑删除对应菜品的口味记录
     * @param dishIds
     */
    void removeByDishIds(List<Long> dishIds);
}
