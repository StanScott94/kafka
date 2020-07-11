package com.kafka.userinput;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kafka.producer.PhoneCallProducer;

@Component
public class UserInput implements CommandLineRunner {

	public static final String EXIT = "exit";
	
	@Autowired
	private PhoneCallProducer phoneCallProducer;

	@Override
	public void run(String... args) throws Exception {
		boolean running = true;
	    try (Scanner scanner = new Scanner(System.in)) {
			String input = "";
			
			while (running) {
				input = "";
			    System.out.print("Enter a number to make a call: ");
			    input = scanner.next();
			    
			    if (StringUtils.isNotEmpty(input)) {
				    if (!input.equals(EXIT)) {
				    	phoneCallProducer.makePhoneCall(input);
					} else {
						running = false;
					}
			    }
			}
		}
	}
}
