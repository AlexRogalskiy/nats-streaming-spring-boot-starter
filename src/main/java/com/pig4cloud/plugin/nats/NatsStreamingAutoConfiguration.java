package com.pig4cloud.plugin.nats;

import com.pig4cloud.plugin.nats.properties.NatsStreamingProperties;
import com.pig4cloud.plugin.nats.properties.NatsStreamingSubProperties;
import io.nats.streaming.SubscriptionOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author 谢宇 Date: 2019/7/9
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({ NatsStreamingProperties.class, NatsStreamingSubProperties.class })
public class NatsStreamingAutoConfiguration {

	@Bean
	public SubscriptionOptions options(NatsStreamingSubProperties properties) {
		SubscriptionOptions.Builder builder = new SubscriptionOptions.Builder();
		builder.maxInFlight(properties.getMaxInFlight()).ackWait(properties.getAckWait(), TimeUnit.SECONDS)
				.deliverAllAvailable();
		if (properties.isManualAcks()) {
			builder.manualAcks();
		}
		String durableName = properties.getDurableName();
		if (StringUtils.hasLength(durableName)) {
			builder.durableName(durableName);
		}
		return builder.build();
	}

	@Bean
	public NatsStreamingTemplate streamingTemplate(NatsStreamingProperties properties, SubscriptionOptions options) {
		NatsStreamingTemplate template = new NatsStreamingTemplate(options);
		template.connect(properties);
		return template;
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public NatsStreamingConfigBeanPostProcessor configBeanPostProcessor(NatsStreamingTemplate streamingTemplate) {
		return new NatsStreamingConfigBeanPostProcessor(streamingTemplate);
	}

}
