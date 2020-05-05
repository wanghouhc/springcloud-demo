服务消费者

1.配置eureka
1.引入eureka客户端依赖包
2.在application.yml中配置Eureka服务地址
3.在启动类上添加@EnableDiscoveryClient或者@EnableEurekaClient


2.修改远程调用的方法

  2.1
          String url= "http://localhost:18081/user/find/"+id;
          return restTemplate.getForObject(url,User.class);

  2.2
             List<ServiceInstance> instances = discoveryClient.getInstances("user-provider");
             ServiceInstance serviceInstance = instances.get(0);
             String instanceUrl="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/find/"+id;
             return restTemplate.getForObject(instanceUrl,User.class);

3.负载均衡的调用 eureka内部已经集成了Ribbon
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
ribbon会拦截RestTemplate之后取出"user-server"字符串，
"user-server"作为服务ID找eureka获取服务实例，之后自动进行拼接（即自动拼接地址及端口），
并使用拼接后的url重新发送请求
因此@LoadBalanced注解必须加在RestTemplate类上

Feign自身已经集成了Ribbon，因此使用Feign的时候，不需要额外引入依赖，就是使用feign来调用多个相同的服务时，
会自动使用ribbon的功能

4.在消费端配置熔断器
   <!--熔断器-->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
   </dependency>

   (2)开启熔断的注解
   在启动项配置
   @EnableHystrix

   (3)服务降级处理
   在Usercontroller中添加服务降级的方法
   /****
    * 服务降级处理方法
    * @return
    */
   public User failBack(Integer id){
       User user = new User();
       user.setUsername("服务降级,默认处理！");
       return  user;
   }


   (4)在有可能发生问题的方法上添加降级处理调用
      @HystrixCommand(fallbackMethod = "failBack")

5.在消费端配置feign  //其实就是代替调用服务提供者的方法
  5.1<!--配置feign-->
   <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
   </dependency>
  5.2 配置feignclient对接user-provider中的调用方法

  5.3 在需要的地方使用feign的调用

  5.4 在启动项中开启Feign
    @EnableFeignClients
6.feign整合其他的组件
  6.1  整合ribbon
   # 修改服务地址轮询策略，默认是轮询，配置之后变随机
     user-provider:
       ribbon:
         #轮询
         NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule
         ConnectTimeout: 10000 # 连接超时时间
         ReadTimeout: 2000 # 数据读取超时时间
         MaxAutoRetries: 1 # 最大重试次数(第一个服务)
         MaxAutoRetriesNextServer: 0 # 最大重试下一个服务次数(集群的情况才会用到)
         OkToRetryOnAllOperations: false # 无论是请求超时或者socket read timeout都进行重试

   6.2  整合Hystrix
     1. 在配置文件application.yml中开启feign熔断器支持
     2. 编写FallBack处理类，实现FeignClient客户端
        创建一个类`com.itheima.feign.fallback.UserClientFallback`，
        实现刚才编写的UserClient，作为FallBack的处理类,//就是在userclient的类基础上添加熔断功能，组成一个新的类
     3. 在@FeignClient注解中，指定FallBack处理类。  //处理类的返回值要和熔断方法的返回值一致
     4. 测试

   6.3  请求压缩
   SpringCloudFeign支持对请求和响应进行GZIP压缩，以减少通信过程中的性能损耗
        6.3.1 通过配置开启请求与响应的压缩功能
              feign:
              	compression:
                      request:
                          enabled: true # 开启请求压缩
                      response:
                          enabled: true # 开启响应压缩
        6.3.2 也可以对请求的数据类型，以及触发压缩的大小下限进行设置
              #  Feign配置
              feign:
              	compression:
              		request:
              			enabled: true # 开启请求压缩
              			mime-types:	text/html,application/xml,application/json # 设置压缩的数据类型
              			min-request-size: 2048 # 设置触发压缩的大小下限
              			#以上数据类型，压缩大小下限均为默认值
   6.4 Feign的日志级别配置
       1. 在application.yml配置文件中开启日志级别配置
       2. 编写配置类，定义日志级别bean。
       3. 在接口的@FeignClient中指定配置类
       4. 重启项目，测试访问
