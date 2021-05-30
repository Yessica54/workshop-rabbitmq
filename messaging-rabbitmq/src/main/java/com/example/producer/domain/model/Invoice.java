package com.example.producer.domain.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	
	private UUID uuid = UUID.randomUUID();
	
	private Biller biller;
	
	private Acquirer acquirer;
	
	@NotBlank
	private String invoiceNumber;
	
	@NotNull
	private Date expeditionDate;
	
	@NotNull
	private Date payDate;
	
	@NotBlank
	private String methodPayment;
	
	@NotNull
	private Double taxTotal;
	
	@NotNull
	private Double amountTotal;
	
	private List<InvoiceLine> invoiceLine;
}
