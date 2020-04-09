package com.ego.item.service;

import com.ego.item.pojo.TbItemChild;
import com.ego.pojo.TbItem;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 13:47
 **/
public interface TbItemService {

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    TbItemChild selById(long id);

}