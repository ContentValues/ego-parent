package com.ego.manage.service.impl;

import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.ResultCodeConstans;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.manage.controller.TbItemController;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;
    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemDubboServiceImpl.show(page, rows);
    }

    @Override
    public int update(String ids, byte status) {
        int index = 0;
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            index += tbItemDubboServiceImpl.updStatus(Long.valueOf(id), status);
        }
        if (index == idsStr.length) {
            return 1;
        }
        return 0;
    }

    @Override
    public int insTbItem(TbItem tbItem, String desc,String itemParams) throws Exception {

//        Date date = new Date();
//        long id = IDUtils.genItemId();
//
//        tbItem.setId(id);
//        tbItem.setStatus((byte) 1);
//        tbItem.setCreated(date);
//        tbItem.setUpdated(date);
//
//        TbItemDesc tbItemDesc = new TbItemDesc();
////        tbItemDesc.setItemId(id);
//        tbItemDesc.setItemDesc(desc);
//        tbItemDesc.setCreated(date);
//        tbItemDesc.setUpdated(date);
//
//        int index = 0;
//        try {
//            index += tbItemDubboServiceImpl.insTbItem(tbItem);
//            index += tbItemDescDubboServiceImpl.insTbItemDesc(tbItemDesc);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (index == 2) {
//            return ResultCodeConstans.SUCCESS_CODE;
//        }
//        return ResultCodeConstans.ERROR_CODE;

        Date date = new Date();
        long itemId = IDUtils.genItemId();

        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
//        tbItemParamItem.setId();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setParamData(itemParams);


        return tbItemDubboServiceImpl.insTbItem(tbItem,tbItemDesc,tbItemParamItem);



    }
}
