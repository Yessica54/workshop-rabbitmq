package com.example.consumer.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "biller_id")
	private Biller biller;
	
	@ManyToOne
    @JoinColumn(name = "acquirer_id")
	private Acquirer acquirer;
	
	@Column(unique = true)
	private String invoiceNumber;
	
	private Date expeditionDate;
	
	private Date payDate;
	
	private String methodPayment;
	
	private Double taxTotal;
	
	private Double amountTotal;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InvoiceLine> invoiceLine;
}
