package com.ego.passport.controller;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import org.apache.http.util.TextUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-08 10:43
 **/
@Controller
public class TbUserController {

    @Resource
    TbUserService tbUserServiceImpl;

    /**
     * 显示登录页面
     * @return
     */
    @RequestMapping("user/showLogin")
    public String showLogin(Model model,@RequestHeader(value = "Referer",defaultValue = "") String url, String interurl){
        if(!TextUtils.isEmpty(url)){
            model.addAttribute("redirect", url);
        }else {
            model.addAttribute("redirect", interurl);
        }
        return "login";
    }

    @RequestMapping(value = "user/login")
    @ResponseBody
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response){
        return tbUserServiceImpl.login(tbUser,request,response);
    }

    @RequestMapping(value = "user/token/{token}")
    @ResponseBody
    public Object token(@PathVariable String token,String callback){
        EgoResult er =  tbUserServiceImpl.getUserInfoByToken(token);
        if(!TextUtils.isEmpty(callback)){
            //把构造方法参数转换为json字符串并当作最终返回值函数的参数
            MappingJacksonValue mjv = new MappingJacksonValue(er);
            //最终返回结果中函数名
            mjv.setJsonpFunction(callback);
            return mjv;
        }
        return er;
    }

    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token,String callback,HttpServletRequest request,HttpServletResponse response){
        EgoResult er =  tbUserServiceImpl.logout(token,request,response);
        if(!TextUtils.isEmpty(callback)){
            //把构造方法参数转换为json字符串并当作最终返回值函数的参数
            MappingJacksonValue mjv = new MappingJacksonValue(er);
            //最终返回结果中函数名
            mjv.setJsonpFunction(callback);
            return mjv;
        }
        return er;
    }


}