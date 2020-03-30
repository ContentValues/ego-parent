package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-27 13:30
 **/
public interface TbItemParamService {

    /**
     * 查询规格参数
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
    EgoResult deleteByIds(String ids) ;

    /**
     * 插入规格参数
     * @param paramData
     * @param catId
     * @return
     */
    EgoResult insTbItemParam(String paramData,long catId);

}