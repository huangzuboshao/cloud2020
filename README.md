# springcloud2020

## 个人学习
# 服务注册：
## eureka: AP   -- http页面
## zookeeper: CP  -- zkCli客户端
## consul: CP   -- http页面

## 2.Ribbon学习:
``` 
 ribbon=restTemplate+负载均衡(默认轮询)
1.配置
@Bean
@LoadBalanced
2.@RibbonClient(name = "调用服务",configuration = MySelfRuleConfig.class)
其中MySelfRuleConfig自定义Irule规则自定义不被@ComponentScan扫描到

或者

配置方式:
PROVIDER-PAYMENT-SERVICE:#调用的服务
  ribbon:
    NFLoadBalancerRuleClassName: IRule实现或者自定义负载均衡算法
    #修改Feign调用超时(单位:s)
    ConnectTimeout:
    

格式如下
<clientName>:
  ribbon:
    NFLoadBalancerClassName: 配置ILoadBalancer的实现类
    NFLoadBalancerRuleClassName: 配置IRule的实现类
    NFLoadBalancerPingClassName: 配置IPing的实现类
    NFWSServerListClassName: 配置ServerList的实现类
    NIWSServerListFilterClassName: 配置ServerListFilter的实现类

```

## 3.OpenFeign
```
1.声明式客户端服务接口:@FeignClient("要调用的服务名")
2.主启动添加  @EnableFeignClients
3.默认OpenFeign超时1s,修改可通过ribbon.ConnectTimeout和ribbon.ReadTimeout(全局)调整；局部可通过(服务名.ribbon.ReadTimeout)
```

## 4.Hystrix--处理延迟和容错的库
```
1.服务降级 - fallback(备选响应)  兜底的解决方案 例如： 服务器忙，请稍后重试，不让客户端等待立刻返回一个友好提示fallback
    程序运行异常
    超时
    服务熔断触发
    线程池/信号量打满导致
服务熔断 - break  类似保险丝，达到最大服务访问，直接拒绝
服务限流 - flowlimit  秒杀高并发，排队一秒N个，有序进行

1.
# 设置服务熔断时限，默认值为1000ms
#hystrix:
#  command:
#    default:
#      execution:
#        isolation:
#          thread:
#            timeoutInMilliseconds: 1000

2.
 @HystrixCommand(fallbackMethod = "error", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000")
    })
    @GetMapping("/consumer/payment/hystrix/ok/{id}"){...}
    
   或者
   类指定@DefaultProperties(defaultFallback = "errorGlobe") 指定全局,方法上注解 @HystrixCommand
   
3. 由feignclient指定的实现类描述服务降级
@FeignClient(value = "HYSTRIX-PROVIDER-PAYMENT-SERVICE",fallback = PaymentServiceImpl.class)或者fallbackFactory=PaymentFallbackFactory.class

4.服务熔断:
@HystrixCommand(fallbackMethod = "circuitBreaker", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),//开启熔断器
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "60"),//失败率达到多少%断路器打开
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),//请求次数
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "10000")//时间窗口期
})

5.主启动添加@EnableCircuitBreaker--启动服务熔断  / @EnableHystrix

6.hystrix-rundashboard监控:
@EnableHystrixDashboard
消费端添加:
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
```

# 5.cloud-gateway:  路由转发+执行过滤链
```
反向代理，鉴权，流量控制，熔断，日志监控...

动态路由:匹配任何请求属性
可以对路由指定Predicate(断言)和filter过滤器，易于编写
集成hystrix断路器
集成服务发现功能
限流;路径重写

性能：API高可用，负载均衡，容错机制。
安全：权限身份认证、脱敏，流量清洗，后端签名（保证全链路可信调用）,黑名单（非法调用的限制）。
日志：日志记录（spainid,traceid）一旦涉及分布式，全链路跟踪必不可少。
缓存：数据缓存。
监控：记录请求响应数据，api耗时分析，性能监控。
限流：流量控制，错峰流控，目前有漏桶算法、令牌桶算法也可以定制限流规则。
灰度：线上灰度部署，可以减小风险。
路由：动态路由规则。
静态：代理

cloud  F版之前推荐的Zuul  ;对比
zuul1.x：servlet2.5阻塞架构
gateway：基于netty非阻塞 webflux Reactive
  代理请求之前pre和之后post
    Pre: 参数校验 权限校验 流量监控  日志输出 协议转换等
    post: 响应内容 响应头修改 日志的输出 流量监控 等


RoutePredicateFactory:19种动态路由匹配断言工厂 --After==可用于提前上线约定ZoneDateTime.now()的一个时间
url: [spring-cloud gateway](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.2.RELEASE/reference/html/#gateway-request-predicates-factories
)

参考 ErrorWebExceptionHandler和ErrorWebFluxAutoConfiguration 配置自定义错误页面
           自定义路由过滤  GatewayFilter 全局过滤 GlobalFilter
           常见11中路由断言工厂
spring:
  cloud:
    gateway:
      routes:
      - id: path_route
        uri: http://www.xinyues.com
        predicates:
        - Path=/foo/{segment},/bar/{segment}
        - Query=foo, ba.
        - Method=GET
        # 时区格式由ZonedDateTime生成
        - Between=Between=2020-06-03T10:44:42.383+08:00[GMT+08:00],2020-06-03T10:46:12.383+08:00[GMT+08:00]
        ...
        
        或
        
   @Bean
   public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
      return builder.routes()
            .route("host_route", r -> r.host("peer1:8080")
                  .uri("http://jd.com"))
            .build();
   }


自定义过滤器:  implements GlobalFilter,Ordered    implements GatewayFilter,Ordered
```

# 6.config + bus
```
1.config+bus
2.springcloud alibaba nacos
3.携程apllo

boostrap.yml--系统级 --比application.yml 优先级高
boostrap Context [从外部加载配置属性并解析配置]作为Application Context的父上下文。。共享一个从外部获取的Environment


spring-cloud-starter-config
eg:
#暴露bus刷新配置端点
management:
  endpoints:
    web:
      exposure:
        include:
        
[config_client]:[port]/actuator/refresh

或者
spring-cloud-config-server
spring-cloud-starter-bus-amqp
curl -X POST [config_server]:[port]/actuator/bus-refresh  全局通知
curl -X POST [config_server]:[port]/actuator/bus-refresh/{destination} 定点通知。其中destination  客户端:port
```
# 7.cloud stream  -- 遵循发布-订阅模式
！[cloud Stream 模型](https://github.com/huangzuboshao/cloud2020/blob/master/stream.png)
```
屏蔽底层消息中间件的差异，降低切换成本，统一消息模型

消息通道MessageChannel子接口SubscribableChannel 由MessageHandler消息处理器订阅

topic主题广播----RabbitMq 就是Exchange  kafka 中对应topic 和partion 分区


@Input   输入信道,通过输入信道进入应用程序
@Output  输出信道,通过输出信道离开应用程序
@StreamListener  监听队列，用于消费者队列的消息接收
@EnableBinding 信道channel 和exchange绑定在一起



消息生产者:
    source->channel->Binder
                        |
               MQ组件(rabbitmq,kafka)
消息消费者               |
    sink<-channel<- Binder


生产者:
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送管道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        return serial;
    }    
}

消费者：
@EnableBinding(Sink.class)
@Component
public class MessageReceiver {

    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message) {
        System.out.println("消费者1号,serverPort>>>" + message.getPayload());
    }
}
    
    
如果订单集群，会出现重复即一个订单被两个服务拿到，怎么持久化呢？
答: stream分组 group

stream中，同一个group中的多个消费者是竞争关系，能够保证消息只被其中一个应用消费一次
不同组可以全面消费(重复)

exchange下 bindings中默认生成多个queue绑定的这个exchange,成了不同queue,形成重复消费;自定义配置分为一组，解决重复消费

spring:
  application:
    name: cloud-stream-consumer-service
  cloud:
    stream:
      binders: #配置要绑定的rabbitmq的服务信息
        defaultRabbit: #定义的名称,用于binding整合,[自定义]
          type: rabbit #消息组件类型
          enviroment:
            rabbitmq:
              host: localhost
              port: 5672
              username: guest
              password: guest
      bindings: #服务的整合处理
        input: #通道名称,[自定义]
          destination: ownerExchange #要使用的exchange名称
          group: A #解决重复消费，分为同一组 Queue【ownerExchange.A】 N个consumers
          contentType: application/json
          binder: defaultRabbit #设置要绑定的消息服务的具体设置

```

# 8 zipkin 

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>

spring:
  zipkin:
    base-url: http://localhost:9411/
  sleuth:
    sampler:
      probability: 1

```