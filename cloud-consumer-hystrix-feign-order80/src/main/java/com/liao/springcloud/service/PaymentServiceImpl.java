package com.liao.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/16 23:58
 */
@Component
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentOk111(Integer id) {
        return "服务降级OK111";
    }

    @Override
    public String paymentTimeout(Integer id) {
        return "服务降级Timeout111";
    }

    @Override
    public String paymentFallback(Integer id) {
        log.info("服务降级Fallback");
        return "服务降级Fallback";
    }
}
