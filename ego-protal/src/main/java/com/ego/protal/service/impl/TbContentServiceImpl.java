package com.ego.protal.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ego.commons.utils.JsonUtils;
import com.ego.protal.service.TbContentService;
//import com.ego.redis.dao.JedisDao;
import com.ego.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-03-30 16:42
 **/
@Service
public class TbContentServiceImpl implements TbContentService {
    @Reference
    private TbContentDubboService tbContentDubboServiceImpl;

    @Resource
    private JedisDao JedisDaoImpl;

    @Value("${redis.bigpic.key}")
    private String key;

    @Override
    public String showBigPic() {
        //先判断在redis中是否存在
        if(JedisDaoImpl.exists(key)){
            String value = JedisDaoImpl.get(key);
            if(value!=null&&!value.equals("")){
                return value;
            }
        }
        //如果不存在从mysql中取,取完后放入redis中
        List<TbContent> list = tbContentDubboServiceImpl.selByCount(6, true);

        List<Map<String,Object>> listResult = new ArrayList<>();
        for (TbContent tc : list) {
            Map<String,Object> map = new HashMap<>();

            map.put("srcB", tc.getPic2());
            map.put("height", 240);
            map.put("alt", "对不起,加载图片失败");
            map.put("width", 670);
            map.put("src", tc.getPic());
            map.put("widthB", 550);
            map.put("href", tc.getUrl() );
            map.put("heightB", 240);

            listResult.add(map);
        }
        String listJson = JsonUtils.objectToJson(listResult);
        //把数据放入到redis中
        JedisDaoImpl.set(key, listJson);
        return listJson;
    }

}