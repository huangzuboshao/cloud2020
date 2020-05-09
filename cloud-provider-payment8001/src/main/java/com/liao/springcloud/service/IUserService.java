package com.liao.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.springcloud.entity.User;

import java.util.List;

/**
 * 通用service
 *
 * @author huangzuboshao
 * @date 2020/5/8 17:23
 */
public interface IUserService extends IService<User> {
    List<User> listUsers();

    int create(User user);
}
