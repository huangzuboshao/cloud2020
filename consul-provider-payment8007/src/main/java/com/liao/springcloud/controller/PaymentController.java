package com.liao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/13 15:46
 */
@RestController
@Slf4j
@RequestMapping("/consul")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("test")
    public Object consulTest() {
        return "consul client port:" + serverPort + UUID.randomUUID().toString();
    }
}
