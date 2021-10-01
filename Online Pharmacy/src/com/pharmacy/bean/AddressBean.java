package com.pharmacy.bean;

public class AddressBean {
	private int id;
	private int userID;
	private String address;

	public AddressBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressBean(int id, int userID, String address) {
		super();
		this.id = id;
		this.userID = userID;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
