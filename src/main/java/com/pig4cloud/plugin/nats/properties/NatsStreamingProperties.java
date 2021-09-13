package com.pig4cloud.plugin.nats.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 *
 * @author 谢宇 Date: 2019/7/9
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.nats.streaming")
public class NatsStreamingProperties  {

    private String urls = "nats://localhost:4222";

    private String clusterId = "test-cluster";

    private String clientId;

    private int reConnInterval = 20;

    private int maxPingsOut = 3;

    private int pingInterval = 3;
}
