package com.example.consumer.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.domain.model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice,Integer>{

	Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

}
