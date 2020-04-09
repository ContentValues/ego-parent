package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.item.service.TbItemDescService;
import com.ego.pojo.TbItemDesc;
import org.springframework.stereotype.Service;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 14:35
 **/
@Service
public class TbItemDescServiceImpl implements TbItemDescService {

    @Reference
    TbItemDescDubboService tbItemDescDubboServiceImpl;

    @Override
    public TbItemDesc selById(long id) {
        return tbItemDescDubboServiceImpl.selByItemId(id);
    }
}