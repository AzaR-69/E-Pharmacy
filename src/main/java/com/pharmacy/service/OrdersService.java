package com.pharmacy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

@Service
public class OrdersService {

	@Autowired
	private ParticularProdRepo productRepo;
	
	@Autowired
	private OrdersRepo ordersRepo;
    
	@Autowired
	private ItemsService items;
	public OrdersService(ParticularProdRepo productRepo, OrdersRepo ordersRepo) {
		super();
		this.productRepo = productRepo;
		this.ordersRepo = ordersRepo;
	}
	
	public int getOrderId(OrdersBean order)
	{
		OrdersBean ord = this.ordersRepo.findByUsernameAndOrderDateAndTotalQuantity(order.getUsername(), order.getOrderDate(), order.getTotalQuantity());
		
		return ord.getOrderId();
	}
	public String addOrder(OrdersBean order, List<ParticularOrderBean> prodBean)
	{
		OrdersBean orderBean = this.ordersRepo.save(order);
		for(ParticularOrderBean prod: prodBean)
		{
			prod.setOrderBean(orderBean);
//			this.productRepo.save(prod);
			System.out.println("saving "+prod);
			this.productRepo.save(prod);
		}
		if(orderBean != null)
			return "SUCCESS";
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
	public OrdersBean updateOrderStatus(int order_id, String status,String message)
	{
			OrdersBean ord = this.ordersRepo.findById(order_id);
			ord.setStatus(status);
			ord.setMessage(message);
			return this.ordersRepo.save(ord);
			
	}
	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) 
	{
		if(role.equals("USER"))
		{
			 List<OrdersBean> list = this.ordersRepo.findByUsername(name);
			 return list;
			
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
	
	public void updateOrder(OrdersBean newOrder) {
		OrdersBean order=ordersRepo.findById(newOrder.getOrderId());
		order.setStatus(newOrder.getStatus());
		order.setMessage(newOrder.getMessage());
		order.setDistributorName(newOrder.getDistributorName());
		order.setTotalQuantity(newOrder.getTotalQuantity());
		order.setPrice(newOrder.getPrice());
		ordersRepo.save(order);
	}
}
