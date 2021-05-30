package com.example.producer.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceLine {
	
	@NotBlank
	private String description;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Double amount;
	
	@NotNull
	private Double totalAmount;
	
	@NotNull
	private Double taxAmount;
}
