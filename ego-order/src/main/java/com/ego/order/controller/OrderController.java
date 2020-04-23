package com.ego.order.controller;

import business.TbItemChild;
import com.ego.commons.pojo.EgoResult;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 21:55
 **/
@Controller
public class OrderController {
    @Resource
    OrderService orderServiceImpl;

    @RequestMapping("order/order-cart.html")
    public String showOrderCart(Model model, @RequestParam("id") List<Long> abc, HttpServletRequest request) {
        List<TbItemChild> list = orderServiceImpl.showOrderCart(abc, request);
        model.addAttribute("cartList", list);
        return "order-cart";
    }

    /**
     * 创建订单
     *
     * @param param
     * @param request
     * @return
     */
    @RequestMapping("order/create.html")
    public String createOrder(MyOrderParam param, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        EgoResult er = orderServiceImpl.create(param, request);
        if (er.getStatus() == 200) {
            return "redirect:/order/my-orders";
        } else {
            redirectAttributes.addFlashAttribute("message", "订单创建失败滴滴滴滴biubiubiu");
            return "redirect:/order/error/exception";
        }
    }


    /**
     * 我的订单界面
     *
     * @return
     */
    @RequestMapping("order/my-orders")
    public String myOrders() {
        return "my-orders";
    }

    /**
     * 系统错误界面
     *
     * @return
     */
    @RequestMapping("order/error/exception")
    public String error() {
        return "error/exception";
    }

}