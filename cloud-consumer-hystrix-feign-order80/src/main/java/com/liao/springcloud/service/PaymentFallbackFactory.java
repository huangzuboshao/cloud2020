package com.liao.springcloud.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/17 12:11
 */
@Component
public class PaymentFallbackFactory implements FallbackFactory<PaymentService> {

    @Override
    public PaymentService create(Throwable throwable) {
        //Fallback优先级高于FallbackFactory
        return new PaymentService() {

            @Override
            public String paymentOk111(Integer id) {
                return "服务降级OK111--factory";
            }

            @Override
            public String paymentTimeout(Integer id) {
                return "服务降级Timeout111--factory";
            }

            @Override
            public String paymentFallback(Integer id) {
                return "服务降级Fallback--factory";
            }
        };
    }
}
