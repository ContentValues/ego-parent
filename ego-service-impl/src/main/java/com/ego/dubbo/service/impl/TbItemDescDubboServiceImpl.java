package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;

import javax.annotation.Resource;

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
}