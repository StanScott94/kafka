package com.kafka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
public class PhoneCall {

	private Long id;
	private long duration;
	private long data;
	@JsonBackReference
	private Customer customer;

	@Override
	public String toString() {
		return "{\"duration\": \"" + this.getDuration()
			+ "\", \"data\": \"" + this.getData() + "\" }";
	}
}
