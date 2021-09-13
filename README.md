### 说明

网上关于nats-streaming和springboot的资源非常匮乏，为了工作需要自制了nats-streaming的springboot starter，支持参数配置和注解式的消息订阅

### 使用方法

### 1. 引用依赖：

```xml

<dependency>
    <groupId>com.pig4cloud.plugin</groupId>
    <artifactId>nats-nats-streaming-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2 配置链接信息.application.yml

```yaml
spring:
  nats:
    streaming:
      client-id: natsServer
      urls: nats://[user:passwd@]host:port
```

更多配置详见`org.springframework.nats.properties.NatsStreamingSubProperties`
和`org.springframework.nats.properties.NatsStreamingProperties`

### 3.主题订阅

```java
@NatsStreamingSubscribe(subscribe = "testSubscribe", queue = "testQueue")
public void doSth(Message msg){
        System.out.println(msg.toString());
        }
```

代码参考： [hotcoffie/nats-streaming-spring-boot-starter](https://github.com/hotcoffie/nats-streaming-spring-boot-starter)
代码参考： [wanlinus/nats-streaming-spring](https://github.com/wanlinus/nats-streaming-spring)
