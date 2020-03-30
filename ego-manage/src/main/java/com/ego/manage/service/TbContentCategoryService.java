package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 11:06
 **/
public interface TbContentCategoryService {

    /**
     *
     * @param pid
     * @return
     */
    List<EasyUiTree> show(long pid);


    EgoResult insTbContentCategory(long pid,String name);

    int updTbContentCategory(TbContentCategory tbContentCategory);

    int delTbContentCategory(long id);

    TbContentCategory selTbContentCategory(long id);
}