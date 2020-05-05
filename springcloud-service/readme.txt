服务提供者

1.JPA的使用
 1.1 导入JPA的依赖
 1.2 创建和表对应的POJO类，
     并且设置
               在类上设置
               @Entity
               @Table(name = "tb_user")
               设置主键
               @Id
               @GeneratedValue(strategy = GenerationType.IDENTITY)
 1.3 设置dao
     public interface UserDao extends JpaRepository<User,Integer> {}
 1.4 创建service类 使用dao


 1.5 注册到eureka
         <!--eureka客户端-->
         <dependency>
             <groupId>org.springframework.cloud</groupId>
             <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
         </dependency>

     1.5.2 开启Eureka客户端发现功能
         添加启动项配置
           @EnableEurekaClient

     1.5.3 添加pom配置
           #指定eureka服务地址
           eureka:
             client:
               service-url:
                 # EurekaServer的地址
                 defaultZone: http://localhost:7001/eureka
     1.5.4
                #指定IP地址
                ip-address: 127.0.0.1
                #访问服务的时候，推荐使用IP
                prefer-ip-address: true
     1.5.5
                 #租约到期，服务时效时间，默认值90秒
                 lease-expiration-duration-in-seconds: 150
                 #租约续约间隔时间，默认30秒
                 lease-renewal-interval-in-seconds: 30
     1.5.6
                 失效剔除和自我保护
                 当服务正常关闭操作时，会发送服务下线的REST请求给EurekaServer。
                 服务中心接受到请求后，将该服务置为下线状态
                 服务中心每隔一段时间(默认60秒)将清单中没有续约的服务剔除。
                 通过eviction-interval-timer-in-ms配置可以对其进行修改，单位是毫秒

1.6 从配置中心获取配置

 修改配置文件为bootstrap.yml
 同时添加配置
 # 注释版本
 spring:
   cloud:
     config:
       name: user-provider # 与远程仓库中的配置文件的application保持一致，{application}-{profile}.yml
       profile: dev # 远程仓库中的配置文件的profile保持一致
       label: master # 远程仓库中的版本保持一致
       discovery:
         enabled: true # 使用配置中心
         service-id: config-server # 配置中心服务id


**配置spingcloudbus的自动更新配置的服务

1.引入依赖
<!--消息总线依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-bus</artifactId>
</dependency>
<!--RabbitMQ依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
</dependency>
<!--健康监控依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>


2.修改配置文件，增加rabbitMQ的配置
# rabbitmq的配置信息；如下配置的rabbit都是默认值，其实可以完全不配置
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
(3)添加刷新配置
@RefreshScope 用于启用刷新配置文件的信息

