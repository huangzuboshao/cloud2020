package com.liao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/6/3 21:21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientMain3355 {
  public static void main(String[] args){
      SpringApplication.run(ConfigClientMain3355.class,args);
  }
}
