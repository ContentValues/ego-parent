package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemDescExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-26 14:37
 **/
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
    @Resource
    TbItemDescMapper tbItemDescMapper;

    @Override
    public int insTbItemDesc(TbItemDesc tbItemDesc) {
        return tbItemDescMapper.insert(tbItemDesc);
    }

    @Override
    public TbItemDesc selByItemId(long itemId) {
        TbItemDescExample example = new TbItemDescExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        List<TbItemDesc> list = tbItemDescMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}