package com.pig4cloud.plugin.nats;

import com.pig4cloud.plugin.nats.annotation.NatsStreamingSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Nats Streaming 处理类 主要是注册{@link NatsStreamingSubscribe}
 *
 * @author 谢宇 Date: 2019/7/9
 */
@Slf4j
public class NatsStreamingConfigBeanPostProcessor implements BeanPostProcessor {

	private NatsStreamingTemplate template;

	NatsStreamingConfigBeanPostProcessor(com.pig4cloud.plugin.nats.NatsStreamingTemplate template) {
		this.template = template;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		template.doSub(bean, beanName);
		return bean;
	}

}
