package com.ego.item.service;

import com.ego.pojo.TbItemDesc;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 14:33
 **/
public interface TbItemDescService {

    /**
     * 通过id发现商品描述
     * @param id
     * @return
     */
    TbItemDesc selById(long id);

}