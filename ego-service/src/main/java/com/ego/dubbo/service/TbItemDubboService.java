package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-23 14:45
 **/
public interface TbItemDubboService {

    /**
     * 商品分页查询
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid show(int page, int rows);


    /**
     * 新增商品信息
     * @param tbItem
     * @return
     */
    int insTbItem(TbItem tbItem);


    /**
     * 新增商品信息 具有事务功能
     * @param tbItem
     * @param tbItemDesc
     * @param tbItemParamItem
     * @return
     */
    int insTbItem(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws Exception;



    /**
     * 修改商品状态
     * @param id
     * @param status
     * @return
     */
    int updStatus(long id,int status);


    /**
     * 通过商品状态查询所有商品信息
     * @return
     */
    List<TbItem> selAllByStatus(byte status);



}