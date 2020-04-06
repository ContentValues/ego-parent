package com.ego.dubbo.service;

import com.ego.pojo.TbItemDesc;

public interface TbItemDescDubboService {

    /**
     * 新增商品描述信息
     * @param tbItemDesc
     * @return
     */
    int insTbItemDesc(TbItemDesc tbItemDesc);


    /**
     * 通过id查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc selByItemId(long itemId);

}
