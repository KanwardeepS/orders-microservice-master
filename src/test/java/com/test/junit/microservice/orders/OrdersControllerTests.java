package com.test.junit.microservice.orders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.test.microservices.orders.Orders;
import com.test.microservices.orders.OrdersController;
import com.test.microservices.orders.OrdersRepository;

public class OrdersControllerTests {

	protected static final String ORDER_TYPE_1 = "Buy";
	protected static final BigDecimal ORDER_PRICE_1 = new BigDecimal( 1000 );
	protected static final Integer ORDER_QUANTITY_1 = 10;
	protected static final Timestamp ORDER_TIMESTAMP_1 = new Timestamp(Calendar.getInstance().getTimeInMillis());
	protected static final Orders theOrder = new Orders( ORDER_TYPE_1, ORDER_PRICE_1, ORDER_QUANTITY_1,ORDER_TIMESTAMP_1 );

	@Autowired
	OrdersController ordersController;

	protected TestOrdersRepository testRepo = new TestOrdersRepository();

	
	@Test
	public void createOrder() {
		List<Orders> orders = ordersController.createOrder(theOrder);
		Assert.assertNotNull( orders );
		Assert.assertEquals( ORDER_TYPE_1, orders.get( 0 ).getType() );
	}
	
	
	@Test
	public void validOrderType() {
		List<Orders> orders = ordersController.byType( theOrder.getType() );
		Assert.assertNotNull( orders );
		Assert.assertEquals( ORDER_TYPE_1, orders.get( 0 ).getType() );
	}

	@Test
	public void validOrderQuantity() {
		List<Orders> order = ordersController.byQuantity( theOrder.getQuantity() );
		Assert.assertNotNull( order );
		Assert.assertEquals( ORDER_QUANTITY_1, order.get( 0 ).getQuantity() );
	}

	@Test
	public void validOrderPrice() {
		List<Orders> order = ordersController.byPrice( theOrder.getPrice().toString() );
		Assert.assertNotNull( order );
		Assert.assertEquals( ORDER_PRICE_1, order.get( 0 ).getPrice() );
	}

	@Before
	public void setup() {
		ordersController = new OrdersController( testRepo );
	}

	protected static class TestOrdersRepository implements OrdersRepository {

		@Override
		public void deleteAllInBatch() {
			// TODO Auto-generated method stub

		}

		@Override
		public void deleteInBatch( Iterable<Orders> p_arg0 ) {
			// TODO Auto-generated method stub

		}

		@Override
		public List<Orders> findAll() {
			List<Orders> ordersList = new ArrayList<Orders>();
			ordersList.add( theOrder );
			return ordersList;
		}

		@Override
		public List<Orders> findAll( Sort p_arg0 ) {
			List<Orders> ordersList = new ArrayList<Orders>();
			ordersList.add( theOrder );
			return ordersList;
		}

		@Override
		public List<Orders> findAll( Iterable<Long> p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub

		}

		@Override
		public Orders getOne( Long p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Orders> List<S> save( Iterable<S> p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Orders> S saveAndFlush( S p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<Orders> findAll( Pageable p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void delete( Long p_arg0 ) {
			// TODO Auto-generated method stub

		}

		@Override
		public void delete( Orders p_arg0 ) {
			// TODO Auto-generated method stub

		}

		@Override
		public void delete( Iterable<? extends Orders> p_arg0 ) {
			// TODO Auto-generated method stub

		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean exists( Long p_arg0 ) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Orders findOne( Long p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Orders> S save( S p_arg0 ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int countOrders() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<Orders> findByQuantity( Integer p_orderQuantity ) {
			if ( p_orderQuantity.equals( ORDER_QUANTITY_1 ) ) {
				List<Orders> ordersList = new ArrayList<Orders>();
				ordersList.add( theOrder );
				return ordersList;
			}
			else {
				return null;
			}
		}

		@Override
		public List<Orders> findByPrice( BigDecimal p_priceVal ) {
			if ( p_priceVal.equals( ORDER_PRICE_1 ) ) {
				List<Orders> ordersList = new ArrayList<Orders>();
				ordersList.add( theOrder );
				return ordersList;
			}
			else {
				return null;
			}
		}

		@Override
		public List<Orders> findByType( String p_orderType ) {
			if ( p_orderType.equals( ORDER_TYPE_1 ) ) {
				List<Orders> ordersList = new ArrayList<Orders>();
				ordersList.add( theOrder );
				return ordersList;
			}
			else {
				return null;
			}
		}

	}

}
