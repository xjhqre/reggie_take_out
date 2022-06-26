package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:21
 */
public interface CategoryService extends IService<Category> {

    void remove(Long ids);
}
