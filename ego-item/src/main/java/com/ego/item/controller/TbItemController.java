package com.ego.item.controller;

import com.ego.item.service.TbItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 13:34
 **/
@Controller
public class TbItemController {
    @Resource
    TbItemService tbItemServiceImpl;
    /**
     * 商品详情
     * @return
     */
    @RequestMapping("item/{id}.html")
    public String search(@PathVariable long id, Model model){
        model.addAttribute("item",tbItemServiceImpl.selById(id));
        return "item";
    }
}