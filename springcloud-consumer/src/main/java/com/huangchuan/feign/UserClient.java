package com.huangchuan.feign;

import com.huangchuan.feign.fallback.UserClientFallback;
import com.huangchuan.feign.util.FeignConfig;
import com.huangchuan.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * User: HC
 * Date: 2020-05-04-20:42
 */
@FeignClient(value = "user-provider",fallback = UserClientFallback.class,configuration = FeignConfig.class)
public interface UserClient {
    @RequestMapping("/user/find/{id}")
    public User findById(@PathVariable("id") Integer id);
}
