package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pharmacy.model.*;

@Component("particularProductDao")
public class ParticularProductDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	public int insertItem(ParticularOrderProductBean prodOrder) {
		sql="INSERT INTO particular_order_prod (order_id,item_name,price,quantity) VALUES (?,?,?,?)";
		try {
			row = this.jdbcTemplate.update(sql, prodOrder.getOrderId(), prodOrder.getItemName(), 
					prodOrder.getPrice(), prodOrder.getQuantity());
			return row;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteItem(int orderID) {
		sql="DELETE FROM particular_order_prod WHERE order_id=?";
		try {
			row = this.jdbcTemplate.update(sql, orderID); 
		}
		catch(Exception e) {
			row=0;
		}
		return row;
	}
	
	
	public String updateProductByIdAndOrderID(ParticularOrderProductBean prod) {
		sql="UPDATE particular_order_prod set item_name=?,price=?,quantity=? WHERE id=? and order_id=?";
		try {
			row = this.jdbcTemplate.update(sql, prod.getItemName(), prod.getPrice(), prod.getQuantity(),
					prod.getId(), prod.getOrderId());
			result = (row > 0) ? "SUCCESS" : "FAIL";
			
		}
		catch(Exception e) {
			result= "FAIL";
		}
		return result;
	}
	public List<ParticularOrderProductBean> getAllItemsOfOrder(int orderID){
		sql="SELECT * FROM particular_order_prod WHERE order_id=?";
		List<ParticularOrderProductBean> prods=new ArrayList<>();
		try {
			prods = this.jdbcTemplate.query(sql, new ParticularProductRowMapper(), orderID);
			return prods;
		}
		catch(Exception e) {
			return null;
		}	
	}
	public List<ParticularOrderProductBean> getProds(){
		sql="SELECT * FROM particular_order_prod";
		List<ParticularOrderProductBean> prods=new ArrayList<>();
		try {
			prods = this.jdbcTemplate.query(sql, new ParticularProductRowMapper());
			return prods;
		}
		catch(Exception e) {
			return null;
		}	
	}
	
}
