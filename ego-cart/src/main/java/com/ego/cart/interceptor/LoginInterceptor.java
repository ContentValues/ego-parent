package com.ego.cart.interceptor;
import com.ego.commons.utils.CookieUtils;
import com.ego.redis.dao.JedisDao;
import org.apache.http.util.TextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-09 15:07
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    JedisDao jedisDaoImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         *  拦截器 判断用户是否登录
         *          如果登录放行
         *          如果没有登录 跳转到登录界面
         */
        String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
        if(!TextUtils.isEmpty(token) && jedisDaoImpl.exists(token)){
            return true;
        }
        String num = request.getParameter("num");
//        response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL()+"%3Fnum="+num);
        response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}