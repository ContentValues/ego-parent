package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.item.pojo.ParamItem;
import com.ego.item.service.TbItemParamItemService;
import com.ego.pojo.TbItemParamItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-07 14:34
 **/
@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {

    @Reference
    TbItemParamItemDubboService tbItemParamItemDubboServiceImpl;

    @Override
    public String selById(long itemId) {

        TbItemParamItem item = tbItemParamItemDubboServiceImpl.selById(itemId);
        List<ParamItem> list = JsonUtils.jsonToList(item.getParamData(), ParamItem.class);
        System.out.println(list);
        StringBuffer sf = new StringBuffer();

        for (ParamItem param : list) {
            sf.append("<table width='500' style='color:gray;'>");
            for (int i = 0 ;i<param.getParams().size();i++) {
                if(i==0){
                    sf.append("<tr>");
                    sf.append("<td align='right' width='30%'>"+param.getGroup()+"</td>");
                    sf.append("<td align='right' width='30%'>"+param.getParams().get(i).getK()+"</td>");
                    sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
                    sf.append("<tr/>");
                }else{
                    sf.append("<tr>");
                    sf.append("<td> </td>");
                    sf.append("<td align='right'>"+param.getParams().get(i).getK()+"</td>");
                    sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
                    sf.append("</tr>");
                }
            }
            sf.append("</table>");
            sf.append("<hr style='color:gray;'/>");
        }
        return sf.toString();
    }
}