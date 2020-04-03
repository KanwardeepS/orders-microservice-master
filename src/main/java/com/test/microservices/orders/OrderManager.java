package com.test.microservices.orders;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderManager {

	private static final String BUY = "Buy";
	private static final String SELL = "Sell";
	private OrdersRepository ordersRepository;

	public OrderManager() {
		super();
	}

	Map<Integer, Orders> orderToSell = new ConcurrentHashMap<Integer, Orders>();
	Map<Integer, Orders> orderToBuy = new ConcurrentHashMap<Integer, Orders>();
	
	public OrderManager( OrdersRepository ordersRepository ) {
		super();
		setOrdersRepository( ordersRepository );
	}

	public synchronized List<Orders> placeOrder( Orders order ) {
		Orders newOrder = new Orders( order.getType(), order.getPrice(), order.getQuantity(), order.getOrdertime() );
		ordersRepository.save( newOrder );
		if ( SELL.equals( order.getType() ) ) {
			orderToSell.put( newOrder.getId(), newOrder );
		}else if(BUY.equals( order.getType() )) {
			orderToBuy.put( newOrder.getId(), newOrder );
		}
		checkAndPlaceBuy();
		return ordersRepository.findAll();
	}

	private void checkAndPlaceBuy() {
		for(Orders buyOrder: orderToBuy.values()) {
		for(Orders saleOrder: orderToSell.values()) {
			if(saleOrder.getPrice().compareTo( buyOrder.getPrice() )>=0) {
				processBuy(buyOrder,saleOrder);
			}
		}
		}
	}

	private void processBuy( Orders buyOrder, Orders sellingOrder ) {
		if(buyOrder.getQuantity()<sellingOrder.getQuantity()) {
			sellingOrder.setQuantity( sellingOrder.getQuantity()-buyOrder.getQuantity() );
			orderToSell.put(sellingOrder.getId(),sellingOrder);
			orderToBuy.remove( buyOrder.getId() );
			ordersRepository.delete( buyOrder );
			ordersRepository.save( sellingOrder );
		}else if(buyOrder.getQuantity()>=sellingOrder.getQuantity()) {
			buyOrder.setQuantity( buyOrder.getQuantity()-sellingOrder.getQuantity() );
			orderToSell.remove( sellingOrder.getId() );
			orderToBuy.remove( buyOrder.getId() );
			ordersRepository.delete( sellingOrder );
			ordersRepository.delete( buyOrder );
		}
		
	}

	public OrdersRepository getOrdersRepository() {
		return ordersRepository;
	}

	public void setOrdersRepository( OrdersRepository p_ordersRepository ) {
		ordersRepository = p_ordersRepository;
	}

}
