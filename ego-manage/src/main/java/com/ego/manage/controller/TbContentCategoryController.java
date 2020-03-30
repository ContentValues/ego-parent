package com.ego.manage.controller;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 11:12
 **/
@Controller
public class TbContentCategoryController {

    @Resource
    TbContentCategoryService tbContentCategoryServiceImpl;

    /**
     * 显示内容分类管理
     * @param id
     * @return
     */
    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUiTree> showCat(@RequestParam(defaultValue = "0") long id){
        return tbContentCategoryServiceImpl.show(id);
    }


    /**
     * 内容分类删除 只是逻辑删除
     * 内容分类状态，1-正常，2-删除
     *
     * @param
     * @return
     */
    @RequestMapping("content/category/delete")
    @ResponseBody
    public EgoResult delete(long id) {
        EgoResult er = new EgoResult();
        TbContentCategory tbContentCategory = tbContentCategoryServiceImpl.selTbContentCategory(id);
        tbContentCategory.setStatus(2);
        tbContentCategory.setUpdated(new Date());

        int index = tbContentCategoryServiceImpl.updTbContentCategory(tbContentCategory);
        if (index == 1) {
            er.setStatus(200);
        }
        return er;
    }

    /**
     * 内容分类新增
     *
     * @param
     * @return
     */
    @RequestMapping("content/category/create")
    @ResponseBody
    public EgoResult create(long parentId,String name) {

        return tbContentCategoryServiceImpl.insTbContentCategory(parentId,name);
    }


    /**
     * 内容分类修改
     *
     * @param
     * @return
     */
    @RequestMapping("content/category/update")
    @ResponseBody
    public EgoResult update(TbContentCategory tbContentCategory) {
        EgoResult egoResult = new EgoResult();
        tbContentCategory.setUpdated(new Date());
        int index = tbContentCategoryServiceImpl.updTbContentCategory(tbContentCategory);
        if(index == 1){
            egoResult.setStatus(200);
        }
        return egoResult;
    }



}