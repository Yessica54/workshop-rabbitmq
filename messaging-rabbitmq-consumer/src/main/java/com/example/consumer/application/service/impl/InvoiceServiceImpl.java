package com.example.consumer.application.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumer.application.service.InvoiceService;
import com.example.consumer.domain.model.Acquirer;
import com.example.consumer.domain.model.Biller;
import com.example.consumer.domain.model.Invoice;
import com.example.consumer.infrastructure.repository.AcquirerRepository;
import com.example.consumer.infrastructure.repository.BillerRepository;
import com.example.consumer.infrastructure.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private AcquirerRepository acquirerRepository;
	
	@Autowired
	private BillerRepository billerRepository;
	
	@Override
	public void processInvoice(Invoice invoice) throws Exception {
		
		Optional<Acquirer>  acquirer = acquirerRepository.findByDocument(invoice.getAcquirer().getDocument());
		Optional<Biller>  biller = billerRepository.findByDocument(invoice.getBiller().getDocument());
		Optional<Invoice>  invoiceFind = invoiceRepository.findByInvoiceNumber(invoice.getInvoiceNumber());
		

		if(invoiceFind.isPresent())
			throw new Exception("La Factura ya existe");
			
			if(!biller.isPresent()) 
				throw new Exception("EL facturador no existe");
				
			
			invoice.getBiller().setId(biller.get().getId());
			if(acquirer.isPresent()){
				invoice.getAcquirer().setId(acquirer.get().getId());
			}
			
			
			acquirerRepository.save(invoice.getAcquirer());
			billerRepository.save(invoice.getBiller());
			invoiceRepository.save(invoice);
		
	}

}
