package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.pharmacy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component("userDao")
public class UserDAO {
	
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

	public String addUser(UserBean user) {
		sql = "INSERT INTO users (username,email,phoneNumber,password,role,name) VALUES (?,?,?,?,?,?)";
		try {
			row = this.jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPhoneNumber(),
					user.getPassword(), user.getRole(), user.getName());
			result = (row > 0) ? "SUCCESS" : "FAILED";
		} catch (Exception e) {
			result = "FAILED";
		}
		return result;

	}

	public String updateUser(UserBean user, int id) {
		sql = "UPDATE users SET username=?,email=?,phoneNumber=?,password=?,role=? WHERE id=?";
		try {
			row = this.jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPhoneNumber(),
					user.getPassword(), user.getRole(), id);
			result = (row > 0) ? "SUCCESS" : "FAILED";

		} catch (Exception e) {
			e.printStackTrace();
			result = "FAILED";
		}
		return result;

	}

	public UserBean getUserById(int id) {
		sql = "SELECT * FROM users WHERE id=?";
		UserBean user = new UserBean();
		try {	
			RowMapper<UserBean> rowMapper = new UserRowMapper();
			user = this.jdbcTemplate.queryForObject(sql, rowMapper, id);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public String updateUser(UserBean user, String oldUsername, String oldPassword) {
		sql = "UPDATE users SET username=?,email=?,phoneNumber=?,password=?,name=? WHERE username=? AND password=?";
		try {
			row = this.jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPhoneNumber(),
					user.getPassword(), user.getName(), oldUsername, oldPassword);
			result = (row > 0) ? "SUCCESS" : "FAILED";

		} catch (Exception e) {
			result = "FAILED";
		}
		return result;

	}

	public String deleteUserByUsername(String username, String password) {
		sql = "DELETE FROM users WHERE username=? AND password=?";
		try {
			row = this.jdbcTemplate.update(sql, username, password);
			result = (row > 0) ? "SUCCESS" : "FAILED";
		} catch (Exception e) {
			result = "FAILED";
		}
		return result;
	}

	public String deleteUserByUsername(int id) {
		sql = "DELETE FROM users WHERE id=?";
		try {
			
			row = this.jdbcTemplate.update(sql, id);
			result = (row > 0) ? "SUCCESS" : "FAILED";
		} catch (Exception e) {
			result = "FAILED";
		}
		return result;
	}

	public List<UserBean> getAllUsers() {
		List<UserBean> users = new ArrayList<>();
		sql = "SELECT * FROM users";
		try {
			users = this.jdbcTemplate.query(sql, new UserRowMapper());
			if (users.isEmpty()) {
				return null;
			} else {
				return users;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public UserBean getUserByUsername(String username) {
		sql = "SELECT * FROM users WHERE username=?";
		UserBean user = new UserBean();
		try {
			RowMapper<UserBean> rowMapper = new UserRowMapper();
			user = this.jdbcTemplate.queryForObject(sql, rowMapper, username);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public String getRole(String username) {
		sql = "SELECT * FROM users WHERE username=?";
		String role = "";
		try {
			RowMapper<UserBean> rowMapper = new UserRowMapper();
			UserBean user = this.jdbcTemplate.queryForObject(sql, rowMapper, username);
			
			if(user != null)
				role = user.getRole();
		} catch (Exception e) {
			role = null;
		}
		return role;
	}

	public boolean authenticate(String username, String password) {
		sql = "SELECT * FROM users WHERE username=? AND password=?";
		try {
			RowMapper<UserBean> rowMapper = new UserRowMapper();
			UserBean user = this.jdbcTemplate.queryForObject(sql, rowMapper, username, password);
			if(user == null)
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
