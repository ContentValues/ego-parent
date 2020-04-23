package com.ego.order.service.impl;

import business.TbItemChild;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.OrderService;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 21:56
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    JedisDao jedisDaoImpl;

    @Reference
    TbOrderDubboService tbOrderDubboServiceImpl;

    @Value("${redis.cart.key}")
    private String itemKey;

    @Override
    public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser tbUser = JsonUtils.jsonToPojo(jedisDaoImpl.get(token), TbUser.class);
        String uuidKeyCart = itemKey + tbUser.getUsername();
        List<TbItemChild> list = JsonUtils.jsonToList(jedisDaoImpl.get(uuidKeyCart),TbItemChild.class);
        List<TbItemChild> result = new ArrayList<>();
        for (Long id : ids) {
            for (TbItemChild item : list) {
                if(id.longValue() == item.getId().longValue()){
                    result.add(item);
                }
            }
        }
        return result;
    }

    @Override
    public EgoResult create(MyOrderParam param, HttpServletRequest request) {

        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser tbUser = JsonUtils.jsonToPojo(jedisDaoImpl.get(token), TbUser.class);
        String uuidKeyCart = itemKey + tbUser.getUsername();

        long id = IDUtils.genItemId();
        Date date =new Date();

        //订单表数据
        TbOrder order = new TbOrder();
        order.setPayment(param.getPayment());
        order.setPaymentType(param.getPaymentType());
        order.setOrderId(id+"");
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setUserId(tbUser.getId());
        order.setBuyerNick(tbUser.getUsername());
        order.setBuyerRate(0);

        //订单-商品表
        for (TbOrderItem item : param.getOrderItems()) {
            item.setId(IDUtils.genItemId()+"");
            item.setOrderId(id+"");
        }
        //收货人信息
        TbOrderShipping shipping = param.getOrderShipping();
        shipping.setOrderId(id+"");
        shipping.setCreated(date);
        shipping.setUpdated(date);

        EgoResult erResult = new EgoResult();
        try {
            int index = tbOrderDubboServiceImpl.insOrder(order, param.getOrderItems(), shipping);
            if(index>0){
                erResult.setStatus(200);
                //删除购买的商品
                List<TbItemChild> listCart = JsonUtils.jsonToList(jedisDaoImpl.get(uuidKeyCart), TbItemChild.class);
                Iterator<TbItemChild> iterator =  listCart.iterator();
                while (iterator.hasNext()){
                    TbItemChild child = iterator.next();
                    for (TbOrderItem item : param.getOrderItems()) {
                        if(child.getId().longValue()==Long.parseLong(item.getItemId())){
                            iterator.remove();
                        }
                    }
                }
                jedisDaoImpl.set(uuidKeyCart, JsonUtils.objectToJson(listCart));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return erResult;
    }

}