package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiTree;

import java.util.List;

public interface TbItemCatService {

    /**
     * 根据父类目id查询所有子类目
     * @param pid
     * @return
     */
    List<EasyUiTree> show(long pid);

}
