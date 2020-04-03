package com.test.microservices.services.orders;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.test.microservices.orders.OrdersConfiguration;
import com.test.microservices.orders.OrdersRepository;

@EnableAutoConfiguration
@Import( OrdersConfiguration.class )
public class OrdersServer {

	@Autowired
	protected OrdersRepository productsRepository;

	protected Logger logger = Logger.getLogger( OrdersServer.class.getName() );

	public static void main( String[] args ) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		System.setProperty( "spring.config.name", "orders-server" );

		SpringApplication.run( OrdersServer.class, args );
	}
}
