package com.liao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/13 16:37
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulConsumerMain80 {
    public static void main(String[] args){
        SpringApplication.run(ConsulConsumerMain80.class,args);
    }
}
