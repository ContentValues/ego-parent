package com.ego.dubbo.service.impl;


import javax.annotation.Resource;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class TbItemDubboServiceImpl implements TbItemDubboService {
    @Resource
    private TbItemMapper tbItemMapper;

    @Resource
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        //分页代码
        //设置分页条件
        PageHelper.startPage(page, rows);
        //查询全部
        List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());

        PageInfo<TbItem> pi = new PageInfo<>(list);

        //放入到实体类
        EasyUIDataGrid datagrid = new EasyUIDataGrid();
        datagrid.setRows(pi.getList());
        datagrid.setTotal(pi.getTotal());
        return datagrid;
    }

    @Override
    public int insTbItem(TbItem tbItem) {
        return tbItemMapper.insert(tbItem);
    }

    @Override
    public int insTbItem(TbItem tbItem, TbItemDesc tbItemDesc) throws Exception {
        int index = 0;
        try {
            index = tbItemMapper.insert(tbItem);
            index += tbItemDescMapper.insert(tbItemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(index==2){
            return 2;
        }else{
            throw new Exception("新增失败,数据还原");
        }
    }


    @Override
    public int updStatus(long id, int status) {
        TbItem tbItem = new TbItem();
        tbItem.setId(id);
        tbItem.setStatus((byte) status);
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }
}
