package com.liao.springcloud.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liao.springcloud.entity.User;
import com.liao.springcloud.service.IUserService;
import com.liao.springcloud.vo.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/9 13:20
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public CommonResultVO<List<User>> listUsers() {
        List<User> userList = userService.listUsers();
        return new CommonResultVO<>("00000", "", userList);
    }

    @GetMapping("/{id}")
    public CommonResultVO<User> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return new CommonResultVO<>("00000", "", user);
    }

    @PostMapping("")
    public CommonResultVO<Integer> createUser(User user) {
        int result = userService.create(user);
        return new CommonResultVO<>("00000", "", result);
    }
}
