package com.huangchuan.service;

import com.huangchuan.pojo.User;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-20:48
 */
public interface UserService {
    User findByUserId(Integer id);
}
