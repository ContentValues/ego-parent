package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
import javax.annotation.Resource;
import java.util.List;
/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 11:00
 **/
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
    @Resource
    TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<TbContentCategory> show(long pid) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(pid)
                                .andStatusEqualTo(1);//1表示正常 2表示删除
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insTbContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.insert(tbContentCategory);
    }

    @Override
    public int delTbContentCategory(long id) {
        return tbContentCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updTbContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }
    @Override
    public TbContentCategory selTbContentCategory(long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(1);//1表示正常 2表示删除

        List<TbContentCategory> list =  tbContentCategoryMapper.selectByExample(example);
        if(list != null){
            return list.get(0);
        }
        return null;
    }
}