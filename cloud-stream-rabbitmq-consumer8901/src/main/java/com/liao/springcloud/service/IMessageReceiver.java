package com.liao.springcloud.service;

import org.springframework.messaging.Message;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/6/4 11:06
 */
public interface IMessageReceiver {
    /**
     * 消息接收
     */
    void receive(Message<String> message);
}
