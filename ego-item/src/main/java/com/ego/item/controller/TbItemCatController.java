package com.ego.item.controller;

import com.ego.item.pojo.PortalMenu;
import com.ego.item.service.TbItemCatService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-28 10:54
 **/
@Controller
public class TbItemCatController {

    @Resource
    TbItemCatService tbItemCatServiceImpl;

    @RequestMapping("rest/itemcat/all")
    @ResponseBody
    public MappingJacksonValue showMenu(String callback){
        PortalMenu p =  tbItemCatServiceImpl.showCatMenu();
        //把构造方法参数转换为json字符串并当作最终返回值函数的参数
        MappingJacksonValue mjv = new MappingJacksonValue(p);
        //最终返回结果中函数名
        mjv.setJsonpFunction(callback);
        return mjv;
    }

//    @RequestMapping("demo3")
//    @ResponseBody
//    public MappingJacksonValue demo(String callback){
//        People p = new People();
//        p.setId(1);
//        p.setName("张三");
//        //把构造方法参数转换为json字符串并当作最终返回值函数的参数
//        MappingJacksonValue mjv = new MappingJacksonValue(p);
//        //最终返回结果中函数名
//        mjv.setJsonpFunction(callback);
//        return mjv;
//    }

}