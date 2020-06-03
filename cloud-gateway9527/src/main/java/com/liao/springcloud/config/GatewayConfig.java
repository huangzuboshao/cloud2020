package com.liao.springcloud.config;

import com.liao.springcloud.filter.AuthSignatureFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author huangzuboshao
 * @date 2020/6/2 19:09
 */
@Configuration
public class GatewayConfig {

    @Bean
    public AuthSignatureFilter authSignatureFilter() {
        return new AuthSignatureFilter();
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/users/**")
                        .filters(f -> f.filter(authSignatureFilter()))
                        .uri("lb://provider-payment-service")
                        .id("customer_filter_router")
                        .order(0)
                )
                .build();
    }

}
