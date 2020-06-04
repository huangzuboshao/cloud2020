package com.liao.springcloud.service.impl;

import com.liao.springcloud.service.IMessageReceiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author huangzuboshao
 * @date 2020/6/4 11:07
 */
@EnableBinding(Sink.class)
@Component
public class MessageReceiverImpl implements IMessageReceiver {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    @Override
    public void receive(Message<String> message) {
        System.out.println("消费者1号,serverPort>>>" + message.getPayload());
    }
}
