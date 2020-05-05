package com.huangchuan.dao;

import com.huangchuan.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: HC
 * Date: 2020-05-03-20:46
 */

/**
 * JpaRepository<User,Integer>  user:表对应的pojo类   integer：主键的类型
 */
public interface UserDao extends JpaRepository<User,Integer>{
}
