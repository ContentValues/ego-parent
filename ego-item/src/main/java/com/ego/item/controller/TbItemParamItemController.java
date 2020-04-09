package com.ego.item.controller;

import com.ego.item.service.TbItemParamItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 14:32
 **/
@Controller
public class TbItemParamItemController {

    @Resource
    TbItemParamItemService tbItemParamItemServiceImpl;

    @RequestMapping(value="item/param/{id}.html",produces="text/html;charset=utf-8")
    @ResponseBody
    public String param(@PathVariable long id){
        return tbItemParamItemServiceImpl.selById(id);
    }
}