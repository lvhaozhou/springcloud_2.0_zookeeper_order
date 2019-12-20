package com.lhz.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: lhz
 * @Date: 2019/12/18 0018 11:20
 * @Description: ribbon本地负载均衡效果
 * @Version: 1.0
 */
@RestController
public class ExtRibbonController {
    //可以获取注册中心的服务
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    //纯手写ribbon 负载均衡
    private AtomicInteger reqCount = new AtomicInteger(1);//接口请求总数  在启动一台应该清0
    @RequestMapping("/ribbonMember")
    public String ribbonMember() {
        //获取对呀服务器调用地址列表
        String instancesUrl = getInstances() + "/getMember";
        System.out.println("instancesUrl:" + instancesUrl);
        //在使用rest方式发送请求， 可以用hettpClient
        return restTemplate.getForObject(instancesUrl, String.class);
    }

    private String getInstances() {
        List<ServiceInstance> instances = discoveryClient.getInstances("zk-member");
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }
        //获取服务器集群的个数
        int instancesSize = instances.size();
        ServiceInstance instance = instances.get(reqCount.intValue() % instancesSize);
        reqCount.getAndIncrement();
        return instance.getUri().toString();
    }

}
