package com.test.microservices.orders;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	@Query("SELECT count(*) from Orders")
	public int countOrders();

	public List<Orders> findByQuantity( Integer p_orderQuantity );

	public List<Orders> findByPrice( BigDecimal p_priceVal );

	public List<Orders> findByType( String p_orderType );
}
