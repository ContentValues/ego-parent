package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;
import com.ego.pojo.TbItemParamItemExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-27 19:06
 **/
public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService {

    @Resource
    TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public int insTbItemParamItem(TbItemParamItem tbItemParamItem) {
        return tbItemParamItemMapper.insert(tbItemParamItem);
    }
    @Override
    public TbItemParamItem selById(long itemid) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(itemid);
        List<TbItemParamItem> list =  tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}