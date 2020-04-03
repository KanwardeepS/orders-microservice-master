package com.test.microservices.orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "ORDERS" )
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	public static Integer nextId = 0;
	
	public Orders() {

	}

	public Orders( String p_type, BigDecimal p_price, Integer p_quantity, Timestamp p_ordertime ) {
		super();
		id = getNextId();
		type = p_type;
		price = p_price;
		quantity = p_quantity;
		ordertime = p_ordertime;
	}

	public Orders(String p_orderType1, BigDecimal p_orderPrice1, Integer p_orderQuantity1) {
		super();
		id = getNextId();
		type = p_orderType1;
		price = p_orderPrice1;
		quantity = p_orderQuantity1;
	}

	@Id
	protected Integer id;

	@Column( name = "type" ) // category
	protected String type;

	@Column( name = "price" )
	protected BigDecimal price;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity( Integer p_quantity ) {
		quantity = p_quantity;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime( Timestamp p_ordertime ) {
		ordertime = p_ordertime;
	}

	@Column( name = "quantity" )
	protected Integer quantity;

	@Column( name = "ordertime" )
	protected Timestamp ordertime;

	protected static Integer getNextId() {
		synchronized ( nextId ) {
			return nextId++;
		}
	}

	public Integer getId() {
		return id;
	}

	protected void setId( Integer id ) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice( BigDecimal price ) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", type=" + type + ", price=" + price + ", quantity=" + quantity + ", ordertime=" + ordertime + "]";
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Orders other = (Orders) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		}
		else if ( !id.equals( other.id ) )
			return false;
		if ( ordertime == null ) {
			if ( other.ordertime != null )
				return false;
		}
		else if ( !ordertime.equals( other.ordertime ) )
			return false;
		if ( price == null ) {
			if ( other.price != null )
				return false;
		}
		else if ( !price.equals( other.price ) )
			return false;
		if ( quantity == null ) {
			if ( other.quantity != null )
				return false;
		}
		else if ( !quantity.equals( other.quantity ) )
			return false;
		if ( type == null ) {
			if ( other.type != null )
				return false;
		}
		else if ( !type.equals( other.type ) )
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( ordertime == null ) ? 0 : ordertime.hashCode() );
		result = prime * result + ( ( price == null ) ? 0 : price.hashCode() );
		result = prime * result + ( ( quantity == null ) ? 0 : quantity.hashCode() );
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
		return result;
	}

}
