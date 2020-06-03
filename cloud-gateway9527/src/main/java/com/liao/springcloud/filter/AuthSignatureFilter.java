package com.liao.springcloud.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 权限校验
 *
 * Global Filter：
 * 应用在有针对性服务（例如用户服务、订单服务、商品服务），单业务自定义过滤时
 *
 * 有序的网关过滤器 {@link  org.springframework.cloud.gateway.filter.OrderedGatewayFilter} and
 * {@link org.springframework.cloud.gateway.filter}
 *
 * @author huangzuboshao
 * @date 2020/5/18 17:54
 */
public class AuthSignatureFilter implements GatewayFilter, Ordered {

    @Override
    public int getOrder() {
        return -400;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = exchange.getRequest().getQueryParams().getFirst("authToken");
        if (StringUtils.isEmpty(authToken)) {
            //权限不通过返回401
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //继续走过滤
        return chain.filter(exchange);
    }
}
