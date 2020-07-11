package com.kafka.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String userId;
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<PhoneCall> phoneCalls;
	
	@Override
	public String toString() {
		return "{\"customer userId\": \"" + this.getUserId() + "\" }";
	}
}
