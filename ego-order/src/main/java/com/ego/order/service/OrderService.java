package com.ego.order.service;

import business.TbItemChild;
import com.ego.commons.pojo.EgoResult;
import com.ego.order.pojo.MyOrderParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 21:56
 **/
public interface OrderService {

    List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request);


    /**
     * 创建订单
     * @param param
     * @return
     */
    EgoResult create(MyOrderParam param, HttpServletRequest request);

}