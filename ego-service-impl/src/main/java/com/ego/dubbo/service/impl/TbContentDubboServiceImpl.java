package com.ego.dubbo.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;


/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 21:47
 **/
public class TbContentDubboServiceImpl implements TbContentDubboService {
    @Resource
    TbContentMapper tbContentMapper;
    @Override
    public EasyUIDataGrid selAll(long cid, int page, int rows) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        PageHelper.startPage(page,rows);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(cid);
        List<TbContent> list = tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pi = new PageInfo<>(list);
        easyUIDataGrid.setTotal(pi.getTotal());
        easyUIDataGrid.setRows(pi.getList());
        return easyUIDataGrid;
    }

    @Override
    public int insTbContent(TbContent tbContent) {
        return tbContentMapper.insert(tbContent);
    }

    @Override
    public int delById(long id) {
        return tbContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TbContent selById(long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }
}