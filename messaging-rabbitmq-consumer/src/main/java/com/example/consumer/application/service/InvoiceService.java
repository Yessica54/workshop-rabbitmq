package com.example.consumer.application.service;

import com.example.consumer.domain.model.Invoice;

public interface InvoiceService {
	
	void processInvoice(Invoice invoice) throws Exception;
}
