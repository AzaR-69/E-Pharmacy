package com.pharmacy.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.dao.UserDAO;
import com.pharmacy.model.UserBean;
import com.pharmacy.payload.LoginBean;

@RestController
public class UserController {

	@Autowired
	private UserDAO userService;

	@PostMapping("/authenticate")
	public ModelAndView authenticateUser(@ModelAttribute("user") LoginBean user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		if (userService.authenticate(user.getUsername(), user.getPassword())) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", userService.getRole(user.getUsername()));
			return new ModelAndView("dashboard");
		} else {
			return new ModelAndView("error");
		}

	}

	@GetMapping("/deleteusers")
	public ModelAndView deleteUser(@RequestParam("userId") int id, Model model) {
		userService.deleteUserByUsername(id);
		model.addAttribute("message", "Deleted Successfully");
		return new ModelAndView("dashboard");
	}

	@PostMapping("/register")
	public ModelAndView addUser(@ModelAttribute("user") UserBean user, Model model) {
		userService.addUser(user);
		model.addAttribute("user", new UserBean());
		model.addAttribute("msg", "Registered Successfully");
		return new ModelAndView("signup");
	}

	@PostMapping("/updateprofile")
	public ModelAndView update(@ModelAttribute("user") UserBean user, Model model, HttpServletRequest request) {
		String role = (String) request.getSession().getAttribute("role");
		String result = userService.updateUser(user, user.getId());
		if (result.equals("SUCCESS")) {
			model.addAttribute("message", "SUCCESS");
			return new ModelAndView("dashboard");
		} else {
			return new ModelAndView("error");
		}

	}

	@GetMapping("/deleteuser")
	public ModelAndView delete(@RequestParam int userId, Model model) {
		String result = userService.deleteUserByUsername(userId);
		if (result.equals("SUCCESS")) {
			model.addAttribute("message", "SUCCESS");
			return new ModelAndView("dashboard");
		} else {
			return new ModelAndView("error");
		}
	}

}
