package com.liao.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.liao.springcloud.entity.User;
import com.liao.springcloud.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/8 17:28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testGetOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //Wrapper<User> query = Wrappers.query();
        queryWrapper.select(User.class,info->!info.getColumn().equals("age")).nested(t->t.and(wq->wq.eq("name","蔡启祥"))).gt("age", 25);
        //User user = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);
        User user = userService.getOne(queryWrapper,false);
        Assert.assertNotNull(user);
    }
}