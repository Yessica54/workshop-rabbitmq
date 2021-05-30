package com.example.consumer.application.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.consumer.application.service.InvoiceService;
import com.example.consumer.domain.model.Invoice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQConsumer {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RabbitListener(queues = "${javainuse.rabbitmq.queue}")
	public void recievedMessage(Invoice invoice) {
		try {
			invoiceService.processInvoice(invoice);
		} catch (Exception e) {
			log.error("Error processInvoice: "+e.getMessage(), e);
		}
	}
}
