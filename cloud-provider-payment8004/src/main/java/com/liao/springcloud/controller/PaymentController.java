package com.liao.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/13 10:25
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/zk/test")
    public Object zkTest() {
        return "zookeeper client port:" + serverPort + UUID.randomUUID().toString();
    }
}
