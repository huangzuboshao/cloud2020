package com.liao.springcloud.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liao.springcloud.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/4/20 17:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {

        String[] str = new String[] { "yang", "hao" };
        List list = Arrays.asList(str);

        System.out.println(list);

        str[0] = "ang";

        System.out.println(list);

          QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectByLiao();
        if (!CollectionUtils.isEmpty(userList)) {
            userList.forEach(System.out::println);
        }
    }

    @Test
    public void test2() {
        List<User> userList = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        if (!CollectionUtils.isEmpty(userList)) {
            userList.forEach(System.out::println);
        }
    }
}