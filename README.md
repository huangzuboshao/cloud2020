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
1.声明式服务接口:@FeignClient("要调用的服务名")
2.主启动添加  @EnableFeignClients
3.默认OpenFeign超时1s,修改可通过ribbon.ConnectTimeout和ribbon.ReadTimeout(全局)调整；局部可通过(服务名.ribbon.ReadTimeout)
```