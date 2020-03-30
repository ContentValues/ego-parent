package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatService {

    /**
     * 根据父类目id查询所有子类目
     * @param pid
     * @return
     */
    List<EasyUiTree> show(long pid);

    /**
     * 通过id查询分类
     * @param id
     * @return
     */
    TbItemCat selById(long id);

}
