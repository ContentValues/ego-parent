package com.ego.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-10 10:51
 **/
@Controller
public class ForwardController {

    @RequestMapping("/")
    public String index(){
        return "forward:/forward";
    }

    @RequestMapping("/forward")
    @ResponseBody
    public String forward(){
        return "WelCome ForwardController";
    }



}