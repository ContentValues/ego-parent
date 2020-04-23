package com.ego.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-10 10:57
 **/
@Controller
public class RedirectController {


    /*无参数*/
//    @RequestMapping("/index.action")
//    public String index(){
//        return "redirect:/redirect";
//    }
//
//    @RequestMapping("/redirect")
//    @ResponseBody
//    public String redirect(){
//        return "WelCome RedirectController";
//    }



    /**
     * 有参数的三种方式
     *
     * 1 通过URL附加参数在重定向中传参 最普通的接收参数的方式  通过在重定向中在URL的尾部附加参数来完成的，就是把参数附加在重定向的第二次HTTP请求后面，下图中在重定向的URL尾部添加了“name=tom”
     * 2 通过URL变量在重定向中传参    路径变量需要通过RedirectAttributes类的model对象的addAttribute方法进行添加，这样SpringMVC才能把它作为一个路径变量。这里需要说明一下的是model对象要在方法参数中定义，如果自己new一个RedirectAttributes对象是无法达到目的的
     * 3 通过flash属性在重定向中传参  原理是通过Session作为中转站来完成的，也就是在第一次请求时把需要在重定向中传的参数放入Session中，在第二次的请求中SpringMVC会把这个参数从Session中取出并转移到Request的model中
     * @return
     */
//    @RequestMapping("/index.action")
//    public String index(){
//        return "redirect:/redirect?name=zhangsan";
//    }
//
//    @RequestMapping("/redirect")
//    @ResponseBody
//    public String redirect(@RequestParam String name){
//        return "WelCome RedirectController"+name;
//    }

//    @RequestMapping("/index.action")
//    public String index(RedirectAttributes redirectAttributes){
//        redirectAttributes.addAttribute("name","lisi");
//        return "redirect:/redirect/{name}";
//    }
//
//    @RequestMapping("/redirect/{name}")
//    @ResponseBody
//    public String redirect(@PathVariable String name){
//        return "WelCome RedirectController"+name;
//    }

    @RequestMapping("/index.action")
    public String index(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("name","lisi");
        redirectAttributes.addFlashAttribute("age","14岁");
        return "redirect:/redirect/{name}";
    }

    @RequestMapping("/redirect/{name}")
    @ResponseBody
    public String redirect(@PathVariable String name, HttpServletRequest request, HttpServletResponse response, Model model){
        return "WelCome RedirectController"+name;
    }

}