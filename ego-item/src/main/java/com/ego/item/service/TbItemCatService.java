package com.ego.item.service;

import com.ego.item.pojo.PortalMenu;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-28 10:52
 **/
public interface TbItemCatService {
    /**
     * 查询出所有分类类目并转换为特定类型.
     * @return
     */
    PortalMenu showCatMenu();
}