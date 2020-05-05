package com.huangchuan.springcloudquickstart.controller;

import com.huangchuan.springcloudquickstart.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-19:55
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/list")
    public List<User> list(){
        List<User> users = new ArrayList<User>();
        users.add(new User("王五", "深圳", 25));
        users.add(new User("李四", "北京", 23));
        users.add(new User("赵六", "上海", 26));
        return users;
    }
}
