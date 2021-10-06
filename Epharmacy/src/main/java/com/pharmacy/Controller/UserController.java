package com.pharmacy.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.model.UserBean;
import com.pharmacy.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<UserBean> addUser(@RequestBody UserBean user){
		UserBean newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserBean>> getAllUsers()
	{
		List<UserBean> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/find/{username}")
	public ResponseEntity<Optional<UserBean>> findByUser(@PathVariable("username") String username)
	{
		Optional<UserBean> user = userService.getUser(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/find/role/{username}")
	public ResponseEntity<Object> findByUserRole(@PathVariable("username") String username)
	{
		Object user = userService.getRole(username);
		System.out.println("User role "+user);
		if(user == null)
		{
			return new ResponseEntity<>("Not Found", HttpStatus.OK);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@GetMapping("/find/auth")
	public ResponseEntity<Object> authenticateUser()
	{
		Object user = userService.auth("maha", "maha");
		
		if(user == null)
		{
			return new ResponseEntity<>("Not Found", HttpStatus.OK);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<UserBean> updateUser(@RequestBody UserBean user, @PathVariable("id") int id){
		UserBean UpdateUser = userService.updateUser(user, id);
		return new ResponseEntity<>(UpdateUser, HttpStatus.OK);
	}
	@PutMapping("/update/{username}/{password}")
	public ResponseEntity<UserBean> updateUser(@RequestBody UserBean user, @PathVariable("username") String username,@PathVariable("password") String password){
		
		UserBean UpdateUser = userService.updateUser(user, username,password);
		return new ResponseEntity<>(UpdateUser, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{username}/{password}")
	public String deleteByUsnameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password)
	{
		userService.deleteByUsnameAndPass(username, password);
		
		return "";
	}
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") int id)
	{
		userService.deleteByServiceId(id);
		
		return "";
	}
}
