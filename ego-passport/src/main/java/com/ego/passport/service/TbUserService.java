package com.ego.passport.service;

import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @param request
     * @param response
     * @return
     */
    EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response);


    /**
     * 退出
     * @param token
     * @param request
     * @param response
     * @return
     */
    EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据token查询用户信息 实现sso
     * @param token
     * @return
     */
    EgoResult getUserInfoByToken(String token);
}
