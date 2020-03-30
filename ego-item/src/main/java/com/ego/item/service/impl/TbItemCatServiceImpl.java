package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-28 10:53
 **/
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Reference
    TbItemCatDubboService tbItemCatDubboServiceImpl;
    @Override
    public PortalMenu showCatMenu() {
        List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
        PortalMenu pm =new PortalMenu();
        pm.setData(selAllMenu(list));
        return pm;
    }

    /**
     * 最终返回结果所有查询到的结果.
     */
    public List<Object> selAllMenu(List<TbItemCat> list){
        List<Object> listNode = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            if(tbItemCat.getIsParent()){
                PortalMenuNode pmd  = new PortalMenuNode();
                pmd.setU("/products/"+tbItemCat.getId()+".html");
                pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                pmd.setI(selAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
                listNode.add(pmd);
            }else{
                listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }
        return listNode;
    }
}