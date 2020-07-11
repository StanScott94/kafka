package com.kafka.producer;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.model.Customer;
import com.kafka.model.PhoneCall;

@Component
public class PhoneCallProducer {
    
    private final KafkaTemplate<String, Object> template;
	private final String topicName;
	private final String[] users = {"user1", "user2", "user3", "user4", "user5"};
	
    public PhoneCallProducer(
            final KafkaTemplate<String, Object> template,
            @Value("${topic.topic-name}") final String topicName) {
        this.template = template;
        this.topicName = topicName;
    }
	
    public void makePhoneCall(String phoneNumber) {
		Customer customer = createCustomer();
		PhoneCall phoneCall = createPhoneCall(customer);
		customer.setPhoneCalls(Arrays.asList(phoneCall));
		this.template.send(topicName, customer.getUserId(), customer);
	}
	
	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setUserId(this.users[new Random().nextInt(5)]);
		return customer;
	}
	
	private PhoneCall createPhoneCall(Customer customer) {
		PhoneCall phoneCall = new PhoneCall();
		phoneCall.setCustomer(customer);
		phoneCall.setDuration(new Random().nextInt(300));
		phoneCall.setData(new Random().nextInt(700));
		return phoneCall;
	}
}
