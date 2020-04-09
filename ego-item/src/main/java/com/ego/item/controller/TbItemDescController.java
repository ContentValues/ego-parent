package com.ego.item.controller;

import com.ego.item.service.TbItemDescService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 14:31
 **/
@Controller
public class TbItemDescController {

    @Resource
    TbItemDescService tbItemDescServiceImpl;

    @RequestMapping(value="item/desc/{id}.html",produces="text/html;charset=utf-8")
    @ResponseBody
    public String desc(@PathVariable long id){
        return tbItemDescServiceImpl.selById(id).getItemDesc();
    }

}