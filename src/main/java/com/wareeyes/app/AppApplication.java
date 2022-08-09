package com.wareeyes.app;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;


import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan
public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner clr(KafkaTemplate<String, Long> kafkaTemplate) {
		return args -> {
			while(true) {
				TimeUnit.SECONDS.sleep(3);
				long rand1 = (long)(Math.random() * 200) + 1;
				long rand2 = (long)(Math.random() * 200) + 1;
				long rand3 = (long)(Math.random() * 10000) + 1;
				long rand4 = (long)(Math.random() * 1000) + 1;

				kafkaTemplate.send("Current-Number-Of-Employees", rand1);
				kafkaTemplate.send("Deliveries-Sent", rand2);
				kafkaTemplate.send("Inventory-Quantity", rand3);
				kafkaTemplate.send("Transactions-Completed", rand4);
			}
		};
	}
}
