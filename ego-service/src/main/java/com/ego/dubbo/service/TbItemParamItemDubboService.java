package com.ego.dubbo.service;

import com.ego.pojo.TbItemParamItem;

public interface TbItemParamItemDubboService {


    /**新增规格参数详情
     * @param tbItemParamItem
     * @return
     */
    int insTbItemParamItem(TbItemParamItem tbItemParamItem);


    /**
     * 发现商品规格参数
     * @return
     */
    TbItemParamItem selById(long id);


}
