package com.liao.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * fallback 优先级高于fallbackFactory
 *
 * @author huangzuboshao
 * @date 2020/5/14 20:55
 */
@FeignClient(value = "HYSTRIX-PROVIDER-PAYMENT-SERVICE", fallback = PaymentServiceImpl.class, fallbackFactory = PaymentFallbackFactory.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentOk111(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentTimeout(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/fallback/{id}")
    String paymentFallback(@PathVariable("id") Integer id);
}
