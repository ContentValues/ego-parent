package com.ego.manage.controller;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentService;
import com.ego.manage.service.impl.TbContentServiceImpl;
import com.ego.pojo.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-29 22:07
 **/
@Controller
public class TbContentController {

    @Resource
    TbContentServiceImpl tbContentServiceImpl;

    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUIDataGrid selAll(long categoryId, int page, int rows){
        return tbContentServiceImpl.selAll(categoryId,page,rows);
    }

    @RequestMapping("content/save")
    @ResponseBody
    public EgoResult save(TbContent tbContent){
        return tbContentServiceImpl.insTbContent(tbContent);
    }

    @RequestMapping("content/delete")
    @ResponseBody
    public EgoResult delete(String ids){
        return tbContentServiceImpl.delById(ids);
    }


}