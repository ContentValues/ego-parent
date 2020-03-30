package com.ego.manage.controller;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-27 13:44
 **/
@Controller
public class TbItemParamController {

    @Resource
    TbItemParamService tbItemParamServiceImpl;

    /**
     * 分页显示规格参数
     */
    @RequestMapping("item/param/list")
    @ResponseBody
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemParamServiceImpl.show(page, rows);
    }

    /**
     * 根据catid查询规格参数模板
     * @param catId
     * @return
     */
    @RequestMapping("item/param/query/itemcatid/{catId}")
    @ResponseBody
    public EgoResult selByCatId(@PathVariable long catId) {
        EgoResult egoResult = new EgoResult();
        TbItemParam tbItemParam = tbItemParamServiceImpl.selByCatId(catId);
        if(tbItemParam != null){
            egoResult.setData(tbItemParam);
            egoResult.setStatus(200);
        }
        return egoResult;
    }


    /**
     * 批量删除规格参数
     * @param ids
     * @return
     */
    @RequestMapping("item/param/delete")
    @ResponseBody
    public EgoResult delByIds(String ids) {
        return tbItemParamServiceImpl.deleteByIds(ids);
    }


    /**
     * 商品类目新增
     * @param paramData
     * @param catId
     * @return
     */
    @RequestMapping("item/param/save/{catId}")
    @ResponseBody
    public EgoResult save(String paramData,@PathVariable long catId) {
        return tbItemParamServiceImpl.insTbItemParam(paramData,catId);
    }

}