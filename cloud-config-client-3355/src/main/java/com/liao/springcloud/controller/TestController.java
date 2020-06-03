package com.liao.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/6/3 21:47
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${config.version}")
    private String configVersion;

    @GetMapping("/test_config")
    public String getConfigVersion() {
        return configVersion;
    }
}
