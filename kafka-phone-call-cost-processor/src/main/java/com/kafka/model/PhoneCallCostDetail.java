package com.kafka.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class PhoneCallCostDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double callCost;
	private double dataCost;
	@JsonBackReference
	@OneToOne(optional=false, cascade = CascadeType.ALL)
	private PhoneCall phoneCall;
	
	public String toString() {
		return "{\"callCost\": \"" + this.getCallCost()
				+ "\", \"dataCost\": \"" + this.getDataCost() + "\" }";
	}
}
