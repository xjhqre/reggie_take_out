package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.mapper.DishFlavorMapper;
import com.itheima.reggie.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:22
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

    @Autowired
    DishFlavorMapper dishFlavorMapper;

    /**
     * 逻辑删除对应菜品的口味记录
     * @param dishIds
     */
    @Override
    public void removeByDishIds(List<Long> dishIds) {
        dishFlavorMapper.removeByDishIds(dishIds);
    }
}
