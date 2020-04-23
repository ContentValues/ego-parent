package com.ego.cart.controller;

import business.TbItemChild;
import com.ego.cart.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 13:06
 **/
@Controller
public class CartController {
    @Resource
    CartService cartServiceImpl;

    @RequestMapping(value = "cart/cart.html")
    public String showCart(HttpServletRequest request, Model model){
        List<TbItemChild> list =  cartServiceImpl.showCart(request);
        model.addAttribute("cartList",list);
        return "cart";
    }

    @RequestMapping(value = "cart/add/{id}.html")
    public String addCart(@PathVariable long id,int num,HttpServletRequest request){
        cartServiceImpl.addCart(id,num,request);
        return "cartSuccess";
    }


    @RequestMapping(value = "cart/delete/{id}.action")
    @ResponseBody
    public String delete(@PathVariable long id,HttpServletRequest request){
        cartServiceImpl.delete(id,request);
        return "";
    }

    @RequestMapping(value = "cart/update/num/{id}/{num}.action")
    @ResponseBody
    public String update(@PathVariable long id,@PathVariable int num,HttpServletRequest request){
        cartServiceImpl.update(id,num,request);
        return "";

    }



}