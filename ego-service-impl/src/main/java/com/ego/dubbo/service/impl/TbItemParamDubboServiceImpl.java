package com.ego.dubbo.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-27 13:10
 **/
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {
    @Resource
    TbItemParamMapper tbItemParamMapper;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        PageInfo<TbItemParam> pi = new PageInfo<>(list);
        EasyUIDataGrid eg = new EasyUIDataGrid();
        eg.setTotal(pi.getTotal());
        eg.setRows(pi.getList());
        return eg;
    }

    @Override
    public TbItemParam selByCatId(long catId) {
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(catId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            //要求每个类目只能有一个模板
            return list.get(0);
        }
        return null;
    }

    @Override
    public int deleteByIds(String ids) throws Exception{
        String[] split = ids.split(",");
        int index = 0;
        try {
            index = 0;
            for (String id : split) {
                index += tbItemParamMapper.deleteByPrimaryKey(Long.valueOf(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(index != split.length){
            throw new Exception("删除失败.可能原因:数据已经不存在");
        }
        return index;
    }

    @Override
    public int insTbItemParam(TbItemParam tbItemParam) {
        return tbItemParamMapper.insert(tbItemParam);
    }
}