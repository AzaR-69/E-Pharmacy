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
import com.pharmacy.util.DBUtil;
@Component("DistributorItemDao")
public class DistributorItemDAO {
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
	
	public String insertItem(DistributorItemBean item) {
		sql="INSERT INTO distributor_item (items_id,item_name,price,description,quantity) VALUES (?,?,?,?,?)";
		try {
			row=this.jdbcTemplate.update(sql,item.getItemsId(),item.getItemName(),item.getPrice(),item.getDescription(),item.getQuantity());
			result = (row > 0) ? "SUCCESS" : "FAIL";
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
		return result;
	}
	
	public int deleteItem(int id,int itemsId) {
		sql="DELETE FROM distributor_item WHERE id=? AND items_id=?";
		try {
			
			row=ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			row=0;
		}
		return row;
	}
	
	public int deleteItem(int id) {
		sql="DELETE FROM distributor_item WHERE id=?";
		try {
			
			row=this.jdbcTemplate.update(sql,id);
		}
		catch(Exception e) {
			e.printStackTrace();
			row=0;
		}
		return row;
	}
	
	public String updateItemById(DistributorItemBean item) {
		sql="UPDATE distributor_item set item_name=?,price=?,description=?,quantity=? WHERE id=?";
		try {
			row= this.jdbcTemplate.update(sql,item.getItemName(),item.getPrice(),item.getDescription(),item.getQuantity(),item.getId());
			result = (row > 0) ? "SUCCESS" : "FAIL";
			
		}
		catch(Exception e) {
			e.printStackTrace();
			result= "FAIL";
		}
		return result;
	}
	public List<DistributorItemBean> getAllItems(int items_id){
		sql="SELECT * FROM distributor_item WHERE items_id=?";
		List<DistributorItemBean> items=new ArrayList<>();
		try {
			items = this.jdbcTemplate.query(sql, new DistributorItemRowMapper(),items_id);
			return items;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public DistributorItemBean getItemById(int id){
		sql="SELECT * FROM distributor_item WHERE id=?";
		
		try {
			DistributorItemBean item = this.jdbcTemplate.queryForObject(sql, new DistributorItemRowMapper(),id);
			return item;
		}
		catch(Exception e) {
			return null;
		}	
	}
	
	public List<DistributorItemBean> getCartProducts(List<DistributorItemBean> items){
		List<DistributorItemBean> products =new ArrayList<DistributorItemBean>();
		if(items.size()>0 && items!=null) {
			for(DistributorItemBean item:items) {
				DistributorItemBean cart=(DistributorItemBean) this.getItemById(item.getId());
				cart.setPrice(item.getQuantity()*cart.getPrice());
				cart.setQuantity(item.getQuantity());
				products.add(cart);
			}
		}
		return products;
	}
	public List<DistributorItemBean> getItems(){
		sql="SELECT * FROM distributor_item";
		List<DistributorItemBean> items=new ArrayList<>();
		try {
			items = this.jdbcTemplate.query(sql, new DistributorItemRowMapper());
			return items;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<DistributorItemBean> getItemsByCategory(String category,String distributor){
		ItemsDAO dao=new ItemsDAO();
		int items_id=dao.getIdByCategory(category, distributor);
		sql="SELECT * FROM distributor_item WHERE items_id=?";
		List<DistributorItemBean> items=new ArrayList<>();
		try {
			items = this.jdbcTemplate.query(sql, new DistributorItemRowMapper(),items_id);
			return items;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getItemIdbyId(int id) {
		sql="SELECT * FROM distributor_item WHERE id=?";
		
		try {
			DistributorItemBean itemsId = this.jdbcTemplate.queryForObject(sql, new DistributorItemRowMapper(),id);
			return itemsId.getItemsId();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}
	public int getQuantityByItemIdAndName(int itemsId,String itemName) {
		sql="SELECT * FROM distributor_item WHERE items_id=? AND item_name=?";
		
		try {
			DistributorItemBean distributorBean = this.jdbcTemplate.queryForObject(sql, new DistributorItemRowMapper(),itemsId,itemName);
			return distributorBean.getQuantity();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updateItemAfterOrder(int itemsId,String itemName,int quantity) {
		int itemQuantity=this.getQuantityByItemIdAndName(itemsId, itemName);
		quantity=itemQuantity-quantity;
		sql="UPDATE distributor_item SET quantity=? WHERE items_id=? AND item_name=?";
		try {
			row= this.jdbcTemplate.update(sql,quantity,itemsId,itemName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
