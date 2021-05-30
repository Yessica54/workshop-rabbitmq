package com.example.producer.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Acquirer {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String document;
	
	@NotBlank
	private String typeDocument;
	
	private String address;
	
	private String phone;
	
	@Email
	private String email;
}
