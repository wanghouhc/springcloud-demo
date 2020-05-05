package com.huangchuan.feign.util;

import feign.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * User: HC
 * Date: 2020-05-04-21:35
 */
@Configuration
public class FeignConfig {

    /**
     * 日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
