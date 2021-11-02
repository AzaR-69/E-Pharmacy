package com.pharmacy.model;

import java.util.Arrays;

public class OrdersBean {

	private int orderId;
	private String username;
	private String distributorName;
	private String address;
	private float price;
	private String phoneNumber;
	private int totalQuantity;
	private String orderDate;
	private String status;
	private byte[] prescription;
	private boolean medicine;
	private String message;
	
	public OrdersBean() {
		super();
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public byte[] getPrescription() {
		return prescription;
	}
	public boolean isMedicine() {
		return medicine;
	}
	public String getMessage() {
		return message;
	}
	public void setPrescription(byte[] prescription) {
		this.prescription = prescription;
	}
	public void setMedicine(boolean medicine) {
		this.medicine = medicine;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "OrdersBean [orderId=" + orderId + ", username=" + username + ", distributorName=" + distributorName
				+ ", address=" + address + ", price=" + price + ", phoneNumber=" + phoneNumber + ", totalQuantity="
				+ totalQuantity + ", orderDate=" + orderDate + ", status=" + status + ", prescription="
				+ Arrays.toString(prescription) + ", medicine=" + medicine + ", message=" + message + "]";
	}
	
	
	

}
