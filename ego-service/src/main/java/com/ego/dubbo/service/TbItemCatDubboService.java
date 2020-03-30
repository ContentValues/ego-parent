package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-26 13:45
 **/
public interface TbItemCatDubboService {

    /**
     * 根据父类目id查询所有子类目
     * @param pid
     * @return
     */
    List<TbItemCat> show(long pid);

    /**
     * 通过id查询分类
     * @param id
     * @return
     */
    TbItemCat selById(long id);


}