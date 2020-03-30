package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-27 13:34
 **/
@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Reference
    TbItemParamDubboService tbItemParamDubboServiceImpl;

    @Reference
    TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        EasyUIDataGrid eg = tbItemParamDubboServiceImpl.show(page, rows);
        List<TbItemParam> list = (List<TbItemParam>) eg.getRows();
        List<TbItemParamChild> childList = new ArrayList<>();
        for (TbItemParam tbItemParam : list) {
            TbItemCat tbItemCat = tbItemCatDubboServiceImpl.selById(tbItemParam.getItemCatId());
            TbItemParamChild tbItemParamChild = new TbItemParamChild();
            tbItemParamChild.setId(tbItemParam.getId());
            tbItemParamChild.setItemCatId(tbItemParam.getItemCatId());
            tbItemParamChild.setParamData(tbItemParam.getParamData());
            tbItemParamChild.setCreated(tbItemParam.getCreated());
            tbItemParamChild.setUpdated(tbItemParam.getUpdated());
            tbItemParamChild.setItemCatName(tbItemCat.getName());
            childList.add(tbItemParamChild);
        }
        eg.setRows(childList);
        return eg;
    }

    @Override
    public TbItemParam selByCatId(long catId) {
        return tbItemParamDubboServiceImpl.selByCatId(catId);
    }

    @Override
    public EgoResult deleteByIds(String ids)  {

        EgoResult egoResult = new EgoResult();
        int index = 0;
        try {
            index = tbItemParamDubboServiceImpl.deleteByIds(ids);
//            if(ids.split(",").length == index){
//                egoResult.setStatus(200);
//            }
            //todo 可以这么写 因为在dubbo层 如果ids.split(",").length != index 会直接抛出异常 所以在进行判断没啥意义
            egoResult.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            egoResult.setData(e.getMessage());
        }
        return egoResult;
    }

    @Override
    public EgoResult insTbItemParam(String paramData,long catId) {

        Date date = new Date();
        TbItemParam tbItemParam = new TbItemParam();
//        tbItemParam.setId(); //自增不需要考虑
        tbItemParam.setItemCatId(catId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);

        int index = tbItemParamDubboServiceImpl.insTbItemParam(tbItemParam);
        EgoResult egoResult = new EgoResult();
        egoResult.setStatus(200);
        return egoResult;
    }


}