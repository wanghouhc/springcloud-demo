package com.huangchuan.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description:
 * User: HC
 * Date: 2020-05-05-0:00
 */
@Component
public class MyParamGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    /**
     * 处理过程  默认需要在配置配置文件中配置 NAME ,VALUE
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String name111=config.getName();//获取参数名name的值
                String value111 = config.getValue();//获取参数名value的值
                System.out.println("获取配置中的参数的NAME值:" + name111);
                System.out.println("获取配置中的参数的VALUE值:" + value111);
                //获取参数值
                String name = exchange.getRequest().getQueryParams().getFirst("name");
                if (!StringUtils.isEmpty(name)) {
                    System.out.println("哈哈:" + name);
                }
                //添加到头信息或者作为参数传递等等.
                return chain.filter(exchange);
            }
        };
    }
}
