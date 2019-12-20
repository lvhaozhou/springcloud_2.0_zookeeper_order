package com.lhz.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: lhz
 * @Date: 2019/12/7 0007 15:52
 * @Description: member启动类
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMember {
    //@EnableDiscoveryClient 要想将一个微服务注册到Eureka Server（或其他服务发现组件，例如Zookeeper、Consul等
    public static void main(String[] args) {
        SpringApplication.run(OrderMember.class, args);
    }

    @Bean
    /*@LoadBalanced*/
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
