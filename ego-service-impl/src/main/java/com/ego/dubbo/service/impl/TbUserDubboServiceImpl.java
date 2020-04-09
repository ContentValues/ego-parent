package com.ego.dubbo.service.impl;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;
import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-08 11:02
 **/
public class TbUserDubboServiceImpl implements TbUserDubboService {
    @Resource
    TbUserMapper tbUserMapper;

    @Override
    public TbUser selByUser(TbUser tbUser) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(tbUser.getUsername())
                                .andPasswordEqualTo(tbUser.getPassword());
        List<TbUser> users =  tbUserMapper.selectByExample(example);
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }
}