package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.manage.service.TbItemDescService;
import com.ego.pojo.TbItemDesc;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-26 14:40
 **/
public class TbItemDescServiceImpl implements TbItemDescService {

    @Reference
    TbItemDescDubboService tbItemDescDubboServiceImpl;

    @Override
    public int insTbItemDesc(TbItemDesc tbItemDesc) {
        return tbItemDescDubboServiceImpl.insTbItemDesc(tbItemDesc);
    }
}