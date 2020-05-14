package com.liao.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign相关配置
 *
 * @author huangzuboshao
 * @date 2020/5/14 17:01
 */
@Configuration
public class FeignConfig {

    /**
     * Feign调用日志配置
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
