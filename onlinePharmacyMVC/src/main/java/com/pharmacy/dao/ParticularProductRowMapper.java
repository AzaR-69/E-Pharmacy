package com.pharmacy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pharmacy.model.*;

public class ParticularProductRowMapper implements RowMapper<ParticularOrderProductBean>{

	public ParticularOrderProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ParticularOrderProductBean productBean = new ParticularOrderProductBean();
		productBean.setId(rs.getInt("id"));
		productBean.setOrderId(rs.getInt("order_id"));
		productBean.setItemName(rs.getString("item_name"));
		productBean.setPrice(rs.getFloat("price"));
		productBean.setQuantity(rs.getInt("quantity"));
		return productBean;
	}

}
