package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;


public interface TbContentDubboService {

    /**
     * 分页查询分类下的内容 categoryId=0&page=1&rows=20
     * @return
     */
    EasyUIDataGrid selAll(long cid, int page, int rows);

    /**
     *
     * @param tbContent
     * @return
     */
    int insTbContent(TbContent tbContent);


    /**
     *
     * @param id
     * @return
     */
    int delById(long id);


    /**
     *
     * @param id
     * @return
     */
    TbContent selById(long id);
}
