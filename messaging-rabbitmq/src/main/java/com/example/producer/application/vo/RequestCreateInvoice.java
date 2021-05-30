package com.example.producer.application.vo;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.example.producer.domain.model.Invoice;

import lombok.Getter;

@Getter

public class RequestCreateInvoice extends Invoice{
	private UUID uuid = UUID.randomUUID();
}
