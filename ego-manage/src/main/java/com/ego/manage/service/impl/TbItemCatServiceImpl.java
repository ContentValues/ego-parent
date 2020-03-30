package com.ego.manage.service.impl;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.manage.service.TbItemCatService;
import com.ego.pojo.TbItemCat;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-26 13:51
 **/
@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Reference
    TbItemCatDubboService tbItemCatDubboServiceImpl;
    @Override
    public List<EasyUiTree> show(long pid) {
        List<EasyUiTree> list = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCatDubboServiceImpl.show(pid)) {
            EasyUiTree easyUiTree = new EasyUiTree();
            easyUiTree.setId(tbItemCat.getId());
            easyUiTree.setText(tbItemCat.getName());
            easyUiTree.setState(tbItemCat.getIsParent()?"closed":"open");
            list.add(easyUiTree);
        }
        return list;
    }

    @Override
    public TbItemCat selById(long id) {
        return tbItemCatDubboServiceImpl.selById(id);
    }
}