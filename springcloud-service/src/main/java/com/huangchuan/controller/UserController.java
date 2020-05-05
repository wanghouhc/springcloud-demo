package com.huangchuan.controller;

import com.huangchuan.pojo.User;
import com.huangchuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-20:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/find/{id}")
    public User findById(@PathVariable("id") Integer id){
        User user = userService.findByUserId(id);
        user.setUsername(user+"     user-provider");
        return user;
    }
}
