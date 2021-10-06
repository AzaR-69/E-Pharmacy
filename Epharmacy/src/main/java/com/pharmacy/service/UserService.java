package com.pharmacy.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.UserBean;
import com.pharmacy.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private final UserRepo userRepo;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public UserBean addUser(UserBean user)
	{
		return userRepo.save(user);
	}
	
	public List<UserBean> getUsers()
	{
		return userRepo.findAll();
	}
	
	public Optional<UserBean> getUser(String username)
	{
		return userRepo.findUserBeanByUsername(username);
	}
	public Object getRole(String username)
	{
		return userRepo.getRole(username);
	}
	public Object auth(String username, String password)
	{
		return userRepo.authenticate(username, password);
	}
	public UserBean updateUser(UserBean user, int id)
	{
//		Optional<UserBean> olduser = userRepo.findById(id);
		user.setId(id);
		return userRepo.save(user);
	}
	public UserBean updateUser(UserBean user, String username,String password)
	{
//		Optional<UserBean> olduser = userRepo.findById(id);
		UserBean olduser = (UserBean) userRepo.authenticate(username, password);
		user.setId(olduser.getId());
//		user.setId(id);
		return userRepo.save(user);
	}
	@Transactional
	public String deleteByUsnameAndPass(String username, String password)
	{
		int res = userRepo.deleteByUsernameAndPassword(username, password);
		if(res ==1)
			
			return "Deleted";
		else
			return "notdel";
	}
	@Transactional
	public String deleteByServiceId(int id)
	{
		int res = userRepo.deleteById(id);
		if(res ==1)
			
			return "Deleted";
		else
			return "notdel";
	}
}
