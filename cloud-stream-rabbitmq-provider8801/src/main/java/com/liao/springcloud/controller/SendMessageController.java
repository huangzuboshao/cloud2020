package com.liao.springcloud.controller;

import com.liao.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/6/4 10:51
 */
@RestController
public class SendMessageController {
    @Autowired
    private IMessageProvider messageProvider;

    @GetMapping("/send")
    public String sendMessage() {
        return messageProvider.send();
    }
}
