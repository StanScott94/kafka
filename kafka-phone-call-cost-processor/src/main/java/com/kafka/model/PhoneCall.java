package com.kafka.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class PhoneCall {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private long duration;
	private long data;
	@JsonBackReference
	@ManyToOne(optional=false)
	private Customer customer;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	private PhoneCallCostDetail phoneCallCostDetail;

	@Override
	public String toString() {
		return "{\"duration\": \"" + this.getDuration()
			+ "\", \"data\": \"" + this.getData() + "\" }";
	}
}
