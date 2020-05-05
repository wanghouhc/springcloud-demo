package com.huangchuan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: HC
 * Date: 2020-05-05-10:18
 */
@RestController
@RefreshScope //用于启用刷新配置文件的信息
@RequestMapping(value = "/config")
public class LoadConfigController {

    @Value("${test.message}")
    private String msg;

    /***
     * 响应配置文件中的数据
     * @return
     */
    @RequestMapping(value = "/load")
    public String load(){
        return msg;
    }
}