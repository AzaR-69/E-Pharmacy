package com.pharmacy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="ParticularProductOrder")
public class ParticularOrderProdBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private int id;
	@Column(nullable=false)
	private String itemName;
	@Column(nullable=false)
	private int quantity;
	@Column(nullable=false)
	private float price;
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name="order_id", nullable=false)
	private OrdersBean orderBean;
	public ParticularOrderProdBean(String itemName, int quantity, float price, OrdersBean orderBean) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.orderBean = orderBean;
	}
	public ParticularOrderProdBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public OrdersBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrdersBean orderBean) {
		this.orderBean = orderBean;
	}
	@Override
	public String toString() {
		return "ParticularOrderProdBean [id=" + id + ", itemName=" + itemName + ", quantity=" + quantity + ", price="
				+ price + ", orderBean=" + orderBean + "]";
	}
	
	
}