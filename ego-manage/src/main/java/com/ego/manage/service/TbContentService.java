package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

public interface TbContentService {

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
    EgoResult insTbContent(TbContent tbContent);


    /**
     *
     * @param ids
     * @return
     */
    EgoResult delById(String ids);


    /**
     *
     * @param id
     * @return
     */
    TbContent selById(long id);
}
