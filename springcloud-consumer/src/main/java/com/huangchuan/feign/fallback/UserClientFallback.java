package com.huangchuan.feign.fallback;

import com.huangchuan.feign.UserClient;
import com.huangchuan.pojo.User;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: HC
 * Date: 2020-05-04-21:21
 */
@Component
public class UserClientFallback implements UserClient{
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setUsername("Fallback，服务降级。。。");
        return user;
    }
}
