package com.ego.cart.service.impl;

import business.TbItemChild;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cart.service.CartService;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 13:07
 **/
@Service
public class CartServiceImpl implements CartService {

    @Resource
    JedisDao jedisDaoImpl;

    @Reference
    TbItemDubboService tbItemDubboServiceImpl;

    @Value("${redis.cart.key}")
    private String itemKey;

    @Override
    public List<TbItemChild> showCart(HttpServletRequest request) {

        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser tbUser = JsonUtils.jsonToPojo(jedisDaoImpl.get(token), TbUser.class);
        String uuidKeyCart = itemKey + tbUser.getUsername();

        return JsonUtils.jsonToList(jedisDaoImpl.get(uuidKeyCart), TbItemChild.class);
    }

    @Override
    public void addCart(long id, int num, HttpServletRequest request) {
        /**
         *    先判断购物车
         *      如果购物车没有有数据  没有直接添加
         *      如果购物车有数据     遍历购物车判断购物车的数据是否含有新加的商品 如果包含改变数量 如果不包含直接添加到购物车
         *
         *
         *   先判断购物车？
         *     思考怎么判断 通过redis 键值对 那么 key = cart:username 作为唯一键 其实不太好 因为名称可能会重复
         *                                    value = List<TbItemChild>
         *
         */
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser tbUser = JsonUtils.jsonToPojo(jedisDaoImpl.get(token), TbUser.class);

        String uuidKeyCart = itemKey + tbUser.getUsername();

        List<TbItemChild> list = new ArrayList<>();
        if (jedisDaoImpl.exists(uuidKeyCart)) {
            list = JsonUtils.jsonToList(jedisDaoImpl.get(uuidKeyCart), TbItemChild.class);
            for (TbItemChild child : list) {
                if (child.getId() == id) {//购物车存在相同的商品
                    child.setNum(child.getNum() + num);
                    jedisDaoImpl.set(uuidKeyCart, JsonUtils.objectToJson(list));
                    return;//
                }
            }
        }
        TbItem tbItem = tbItemDubboServiceImpl.selById(id);
        TbItemChild item = new TbItemChild();
        item.setImages(tbItem.getImage() == null ? new String[1] : tbItem.getImage().split(","));
        item.setTitle(tbItem.getTitle());
        item.setPrice(tbItem.getPrice());
        item.setId(tbItem.getId());
        item.setNum(num);

        list.add(item);
        jedisDaoImpl.set(uuidKeyCart, JsonUtils.objectToJson(list));
    }


    @Override
    public void delete(long id, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser tbUser = JsonUtils.jsonToPojo(jedisDaoImpl.get(token), TbUser.class);
        String uuidKeyCart = itemKey + tbUser.getUsername();
        if (jedisDaoImpl.exists(uuidKeyCart)) {
            List<TbItemChild> list = JsonUtils.jsonToList(jedisDaoImpl.get(uuidKeyCart), TbItemChild.class);
            Iterator<TbItemChild> iterator = list.iterator();
            while (iterator.hasNext()){
                TbItemChild itemChild = iterator.next();
                if(itemChild.getId() == id){
                    iterator.remove();
                }
            }
            jedisDaoImpl.set(uuidKeyCart, JsonUtils.objectToJson(list));
            if(list.isEmpty()){
                jedisDaoImpl.expire(uuidKeyCart,0);
            }
        }
    }

    @Override
    public void update(long id, int num, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser tbUser = JsonUtils.jsonToPojo(jedisDaoImpl.get(token), TbUser.class);
        String uuidKeyCart = itemKey + tbUser.getUsername();
        if (jedisDaoImpl.exists(uuidKeyCart)) {
            List<TbItemChild> list = JsonUtils.jsonToList(jedisDaoImpl.get(uuidKeyCart), TbItemChild.class);
            Iterator<TbItemChild> iterator = list.iterator();
            while (iterator.hasNext()){
                TbItemChild itemChild = iterator.next();
                if(itemChild.getId() == id){
                    itemChild.setNum(num);
                    if(num == 0){//数量减到0的时候从购物车中移除
                        iterator.remove();
                    }
                }
            }
            jedisDaoImpl.set(uuidKeyCart, JsonUtils.objectToJson(list));
        }
    }
}