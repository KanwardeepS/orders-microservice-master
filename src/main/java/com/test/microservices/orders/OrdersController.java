package com.test.microservices.orders;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservices.exceptions.RecordNotFoundException;

@RestController
public class OrdersController {

	protected Logger logger = Logger.getLogger( OrdersController.class.getName() );
	protected OrdersRepository ordersRepository;
	protected OrderManager orderManager;

	@Autowired
	public OrdersController( OrdersRepository ordersRepository ) {
		this.ordersRepository = ordersRepository;
		orderManager = new OrderManager(ordersRepository);
		logger.info( "OrderRepository says system has " + ordersRepository.countOrders() + " records" );
	}

	@RequestMapping( value = "/orders/sell", method = RequestMethod.PUT )
	public List<Orders> createOrder( @RequestBody Orders order ) {
		logger.info( "orders-service createOrder() invoked: " + order );
		return orderManager.placeOrder(order);
	}

	@RequestMapping( value = "/order/buy", method = RequestMethod.POST )
	public List<Orders> createProduct( @RequestBody Orders order ) {
		logger.info( "products-service createOrder() invoked: " + order );
		ordersRepository.save( new Orders( order.getType(), order.getPrice(), order.getQuantity(), order.getOrdertime() ) );
		return ordersRepository.findAll();
	}
	
	
	@RequestMapping( "/orders/type/{orderType}" )
	public List<Orders> byType( @PathVariable( "orderType" ) String orderType ) {

		logger.info( "orders-service byType() invoked: " + orderType );
		List<Orders> orders = ordersRepository.findByType( orderType );
		logger.info( "products-service byType() found: " + orders );

		if ( orders == null )
			throw new RecordNotFoundException( orderType );
		else {
			return orders;
		}
	}

	@RequestMapping( "/orders/price/{orderPrice}" )
	public List<Orders> byPrice( @PathVariable( "orderPrice" ) String orderPrice ) {
		BigDecimal priceVal = new BigDecimal( orderPrice );
		logger.info( "orders-service byPrice() invoked: " + orderPrice );
		List<Orders> orders = ordersRepository.findByPrice( priceVal );
		logger.info( "products-service byPrice() found: " + orders );

		if ( orders == null )
			throw new RecordNotFoundException( orderPrice );
		else {
			return orders;
		}
	}

	@RequestMapping( "/orders/quantity/{orderQuantity}" )
	public List<Orders> byQuantity( @PathVariable( "orderQuantity" ) Integer orderQuantity ) {

		logger.info( "orders-service byQuantity() invoked: " + orderQuantity );
		List<Orders> orders = ordersRepository.findByQuantity( orderQuantity );
		logger.info( "products-service byBrand() found: " + orders );

		if ( orders == null )
			throw new RecordNotFoundException( orderQuantity.toString() );
		else {
			return orders;
		}
	}
	
}