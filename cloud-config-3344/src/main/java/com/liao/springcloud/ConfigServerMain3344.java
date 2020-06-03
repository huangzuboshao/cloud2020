package com.liao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/6/3 18:32
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerMain3344 {
    public static void main(String[] args){
        SpringApplication.run(ConfigServerMain3344.class,args);
    }
}
