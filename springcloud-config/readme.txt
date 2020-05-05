配置中心

配置文件的命名方式：{application}-{profile}.yml或{application}-{profile}.properties
application为应用名称
profile用于区分开发环境dev，测试环境test，生产环境pro等
  开发环境 user-dev.yml
  测试环境 user-test.yml
  生产环境 user-pro.yml

搭建步骤
1. 创建配置中心SpringBoot项目config_server
2. 配置坐标依赖
3. 启动类添加开启配置中心服务注解
4. 配置服务中心application.yml文件
5. 启动测试

- bootstrap.yml文件是SpringBoot的默认配置文件，而且其加载时间相比于application.yml更早。
- bootstrap.yml和application.yml都是默认配置文件，但定位不同
  - bootstrap.yml可以理解成系统级别的一些参数配置，一般不会变动
  - application.yml用来定义应用级别的参数
- 搭配spring-cloud-config使用application.yml的配置可以动态替换。
- bootstrap.yml相当于项目启动的引导文件，内容相对固定
- application.yml文件是微服务的常规配置参数，变化比较频繁

添加springcloud-bus和rabbitMQ来构建自动配置中心
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