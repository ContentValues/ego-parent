package com.ego.manage;

import com.ego.dubbo.service.TbItemDubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-24 16:05
 **/
public class Test {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-dubbo.xml");

        TbItemDubboService tbItemDubboServiceImpl = ac.getBean("", TbItemDubboService.class);

        tbItemDubboServiceImpl.show(1, 1);
    }
}