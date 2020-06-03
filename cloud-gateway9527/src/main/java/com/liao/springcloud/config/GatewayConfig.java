package com.liao.springcloud.config;

import com.liao.springcloud.filter.AuthSignatureFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author huangzuboshao
 * @date 2020/6/2 19:09
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public AuthSignatureFilter authSignatureFilter() {
        return new AuthSignatureFilter();
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("customer_filter_router",r -> r.path("/api/users/**")
                        .filters(f -> f.filter(authSignatureFilter()))
                        .uri("lb://provider-payment-service")
                        .order(0)
                )
                .build();
    }

}
