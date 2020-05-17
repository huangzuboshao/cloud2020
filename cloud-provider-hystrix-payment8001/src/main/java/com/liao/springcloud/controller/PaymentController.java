package com.liao.springcloud.controller;

import com.liao.springcloud.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/14 18:15
 */
@RestController
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoOK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoFailure(id);
    }
    @GetMapping("/payment/hystrix/fallback/{id}")
    public String paymentFallback(@PathVariable("id") Integer id) {
        if (id > 0) {
            return "测试成功";
        }
        return paymentService.paymentInfoFailure(id);
    }
}
