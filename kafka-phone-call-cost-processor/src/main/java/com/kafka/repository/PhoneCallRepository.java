package com.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.kafka.model.PhoneCall;

public interface PhoneCallRepository extends CrudRepository<PhoneCall, Long> {

}
