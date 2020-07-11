package com.kafka.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
public class Customer {
	
	private Long id;
	private String userId;
	@JsonManagedReference
	private List<PhoneCall> phoneCalls;
	
	@Override
	public String toString() {
		return "{\"customer userId\": \"" + this.getUserId() + "\" }";
	}
}
