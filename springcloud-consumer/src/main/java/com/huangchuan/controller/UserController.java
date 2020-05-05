package com.huangchuan.controller;

import com.huangchuan.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-22:07
 */
@RestController
@RequestMapping("/consumer")
public class UserController {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @HystrixCommand(fallbackMethod = "failBack")
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id")Integer id){
       /* String url= "http://localhost:18081/user/find/"+id;
        return restTemplate.getForObject(url,User.class);*/

        /*List<ServiceInstance> instances = discoveryClient.getInstances("user-provider");
        ServiceInstance serviceInstance = instances.get(0);
        String instanceUrl="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/find/"+id;
        return restTemplate.getForObject(instanceUrl,User.class);*/


        String url= "http://user-provider/user/find/"+id;
        return restTemplate.getForObject(url,User.class);

    }

    /****
     * 服务降级处理方法
     * @return
     */
    public User failBack(Integer id){
        User user = new User();
        user.setUsername("服务降级,默认处理！");
        return  user;
    }
}
