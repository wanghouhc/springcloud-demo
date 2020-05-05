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
