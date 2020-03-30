package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface TbItemParamDubboService {
    /**
     * 分页显示产品规格参数
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid show(int page, int rows);


    /**
     * 根据分类catid查询模板
     * @param catId
     * @return
     */
    TbItemParam selByCatId(long catId);


    /**
     * 批量删除规格参数
     * @param ids
     * @return
     */
    int deleteByIds(String ids) throws Exception;


    /**
     * 插入规格参数
     * @param tbItemParam
     * @return
     */
    int insTbItemParam(TbItemParam tbItemParam);


}
