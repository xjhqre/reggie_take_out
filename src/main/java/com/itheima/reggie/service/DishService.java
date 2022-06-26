package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:21
 */
public interface DishService extends IService<Dish> {

    // 新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish和dish_falvor
    void saveWithFlavor(DishDto dishDto);

    // 查询所有带菜品分类名的菜品信息
    void selectDishWithCategoryNameList(Page<DishDto> page, String name);

    // 根据id查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息，同时更新对应的口味信息
    void updateWithFlavor(DishDto dishDto);

    // 批量起售停售
    void updateStatusByIds(int status, Long[] ids);
}
