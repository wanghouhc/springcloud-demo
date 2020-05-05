默认过滤器：出厂自带，实现好了拿来就用，不需要实现
  全局默认过滤器
  局部默认过滤器
自定义过滤器：根据需求自己实现，实现后需配置，然后才能用哦。
  全局过滤器：作用在所有路由上。
  局部过滤器：配置在具体路由下，只作用在当前路由上。
  AddRequestHeader对匹配上的请求加上Header
  AddRequestParameters对匹配上的请求路由
  AddResponseHeader对从网关返回的响应添加
  HeaderStripPrefix对匹配上的请求路径去除前缀

自定义全局过滤器
1.在gateway-service工程编写全局过滤器类GlobalFilter,Ordered
2.编写业务逻辑代码
3.访问接口测试，加token和不加token。

自定义局部过滤器
该过滤器在控制台输出配置文件中指定名称的请求参数及参数的值,以及判断是否携带请求中参数
1. 在gateway-service中编写MyParamGatewayFilterFactory类
2. 实现业务代码：循环请求参数中是否包含name，如果包含则输出参数值,并打印在第三步配置的参数名和值
3. 修改配置文件
4. 访问请求测试，带name参数