package com.liao.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.springcloud.dao.UserMapper;
import com.liao.springcloud.entity.User;
import com.liao.springcloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/8 17:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.selectByLiao();
    }

    @Override
    public int create(User user) {
        return userMapper.insert(user);
    }
}
