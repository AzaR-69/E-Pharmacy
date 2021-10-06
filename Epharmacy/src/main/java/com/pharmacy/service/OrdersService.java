package com.pharmacy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderProdBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

public class OrdersService {

	@Autowired
	private ParticularProdRepo productRepo;
	
	@Autowired
	
	private OrdersRepo ordersRepo;

	public OrdersService(ParticularProdRepo productRepo, OrdersRepo ordersRepo) {
		super();
		this.productRepo = productRepo;
		this.ordersRepo = ordersRepo;
	}
	
	public int getOrderId(OrdersBean order)
	{
		OrdersBean ord = this.ordersRepo.findByUsenameAndOrderDateAndTotalQuantity(order.getUsername(), order.getOrderDate(), order.getTotalQuantity());
		
		return ord.getOrderId();
	}
	public String addOrder(OrdersBean order, List<ParticularOrderProdBean> prodBean)
	{
		OrdersBean orderBean = this.ordersRepo.save(order);
		for(ParticularOrderProdBean prod: prodBean)
		{
			prod.setOrderBean(orderBean);
			this.productRepo.save(prod);
		}
		if(orderBean != null)
			return "inserted";
		else
			return "not inserted";
	}
	public List<OrdersBean> getAllOrders()
	{
		return this.ordersRepo.findAll();
	}
	public List<OrdersBean> getAllOrdersByUsername(String username)
	{
		return this.ordersRepo.findByUsername(username);
	}
	@Transactional
	public void deleteOrderById(int order_id)
	{
		this.ordersRepo.deleteById(order_id);
		System.out.println("Deleted");
	}
	@Transactional
	public OrdersBean updateOrderStatus(int order_id, String status)
	{
			OrdersBean ord = this.ordersRepo.findById(order_id);
			ord.setStatus(status);
			return this.ordersRepo.save(ord);
			
	}
	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) 
	{
		if(role.equals("USER"))
		{
			return this.ordersRepo.findByUsername(name);
			
		}
		else if(role.equals("DISTRIBUTOR"))
		{
			return this.ordersRepo.findByDistributorName(name);
		}
		else
		{
			return null;
		}
	}
	
	public OrdersBean getOrderByOrdeId(int order_id)
	{
		return this.ordersRepo.findById(order_id);
	}
	
}
