package com.ego.passport.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-08 10:44
 **/
@Service
public class TbUserServiceImpl implements TbUserService {
    @Reference
    TbUserDubboService tbUserDubboServiceImpl;

    @Resource
    JedisDao jedisDaoImpl;

    @Override
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {

        EgoResult er = new EgoResult();
        TbUser user = tbUserDubboServiceImpl.selByUser(tbUser);
        if (user != null) {
            int cookieMaxage = 60 * 60 * 24 * 7;
            String uuid = UUID.randomUUID().toString();

            jedisDaoImpl.set(uuid, JsonUtils.objectToJson(user));
            jedisDaoImpl.expire(uuid, cookieMaxage);

            CookieUtils.setCookie(request, response, "TT_TOKEN", uuid + "", cookieMaxage);

            er.setStatus(200);
            er.setData(user);
            er.setMsg("OK");
        } else {
            er.setMsg("账号或密码错误");
        }
        return er;
    }

    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        EgoResult er = new EgoResult();
        CookieUtils.deleteCookie(request, response, "TT_TOKEN");
        jedisDaoImpl.del(token);
        er.setStatus(200);
        er.setMsg("OK");
        return er;
    }

    @Override
    public EgoResult getUserInfoByToken(String token) {
        EgoResult er = new EgoResult();
        String json = jedisDaoImpl.get(token);
        if (!TextUtils.isEmpty(json)) {
            TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
            er.setData(user);
            er.setStatus(200);
            er.setMsg("OK");
        } else {
            er.setMsg("通过token获取用户信息失败");
        }
        return er;
    }

}