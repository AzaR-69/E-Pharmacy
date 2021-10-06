package com.pharmacy.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharmacy.model.UserBean;

public interface UserRepo extends  JpaRepository<UserBean,Integer>{

	Optional<UserBean> findUserBeanByUsername(String username);
	
	@Query("select s.role from UserBean s where username=?1")
	Object getRole(String username);
	
	@Query("select s from UserBean s where username=?1 and password=?2")
	Object authenticate(String username, String password);
	
	Optional<UserBean> findUserBeanById(int id);
	
	int deleteByUsernameAndPassword(String username, String password);
	
	int deleteById(int id);
}
