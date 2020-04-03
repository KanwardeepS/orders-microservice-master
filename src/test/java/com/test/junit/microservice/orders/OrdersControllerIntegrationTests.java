package com.test.junit.microservice.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.test.microservices.orders.OrdersConfiguration;



@SpringBootApplication
@Import(OrdersConfiguration.class)
class OrdersMain {
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		System.setProperty("spring.config.name", "orders-server");
		SpringApplication.run(OrdersMain.class, args);
	}
}

