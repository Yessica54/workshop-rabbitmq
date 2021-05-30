package com.example.producer.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.producer.application.exceptions.AmountException;
import com.example.producer.application.service.ServiceInvoice;
import com.example.producer.application.vo.Response;
import com.example.producer.domain.model.Invoice;
import com.example.producer.domain.model.InvoiceLine;
import com.example.producer.infrastructure.queue.RabbitMQSender;

import lombok.extern.java.Log;

@Service
@Log
public class ServiceInvoiceImpl implements ServiceInvoice{
	
	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Override
	public Response sendInvoice(Invoice invoice) throws Exception {
		
		log.info("Procesando Invoice UUID "+invoice.getUuid());
		
		Double sumTaxLine = invoice.getInvoiceLine().stream()
				.mapToDouble(InvoiceLine::getTaxAmount)
				.sum();
		
		Double sumAmountLine = invoice.getInvoiceLine().stream()
				.mapToDouble(InvoiceLine::getTotalAmount)
				.sum();
		
		if(!sumTaxLine.equals(invoice.getTaxTotal()) || !sumAmountLine.equals(invoice.getAmountTotal()) ){
			throw new AmountException("422", "Error in Amount", invoice.getUuid());
		}
		
		rabbitMQSender.send(invoice);
		return Response.builder()
				.code("200")
				.message("Invoice sent Successfully")
				.uuid(invoice.getUuid())
				.build();
		

	}

}
