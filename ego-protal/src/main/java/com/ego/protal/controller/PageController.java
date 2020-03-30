package com.ego.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-28 14:55
 **/
@Controller
public class PageController {
    @RequestMapping("/")
    public String welcome(){
        return "index";
    }

    @RequestMapping("index")
    public String welcomeHome(){
        return "index";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}