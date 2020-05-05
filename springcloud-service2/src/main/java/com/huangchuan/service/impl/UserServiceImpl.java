package com.huangchuan.service.impl;

import com.huangchuan.dao.UserDao;
import com.huangchuan.pojo.User;
import com.huangchuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-20:49
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByUserId(Integer id) {
        return userDao.findById(id).get();
    }
}
