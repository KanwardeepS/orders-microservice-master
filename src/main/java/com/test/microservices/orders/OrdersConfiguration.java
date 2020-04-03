package com.test.microservices.orders;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@ComponentScan
@EntityScan("com.test.microservices.orders")
@EnableJpaRepositories("com.test.microservices.orders")
@PropertySource("classpath:db-config.properties")
public class OrdersConfiguration {

	protected Logger logger;

	public OrdersConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data-orders.sql").build();

		logger.info("dataSource = " + dataSource);


		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> orders = jdbcTemplate.queryForList("SELECT price FROM ORDERS");
		logger.info("System has " + orders.size() + " records");

		return dataSource;
	}
}
