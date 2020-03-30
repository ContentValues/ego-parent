package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 21:57
 **/
@Service
public class TbContentServiceImpl implements TbContentService {
    @Reference
    TbContentDubboService tbContentDubboServiceImpl;

    @Override
    public EasyUIDataGrid selAll(long cid, int page, int rows) {
        return tbContentDubboServiceImpl.selAll(cid,page,rows);
    }

    @Override
    public EgoResult insTbContent(TbContent tbContent) {
//        tbContent.setId(I);
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);

        EgoResult egoResult = new EgoResult();
        int index = tbContentDubboServiceImpl.insTbContent(tbContent);
        egoResult.setStatus(200);
        return egoResult;
    }

    @Override
    public EgoResult delById(String ids) {

        EgoResult egoResult = new EgoResult();

        String[] splits =ids.split(",");
        for (String id : splits) {
            int index = tbContentDubboServiceImpl.delById(Long.valueOf(id));
            egoResult.setStatus(200);
        }
        return egoResult;
    }

    @Override
    public TbContent selById(long id) {
        return tbContentDubboServiceImpl.selById(id);
    }
}