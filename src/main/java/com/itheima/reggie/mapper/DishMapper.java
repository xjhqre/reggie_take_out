package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:20
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    @Select("SELECT a.*, b.`name` AS category_name FROM dish a JOIN category b ON a.`category_id` = b.`id` WHERE a.`name` LIKE CONCAT('%',#{name},'%') AND a.`is_deleted` = 0 ORDER BY sort")
    List<DishDto> selectDishWithCategoryNameList(Page<DishDto> page, @Param("name") String name);

    void updateStatusByIds(@Param("status") int status, @Param("ids") Long[] ids);
}
