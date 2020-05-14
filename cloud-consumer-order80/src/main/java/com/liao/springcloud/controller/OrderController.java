package com.liao.springcloud.controller;

import com.liao.springcloud.constant.ResultStatusEnum;
import com.liao.springcloud.vo.CommonResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/9 18:49
 */
@RestController
@RequestMapping("/consumer/orders")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final String PAYMENT_SERVICE = "http://PROVIDER-PAYMENT-SERVICE";

    @GetMapping("")
    public CommonResultVO listOrders() {
        return restTemplate.getForObject(PAYMENT_SERVICE + "/api/users", CommonResultVO.class);
    }

    @GetMapping("/{id}")
    public CommonResultVO getOrder(@PathVariable Integer id) {
        return restTemplate.getForObject(PAYMENT_SERVICE + "/api/users/" + id, CommonResultVO.class);
    }

    @GetMapping("/order/discovery")
    public Object discovery() {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        instanceList.forEach(t -> System.out.println(t.getServiceId() + "\t" + t.getHost() + "\t" + t.getPort() + "\t" + t.getUri()));
        return discoveryClient;
    }

    @GetMapping("/getForEntity/{id}")
    public CommonResultVO getOrderForEntity(@PathVariable Integer id) {
        ResponseEntity<CommonResultVO> entity = restTemplate.getForEntity(PAYMENT_SERVICE + "/api/users/" + id, CommonResultVO.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }else{
            return CommonResultVO.failure(ResultStatusEnum.ERROR);
        }
    }

}
