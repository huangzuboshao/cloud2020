package com.liao.springcloud.loadbanlance;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 健康检查-对于负载
 *
 * @author huangzuboshao
 * @date 2020/6/3 15:53
 */
@Slf4j
public class HealthExamination implements IPing {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public boolean isAlive(Server server) {
        String serverId = server.getId();
        String url = "http://" + serverId + "/actuator/health";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("ping 【{}】 success",url);
            return true;
        }
        log.info("ping 【{}】 fail",url);
        return false;
    }
}
