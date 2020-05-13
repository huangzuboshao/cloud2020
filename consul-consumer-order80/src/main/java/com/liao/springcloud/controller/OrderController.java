package com.liao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/13 16:52
 */
@RestController
@RequestMapping("/consumer/consul")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_SERVICE = "http://consul-provider-payment";

    @GetMapping("test")
        public String test() {
        return restTemplate.getForObject(PAYMENT_SERVICE + "/consul/test", String.class);
    }
}
