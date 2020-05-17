package com.liao.springcloud.controller;

import com.liao.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/14 20:56
 */
@RestController
/*@DefaultProperties(defaultFallback = "errorGlobe")*/
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000")
    })
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id) {
        return paymentService.paymentOk111(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id) {
        return paymentService.paymentTimeout(id);
    }

    @GetMapping("/consumer/payment/hystrix/fallback_new/{id}")
    public String paymentFallback(@PathVariable("id") Integer id) {
        return paymentService.paymentFallback(id);
    }

    @HystrixCommand(fallbackMethod = "circuitBreaker", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "60"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "10000")
    })
    @GetMapping("/consumer/payment/hystrix/circuitBreaker/{id}")
    public String testBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new NullPointerException();
        }
        return "成功";

    }

    private String error(Integer id) {
        return "错误降级";
    }

    private String circuitBreaker(Integer id) {
        return "错误熔断";
    }

    private String errorGlobe() {
        return "全局降级";
    }
}
