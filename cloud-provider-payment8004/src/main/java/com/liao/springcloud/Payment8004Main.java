package com.liao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * {@link EnableDiscoveryClient}该注解用于使用consul或zookeeper作为注册中心注册服务
 *
 * @author huangzuboshao
 * @date 2020/5/13 10:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8004Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8004Main.class, args);
    }
}
