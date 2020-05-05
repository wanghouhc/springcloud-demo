server:
  #对无效的服务进行剔除的时间
  eviction-interval-timer-in-ms: 5000

  1.自我保护模式下，不会剔除任何服务实例
  2.自我保护模式保证了大多数服务依然可用
  3.通过enable-self-preservation配置可用关停自我保护，默认值是打开

  #关闭自我保护
  enable-self-preservation: false