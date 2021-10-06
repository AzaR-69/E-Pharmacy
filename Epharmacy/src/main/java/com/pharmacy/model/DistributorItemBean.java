package com.pharmacy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DistributorItemBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private int id;
	@Column(nullable=false)
	private String itemName;
	@Column(nullable=false)
	private float price;
	private String description;
	private int quantity;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="Items_id", nullable=false)
	private ItemsBean itemBean;
	public DistributorItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistributorItemBean(String itemName, float price, String description, int quantity, ItemsBean itemBean) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.itemBean = itemBean;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ItemsBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemsBean itemBean) {
		this.itemBean = itemBean;
	}
	@Override
	public String toString() {
		return "DistributorItemBean [id=" + id + ", itemName=" + itemName + ", price=" + price + ", description="
				+ description + ", quantity=" + quantity + ", itemBean=" + itemBean + "]";
	}
	
}
