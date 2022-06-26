package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:21
 */
public interface SetmealService extends IService<Setmeal> {
    // 新增套餐，同时需要保存套餐和菜品的关联关系
    void saveWithDish(SetmealDto setmealDto);

    // 查询带分类名的套餐分页信息
    void querySetmealPageWithCategoryName(Page<SetmealDto> pageInfo, String name);

    // 删除套餐，同时需要删除套餐和菜品的关联数据
    void removeSetmealWithDish(Long[] ids);

    // 根据id查询套餐信息和对应的菜品信息
    SetmealDto getByIdWithDish(Long id);

    // 更新套餐信息
    void updateSetmealWithDish(SetmealDto setmealDto);

    // 批量起售停售套餐
    void updateStatusByIds(int status, Long[] ids);

}
