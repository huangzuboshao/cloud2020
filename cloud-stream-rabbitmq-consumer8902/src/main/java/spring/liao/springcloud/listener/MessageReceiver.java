package spring.liao.springcloud.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/6/4 11:58
 */
@EnableBinding(Sink.class)
public class MessageReceiver {

    @StreamListener(Sink.INPUT)
    public void getMessage(Message<String> message) {
        System.out.println("消费者2号>>>" + message.getPayload());
    }
}
