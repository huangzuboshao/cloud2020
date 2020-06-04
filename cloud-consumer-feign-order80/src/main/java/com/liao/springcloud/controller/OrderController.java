package com.liao.springcloud.controller;

import com.liao.springcloud.service.PaymentService;
import com.liao.springcloud.vo.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangzuboshao
 * @date 2020/5/14 16:20
 */
@RestController
@RequestMapping("/consumer/orders")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public CommonResultVO listUsers() {
        return paymentService.listUsers();
    }

    @GetMapping("/{id}")
    public CommonResultVO getUser(@PathVariable Long id) {
        return paymentService.getUser(id);
    }

    @GetMapping("/feign/timeout")
    public String testTimeOut() {
        //默认OpenFeign等待1s
        return paymentService.testTimeOut();
    }

    @GetMapping("/test_zipkin")
    public String testZipkin() {
        //默认OpenFeign等待1s
        return paymentService.testZipkin();
    }
}
