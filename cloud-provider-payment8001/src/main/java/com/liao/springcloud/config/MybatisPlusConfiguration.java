package com.liao.springcloud.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis-plus 配置
 *
 * @author huangzuboshao
 * @date 2020/4/20 16:11
 */
@EnableTransactionManagement
@MapperScan("com.liao.springcloud.dao")
@Configuration
public class MybatisPlusConfiguration {

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 性能分析插件
     */
    @Profile({"dev", "test"})
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //sql格式化
//        performanceInterceptor.setFormat(true);
        //sql超时5秒
        performanceInterceptor.setMaxTime(5 * 1000L);
        return performanceInterceptor;
    }
}
