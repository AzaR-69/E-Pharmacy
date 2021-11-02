package com.pharmacy.dao;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pharmacy.model.*;

@Component("ordersDao")
public class OrdersDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	ParticularProductDAO popb;

	public ParticularProductDAO getPopb() {
		return popb;
	}

	public void setPopb(ParticularProductDAO popb) {
		this.popb = popb;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;

	public OrdersDAO() {

	}

	public int getOrderID(OrdersBean order) {
		sql = "SELECT * from orders where username=? and distributor_name=? and orderDate=?";
		try {
			OrdersRowMapper rowMapper = new OrdersRowMapper();
			OrdersBean orderResult = this.jdbcTemplate.queryForObject(sql, rowMapper, order.getUsername(),
					order.getDistributorName(), order.getOrderDate());
			return orderResult.getOrderId();
		} catch (Exception e) {
			return 0;
		}
	}

	public String addOrder(OrdersBean order, List<ParticularOrderProductBean> products) {
		sql = "INSERT INTO orders (username,orderDate,total_quantity,price,address,distributor_name,status,phone_number,medicine,message) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			int productRow = 0;
			row = this.jdbcTemplate.update(sql, order.getUsername(), order.getOrderDate(), order.getTotalQuantity(),
					order.getPrice(), order.getAddress(), order.getDistributorName(), order.getStatus(),
					order.getPhoneNumber(),order.isMedicine(),order.getMessage());
			if (row > 0) {
				int orderId = this.getOrderID(order);
				for (ParticularOrderProductBean product : products) {
					product.setOrderId(orderId);
					productRow = this.popb.insertItem(product);
				}
			}
			result = (row > 0 && productRow > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}

	public List<OrdersBean> getAllOrders() {
		List<OrdersBean> orders = new ArrayList<>();
		sql = "SELECT * FROM orders";
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper());
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<OrdersBean> getAllOrdersByUsername(String username) {
		List<OrdersBean> orders = new ArrayList<>();
		sql = "SELECT * FROM orders where distributor_name = ?";
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper(), username);
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String deleteOrder(int orderid, boolean medicine) {
		sql = "DELETE FROM orders WHERE order_id=?";
		try {
			if (!medicine) {
				popb.deleteItem(orderid);
			}
			row = this.jdbcTemplate.update(sql, orderid);
			result = (row > 0) ? "SUCCESS" : "FAIL";

		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
	}

	public String updateOrder(OrdersBean newOrder) {
		sql = "UPDATE orders set status=?,message=?,distributor_name=?,total_quantity=?,price=? WHERE order_id=?";
		try {
			row = this.jdbcTemplate.update(sql, newOrder.getStatus(), newOrder.getStatus(),newOrder.getDistributorName(),newOrder.getTotalQuantity(),newOrder.getPrice(),newOrder.getOrderId());
			result = (row > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;

	}

	public String updateOrderStatus(int orderId,String status,String message) {
		sql="UPDATE orders set status=?,message=? WHERE order_id=?";
		try {
			row = this.jdbcTemplate.update(sql, status,message,orderId);
			result = (row > 0) ? "SUCCESS" : "FAIL";
		} catch (Exception e) {
			e.printStackTrace();
			result = "FAIL";
		}
		return result;
		
	}
	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) {
		List<OrdersBean> orders = new ArrayList<>();
		if (role.equals("USER")) {
			sql = "SELECT * FROM orders WHERE username=?";
		} else if (role.equals("DISTRIBUTOR")) {
			sql = "SELECT * FROM orders WHERE distributor_name=?";
		} else if (role.equals("ADMIN")) {
			return this.getAllOrders();
		}
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper(), name);
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int addFile(OrdersBean order) {
		sql="INSERT INTO orders (username,orderDate,address,phone_number,status,prescription,medicine,message) VALUES (?,?,?,?,?,?,?,?)";
		try {
			row=this.jdbcTemplate.update(sql,order.getUsername(),order.getOrderDate(),order.getAddress(),order.getPhoneNumber(),order.getStatus(),order.getPrescription(),order.isMedicine(),order.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			row= 0;
		}
		return row;
	}
	
	public OrdersBean getOrderByOrderID(int orderId) {
		sql = "SELECT * FROM orders WHERE order_id=?";
		OrdersBean order = new OrdersBean();
		try {
			OrdersRowMapper rowMapper = new OrdersRowMapper();
			order = this.jdbcTemplate.queryForObject(sql, rowMapper, orderId);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<OrdersBean> getListByDate(String date) {
		sql = "SELECT * FROM orders WHERE orderDate=?";
		List<OrdersBean> orders = new ArrayList<>();
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper(), date);
			if (orders.isEmpty()) {
				return null;
			} else {
				return orders;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<OrdersBean> findByOrderDateAndDistributorName(String date, String distributor) {
		sql = "SELECT * FROM orders WHERE orderDate=? AND distributor_name=?";
		List<OrdersBean> orders = new ArrayList<>();
		try {
			orders = this.jdbcTemplate.query(sql, new OrdersRowMapper(), date, distributor);
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<OrdersBean> getOrdersByDate(String date, String role, String username) {
		List<OrdersBean> orders = new ArrayList<>();
		if (role.equals("ADMIN")) {
			return this.getListByDate(date);
		} else if (role.equals("DISTRIBUTOR")) {
			orders = this.findByOrderDateAndDistributorName(date, username);
		}
		return orders;
	}
	
	public byte[] getPrescription(int orderId) {
		sql="SELECT prescription FROM orders WHERE order_id=?";
		byte[] prescription = null;
		try {
			Blob image=jdbcTemplate.queryForObject(sql, Blob.class, orderId);
			prescription= image.getBytes(1, (int) image.length());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return prescription;
	}
}
