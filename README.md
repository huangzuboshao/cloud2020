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