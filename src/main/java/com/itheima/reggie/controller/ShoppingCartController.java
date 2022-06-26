package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.ShoppingCart;
import com.itheima.reggie.service.ShoppingCartService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: xjhqre
 * @DateTime: 2022/6/15 16:50
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart, HttpSession session) {
        log.info("购物车数据：{}", shoppingCart);

        // 设置用户id，指定当前是哪个用户添加的购物车数据
        Long userId = (Long) session.getAttribute("user");
        shoppingCart.setUserId(userId);

        // 查询当前菜品或者套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId); // 匹配用户id

        if (dishId != null) {
            // 添加到购物车的是菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            // 添加的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        // 查询当前菜品或者套餐是否在购物车中
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null) { // 购物车中已经有该菜品或套餐
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        } else {
            // 如果不存在则插入记录
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }

    /**
     * 减少菜品/套餐数量
     */
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody Map<String, Object> params, HttpSession session) {
        log.info("减少菜品/套餐数量：{}", params);

        Long dishId = null, setmealId = null;
        if (params.get("dishId") != null) {
            dishId = Long.valueOf((String) params.get("dishId"));
        }
        if (params.get("setmealId") != null) {
            setmealId = Long.valueOf((String) params.get("setmealId"));
        }

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        Long userId = (Long) session.getAttribute("user");
        queryWrapper.eq(ShoppingCart::getUserId, userId); // 匹配用户id
        if (dishId != null) {
            queryWrapper.eq(ShoppingCart::getDishId, dishId); // 匹配菜品
        } else {
            queryWrapper.eq(ShoppingCart::getSetmealId, setmealId); // 匹配套餐
        }

        ShoppingCart shoppingCart = shoppingCartService.getOne(queryWrapper);
        int number = shoppingCart.getNumber() - 1;
        shoppingCart.setNumber(number);

        if (number == 0) { // 数量为0，删除该购物车记录
            shoppingCartService.remove(queryWrapper); // delete shopping_cart where user_id = ? and dish_id/setmeal_id = ？
        } else {
            // 更新数据
            shoppingCartService.updateById(shoppingCart);
        }

        return R.success(shoppingCart);
    }

    /**
     * 查看购物车
     * @param session
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(HttpSession session) {
        log.info("查看购物车");

        Long userId = (Long) session.getAttribute("user");

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(HttpSession session) {

        Long userId = (Long) session.getAttribute("user");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);

        shoppingCartService.remove(queryWrapper);

        return R.success("清空购物车成功");
    }
}
