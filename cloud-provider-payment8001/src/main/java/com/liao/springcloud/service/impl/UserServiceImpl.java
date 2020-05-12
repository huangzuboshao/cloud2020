package com.liao.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liao.springcloud.dao.UserMapper;
import com.liao.springcloud.dto.UserDTO;
import com.liao.springcloud.entity.User;
import com.liao.springcloud.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/8 17:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserDTO> listUsers() {
        List<User> userList = userMapper.selectByLiao();
        return userList.stream().map(t -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(t, userDTO);
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public int create(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return userMapper.insert(user);
    }
}
