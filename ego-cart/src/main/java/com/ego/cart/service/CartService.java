package com.ego.cart.service;

import business.TbItemChild;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CartService {

    /**
     * 显示购物车
     * @return
     */
    List<TbItemChild> showCart(HttpServletRequest request);


    /**
     * 添加购物车
     * @param id
     * @param num
     */
    void addCart(long id,int num,HttpServletRequest request);


    /**
     * 删除购物车商品
     * @param id
     */
    void delete(long id,HttpServletRequest request);


    /**
     * 更新购物车数量
     * @param id
     * @param num
     */
    void update(long id,int num,HttpServletRequest request);




}
