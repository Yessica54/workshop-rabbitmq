package com.example.consumer.application.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
public class RabbitMQConfig {
	
	@Value("${javainuse.rabbitmq.queue}")
	String queueName;

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private Integer port;

	@Value("${spring.rabbitmq.username}")
	private String user;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.virtualhost}")
	private String virtualhost;
	
	@Bean
	public ConnectionFactory connectionFactory() throws Exception {
		final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualhost);
		return connectionFactory;
	}


	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public SimpleRabbitListenerContainerFactory customRabbitListenerContainerFactory() throws Exception {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}
}
