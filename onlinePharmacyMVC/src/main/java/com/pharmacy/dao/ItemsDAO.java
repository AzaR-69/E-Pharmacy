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

@Component("ItemsDao")
public class ItemsDAO {
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String result;
	private int row;
	@Autowired
	DistributorItemDAO itemDAO;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public DistributorItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(DistributorItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ItemsDAO() {

	}

	public String addItem(DistributorItemBean distributorItem, ItemsBean item) {
		int itemsId = this.getItemIdByDistributor(item.getDistributor());
		distributorItem.setItemsId(itemsId);
		if (itemsId == 0) {
			this.insertIntoItems(item);
			distributorItem.setItemsId(this.getItemIdByDistributor(item.getDistributor()));
			result = itemDAO.insertItem(distributorItem);
		} else {

			result = itemDAO.insertItem(distributorItem);
		}
		return result;
	}

	public void insertIntoItems(ItemsBean item) {
		sql = "INSERT INTO items (distributor,category) VALUES (?,?)";
		try {
			row = this.jdbcTemplate.update(sql, item.getDistributor(), item.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getItemIdByDistributor(String distributor) {
		sql = "SELECT * FROM items WHERE distributor=?";
		try {

			ItemsBean item = jdbcTemplate.queryForObject(sql, new ItemsRowMapper(),distributor);
			return item.getId();
		} catch (Exception e) {
			return 0;
		}

	}

	public List<ItemsBean> getItemsByDistributor(String distributor) {
		sql = "SELECT * FROM items WHERE distributor=?";
		List<ItemsBean> items = new ArrayList<>();
		try {
			items = this.jdbcTemplate.query(sql, new ItemsRowMapper(), distributor);
			if (items.isEmpty()) {
				return null;
			} else {
				return items;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<DistributorItemBean> getAllDistributorItems(String distributor) {
		int itemsId = this.getItemIdByDistributor(distributor);
		return itemDAO.getAllItems(itemsId);

	}

	public List<ItemsBean> getAllItems() {
		List<ItemsBean> items = new ArrayList<ItemsBean>();
		sql = "SELECT * FROM items";
		try {
			items = this.jdbcTemplate.query(sql, new ItemsRowMapper());
			if (items.isEmpty()) {
				return null;
			} else {
				return items;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public int getIdByCategory(String category, String distributor) {
		sql = "SELECT id FROM items WHERE distributor=? AND category=?";
		try {
			ItemsBean item = this.jdbcTemplate.queryForObject(sql, new ItemsRowMapper(), category, distributor);
			return item.getId();
		} catch (Exception e) {
			return 0;
		}

	}

	public String getDistributorName(int id) {
		sql = "SELECT * FROM items WHERE id=?";

		try {
			ItemsBean distributor = this.jdbcTemplate.queryForObject(sql, new ItemsRowMapper(), id);
			return distributor.getDistributor();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateDistributorItem(String distributor, List<ParticularOrderProductBean> products) {
		int itemsId = this.getItemIdByDistributor(distributor);
		System.out.println(distributor + ":" + itemsId);
		for (ParticularOrderProductBean product : products) {
			itemDAO.updateItemAfterOrder(itemsId, product.getItemName(), product.getQuantity());
		}
	}

}
