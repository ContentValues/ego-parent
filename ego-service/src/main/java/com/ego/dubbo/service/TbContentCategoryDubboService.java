package com.ego.dubbo.service;

import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 11:00
 **/
public interface TbContentCategoryDubboService {

    /**
     * 根据父类目id查询所有子类目
     * @param pid
     * @return
     */
    List<TbContentCategory> show(long pid);

    int insTbContentCategory(TbContentCategory tbContentCategory);

    int updTbContentCategory(TbContentCategory tbContentCategory);

    int delTbContentCategory(long id);

    TbContentCategory selTbContentCategory(long id);
}