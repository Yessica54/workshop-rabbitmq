package com.example.producer.infrastructure.queue;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.producer.domain.model.Invoice;

@Component
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;	
	
	@Async
	@Retryable(value = AmqpException.class, maxAttempts = 2)
	public void send(Invoice invoice) throws AmqpException{
		rabbitTemplate.convertAndSend(exchange, routingkey, invoice);
		System.out.println("Send msg = " + invoice);
	    
	}
}
