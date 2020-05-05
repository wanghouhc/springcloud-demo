package com.huangchuan.controller;

import com.huangchuan.feign.UserClient;
import com.huangchuan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: HC
 * Date: 2020-05-04-20:44
 */
@RestController
@RequestMapping("/feign")
public class ConsumerFeignController {

    @Autowired
    private UserClient userClient;


    @RequestMapping("/{id}")
    public User queryById(@PathVariable(value = "id")Integer id){
        return userClient.findById(id);
    }
}
