package com.lhz.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Auther: lhz
 * @Date: 2019/12/9 0009 10:32
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class ZkOrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getOrder")
    public String getOrder() {
        String url = "http://zk-member/getMember";
        return restTemplate.getForObject(url, String.class);
    }

    //如何从注册中心获取服务信息
    @RequestMapping("/getClinetMember")
    public List<ServiceInstance>  getClinetMember() {
        List<ServiceInstance> instances = discoveryClient.getInstances("zk-member");
        for (ServiceInstance instance : instances) {
            System.out.println("url:" + instance.getUri());
        }
        return instances;
    }
}
