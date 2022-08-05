package com.wareeyes.app;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner clr(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			while(true) {
				TimeUnit.SECONDS.sleep(2);
				int rand = (int)(Math.random() * 100) + 1;
				kafkaTemplate.send("testTopic", "" + rand);
			}
		};
	}
}
