package com.pharmacy.model;

public class ItemsBean {
	private int id;
	private String distributor;
	private String category;

	public ItemsBean() {
	}

	
	public ItemsBean(int id, String distributor, String category) {
		super();
		this.id = id;
		this.distributor = distributor;
		this.category = category;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "ItemsBean [id=" + id + ", distributor=" + distributor + ", category=" + category + "]";
	}

	

}
