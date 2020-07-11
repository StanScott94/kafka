package com.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.kafka.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByUserId(String userId);
}
