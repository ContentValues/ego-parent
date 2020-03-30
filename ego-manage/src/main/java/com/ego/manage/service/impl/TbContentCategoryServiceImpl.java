package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manage.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 11:07
 **/
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Reference
    TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;

    @Override
    public List<EasyUiTree> show(long pid) {

        List<EasyUiTree> uiTrees = new ArrayList<>();
        List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.show(pid);

        for (TbContentCategory tbContentCategory : list) {
            EasyUiTree easy = new EasyUiTree();
            easy.setText(tbContentCategory.getName());
            easy.setId(tbContentCategory.getId());
            easy.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            uiTrees.add(easy);
        }
        return uiTrees;
    }

    /**
     * 新增内容分类管理 暂时不考虑事务回滚
     * 大前提 1 父亲不能为null 2 新增的是一个子分类
     * 如果父亲是子分类 那么改成父分类
     *
     * @param pid
     * @param name
     * @return
     */
    @Override
    public EgoResult insTbContentCategory(long pid, String name) {
        int index = 0;
        Date date = new Date();


        TbContentCategory tbContentCategory = new TbContentCategory();
//        tbContentCategory.setId();
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(pid);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);//是不是父亲呢 很奇怪怎么弄
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);

        EgoResult er = new EgoResult();
        index += tbContentCategoryDubboServiceImpl.insTbContentCategory(tbContentCategory);

        if(index >0){
            TbContentCategory selCategoryParent = tbContentCategoryDubboServiceImpl.selTbContentCategory(pid);
            selCategoryParent.setUpdated(date);
            if (selCategoryParent.getIsParent() == false) {
                selCategoryParent.setIsParent(true);
            }
            index += updTbContentCategory(selCategoryParent);
        }
        if (index == 2) {
            er.setStatus(200);
        }
        return er;
    }

    @Override
    public int delTbContentCategory(long id) {
        return tbContentCategoryDubboServiceImpl.delTbContentCategory(id);
    }

    @Override
    public int updTbContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryDubboServiceImpl.updTbContentCategory(tbContentCategory);
    }

    @Override
    public TbContentCategory selTbContentCategory(long id) {
        return tbContentCategoryDubboServiceImpl.selTbContentCategory(id);
    }
}