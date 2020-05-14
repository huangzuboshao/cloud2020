package com.liao.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  方式:1.
 * 不能放在{@link org.springframework.context.annotation.ComponentScan}所扫描的包及子包下,
 * 否则自定义的配置类被 所有Ribbon客户端所共享，达不到特殊化定制目的
 *
 * 方式2：application.yml配置 【客户端名称】.ribbon
*  EUREKA-PROVIDER-PAYMENT:
 *   ribbon:
 *     NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
 *
 * @author huangzuboshao
 * @date 2020/5/13 17:42
 */
@Configuration
public class MySelfRuleConfig {

    @Bean
    public IRule randomRule() {
        //定义为随机
        return new RandomRule();
    }
}
