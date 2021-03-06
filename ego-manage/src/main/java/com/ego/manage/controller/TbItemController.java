package com.ego.manage.controller;

import javax.annotation.Resource;
import com.ego.pojo.TbItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemService;

@Controller
public class TbItemController {
    @Resource
    private TbItemService tbItemServiceImpl;

    /**
     * 分页显示商品
     */
    @RequestMapping("item/list")
    @ResponseBody
    public EasyUIDataGrid show(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int rows) {
        return tbItemServiceImpl.show(page, rows);
    }

    /**
     * 显示商品修改
     *
     * @return
     */
    @RequestMapping("rest/page/item-edit")
    public String showItemEdit() {
        return "item-edit";
    }


    /**
     * 商品删除
     * 商品状态，1-正常，2-下架，3-删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/delete")
    @ResponseBody
    public EgoResult delete(String ids) {
        EgoResult er = new EgoResult();
        int index = tbItemServiceImpl.update(ids, (byte) 3);
        if (index == 1) {
            er.setStatus(200);
        }
        return er;
    }


    /**
     * 商品下架
     *
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/instock")
    @ResponseBody
    public EgoResult instock(String ids) {
        EgoResult er = new EgoResult();
        int index = tbItemServiceImpl.update(ids, (byte) 2);
        if (index == 1) {
            er.setStatus(200);
        }
        return er;
    }

    /**
     * 商品上架
     *
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/reshelf")
    @ResponseBody
    public EgoResult reshelf(String ids) {
        EgoResult er = new EgoResult();
        int index = tbItemServiceImpl.update(ids, (byte) 1);
        if (index == 1) {
            er.setStatus(200);
        }
        return er;
    }


    /**
     * 商品新增
     *
     * @param tbItem
     * @param desc
     * @return
     */
    @RequestMapping("item/save")
    @ResponseBody
    public EgoResult save(TbItem tbItem, String desc, String itemParams) {
        EgoResult er = new EgoResult();
        int index = 0;
        try {
            index = tbItemServiceImpl.insTbItem(tbItem, desc, itemParams);
            if (index == 3) {//插入三张表
                er.setStatus(200);
            }
        } catch (Exception e) {
            er.setData(e.getMessage());
            e.printStackTrace();
        }
        return er;
    }


}
