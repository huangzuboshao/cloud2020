package com.liao.springcloud.service;

import com.liao.springcloud.vo.CommonResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/14 16:25
 */
@FeignClient(value="PROVIDER-PAYMENT-SERVICE")
public interface PaymentService {

    @GetMapping("/api/users")
    CommonResultVO listUsers();

    @GetMapping("/api/users/{id}")
    CommonResultVO getUser(@PathVariable("id") Long id);

    @GetMapping("/api/users/feign/timeout")
    String testTimeOut();
}
