package com.liao.springcloud.controller;

import com.liao.springcloud.dto.UserDTO;
import com.liao.springcloud.entity.User;
import com.liao.springcloud.service.IUserService;
import com.liao.springcloud.vo.CommonResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("")
    public CommonResultVO listUsers() {
        List<UserDTO> userList = userService.listUsers();
        return CommonResultVO.success(userList);
    }

    @GetMapping("/{id}")
    public CommonResultVO getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setServerPort(serverPort);
        return CommonResultVO.success(userDTO);
    }

    @PostMapping("")
    public CommonResultVO createUser(UserDTO userDTO) {
        int result = userService.create(userDTO);
        return CommonResultVO.success(result);
    }

    @GetMapping("/feign/timeout")
    public String testTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
