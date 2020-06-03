package com.liao.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/14 18:12
 */
@Service
public class PaymentServiceImpl {

    /**
     * 正常访问
     */
    public String paymentInfoOK(Integer id) {
        System.out.println("进来了");
        return "线程池：" + Thread.currentThread().getName() + "成功";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoFailHandler", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000")
    })
    public String paymentInfoFailure(Integer id) {
        int timeNumber = 4;
        int a = 10 / 0;
        /*try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "线程池：" + Thread.currentThread().getName() + "失败,耗时" + timeNumber + "s";
    }

    private String paymentInfoFailHandler(Integer id) {
        return "paymentInfoFailHandler服务降级";
    }
}
