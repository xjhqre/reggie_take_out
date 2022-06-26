package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/17 21:20
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

    @Select("SELECT a.*, b.`name` AS category_name FROM setmeal a JOIN category b ON a.`category_id` = b.`id` WHERE a.`name` LIKE CONCAT('%',#{name},'%') AND a.`is_deleted` = 0 ORDER BY sort")
    List<SetmealDto> querySetmealPageWithCategoryName(Page<SetmealDto> pageInfo, String name);
}
