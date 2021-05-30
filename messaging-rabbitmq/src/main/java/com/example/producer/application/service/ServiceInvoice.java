package com.example.producer.application.service;

import com.example.producer.application.vo.Response;
import com.example.producer.domain.model.Invoice;

public interface ServiceInvoice {
	
	Response sendInvoice(Invoice request) throws Exception;
}
