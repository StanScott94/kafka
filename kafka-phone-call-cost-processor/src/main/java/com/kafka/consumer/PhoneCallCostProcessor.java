package com.kafka.consumer;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kafka.model.Customer;
import com.kafka.model.PhoneCall;
import com.kafka.model.PhoneCallCostDetail;
import com.kafka.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PhoneCallCostProcessor {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${phonecall.rate-per-second}")
	private double ratePerSecond;
	
	@Value("${phonecall.rate-per-mb}")
	private double ratePerMB;

    @KafkaListener(topics = "advice-topic", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(ConsumerRecord<String, Customer> cr,  @Payload Customer payload) {
        log.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", 
        		cr.key(), typeIdHeader(cr.headers()), payload, cr.toString());
        
        Customer customer = processPhoneCallCost(payload);
        
        //TODO: add code to retreive customer if exists and append call an bill
        // then functionality for user to check total bill
        customerRepository.save(customer);
        Customer fromRepo = customerRepository.findByUserId(customer.getUserId());
        System.out.println("1 " + customer);
        System.out.println("2 " + fromRepo);
    }

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
                .filter(header -> header.key().equals("__TypeId__"))
                .findFirst().map(header -> new String(header.value())).orElse("N/A");
    }
	
	public Customer processPhoneCallCost(Customer customer) {		
		for (PhoneCall phoneCall :  customer.getPhoneCalls()) {
			PhoneCallCostDetail phoneCallCostDetail = new PhoneCallCostDetail();
			phoneCallCostDetail.setPhoneCall(phoneCall);
			phoneCallCostDetail.setCallCost(phoneCall.getDuration() * this.ratePerSecond);
			phoneCallCostDetail.setDataCost(phoneCall.getData() * this.ratePerMB);
			phoneCall.setPhoneCallCostDetail(phoneCallCostDetail);
		}
		return customer;
	}
}
