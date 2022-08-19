package com.wareeyes.app;

import com.wareeyes.app.database.data.DataDriverDB;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private DataDriverDB db;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner clr(KafkaTemplate<String, Long> kafkaTemplate) {
		return args -> {
			Thread thread1 = new Thread(() -> {
				while (true) {
					long randomInterval = (long)(Math.random() * 35) + 25;

					try {
						Thread.sleep(randomInterval * 1000);
						long rand = (long)(Math.random() * 7000) + 4000;
						kafkaTemplate.send("Inventory-Quantity", rand);
					} catch (Exception e){
						System.out.println("Something went wrong!");
					}
				}
			});

			Thread thread2 = new Thread(() -> {
				while (true) {
					long randomInterval = (long)(Math.random() * 35) + 25;

					try {
						Thread.sleep(randomInterval * 1000);
						long rand = (long)(Math.random() * 300) + 150;
						kafkaTemplate.send("Current-Number-Of-Employees", rand);
					} catch (Exception e){
						System.out.println("Something went wrong!");
					}
				}
			});

			Thread thread3 = new Thread(() -> {
				while (true) {
					long randomInterval = (long)(Math.random() * 35) + 25;

					try {
						Thread.sleep(randomInterval * 1000);
						long rand = (long)(Math.random() * 1000) + 300;
						kafkaTemplate.send("Transactions-Completed", rand);
					} catch (Exception e){
						System.out.println("Something went wrong!");
					}
				}
			});

			Thread thread4 = new Thread(() -> {
				while (true) {
					long randomInterval = (long)(Math.random() * 35) + 25;

					try {
						Thread.sleep(randomInterval * 1000);
						long rand = (long)(Math.random() * 300) + 150;
						kafkaTemplate.send("Deliveries-Sent", rand);
					} catch (Exception e){
						System.out.println("Something went wrong!");
					}
				}
			});

			Thread thread5 = new Thread(() -> {
				while (true) {
					long randomInterval = (long)(Math.random() * 35) + 25;

					try {
						Thread.sleep(randomInterval * 1000);
						long rand = (long)(Math.random() * 300) + 150;
						kafkaTemplate.send("Deliveries-Received", rand);
					} catch (Exception e){
						System.out.println("Something went wrong!");
					}
				}
			});

			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();

		};
	}
}
