package com.pharmacy.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderProdBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

@Service
public class ParticularOrderProductService implements Serializable{

	@Autowired
	private ParticularProdRepo productRepo;
	
	@Autowired
	private OrdersRepo ordersRepo;
	
	public ParticularOrderProductService(ParticularProdRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	public ParticularOrderProdBean addPartOrder(ParticularOrderProdBean prod)
	{
		return productRepo.save(prod);
	}
	public void deleteInTable(int id)
	{
		this.productRepo.deleteById(id);
	}
	public ParticularOrderProdBean updateByIdAndOrderId(ParticularOrderProdBean prod)
	{
		return this.productRepo.save(prod);
	}
	public List<ParticularOrderProdBean> getPartByOrderId(int order_id)
	{
		OrdersBean ord = this.ordersRepo.findById(order_id);
		return ( this.productRepo).findByOrdersBean(ord);
	}
	public List<ParticularOrderProdBean> getAllProds()
	{
		return this.productRepo.findAll();
	}
	
}
