package com.liao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/13 15:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulMain8007 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulMain8007.class, args);
    }
}
