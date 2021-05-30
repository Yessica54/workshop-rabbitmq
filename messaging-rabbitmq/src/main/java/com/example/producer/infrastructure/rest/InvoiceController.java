package com.example.producer.infrastructure.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.application.exceptions.AmountException;
import com.example.producer.application.service.ServiceInvoice;
import com.example.producer.application.vo.RequestCreateInvoice;
import com.example.producer.application.vo.Response;
import com.example.producer.domain.model.Invoice;

@RestController
@RequestMapping(value = "/invoice/")
@Validated
public class InvoiceController {
	
	@Autowired
	ServiceInvoice serviceInvoice;

	@PostMapping(value = "/create")
	public Response createdInvoice(@Valid  @RequestBody Invoice request) throws Exception {
		return serviceInvoice.sendInvoice(request);
	}
}	
