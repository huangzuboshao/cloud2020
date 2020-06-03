package com.liao.springcloud.filter;

import com.liao.springcloud.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 记时统计
 *
 * @author huangzuboshao
 * @date 2020/5/18 18:03
 */
@Component
@Slf4j
public class CountTimeFilter implements GlobalFilter, Ordered {

    /**
     * 记时开始字段
     */
    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis());
        ServerHttpRequest request = exchange.getRequest();
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(COUNT_START_TIME);
                    Long finishedTime = System.currentTimeMillis() - startTime;
                    log.info("ip:【{}】,路径:【{}】,请求时长:{}ms", IPUtils.getIpAddress(request), exchange.getRequest().getURI().getPath(), finishedTime);
                }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
