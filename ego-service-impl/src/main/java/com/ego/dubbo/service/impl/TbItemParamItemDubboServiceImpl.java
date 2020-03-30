package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;

import javax.annotation.Resource;

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
}