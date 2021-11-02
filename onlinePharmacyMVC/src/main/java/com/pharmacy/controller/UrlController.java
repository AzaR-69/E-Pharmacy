package com.pharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.dao.DistributorItemDAO;
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.dao.UserDAO;
import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.model.UserBean;
import com.pharmacy.payload.ItemPayload;
import com.pharmacy.payload.LoginBean;

@Controller
public class UrlController {

	@Autowired
	private UserDAO userService;

	@Autowired
	private ItemsDAO itemsService;

	@Autowired
	private DistributorItemDAO distributorService;

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/continueshopping")
	public String getShoppingPage() {
		return "itemslist";
	}

	@GetMapping("/login")
	public ModelAndView getLoginPage(Model model) {
		model.addAttribute("user", new LoginBean());
		return new ModelAndView("login");
	}

	@GetMapping("/producttype")
	public String getProductPage() {
		return "producttype";
	}

	@GetMapping("/adduser")
	public String getAddUserPage(Model model) {
		model.addAttribute("user", new UserBean());
		return "addusers";
	}

	@GetMapping("/register")
	public ModelAndView getRegisterPage(Model model) {
		model.addAttribute("user", new UserBean());
		return new ModelAndView("signup");
	}

	@GetMapping("/yourcart")
	public String getCart(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		ArrayList<DistributorItemBean> clist = (ArrayList<DistributorItemBean>) request.getSession()
				.getAttribute("cartList");
		List<DistributorItemBean> list = clist != null ? distributorService.getCartProducts(clist) : null;
		if (list != null) {
			request.getSession().setAttribute("cartList", list);
		}
		return "cart";
	}

	@GetMapping("/shopping")
	public String getShoppingPage(Model model) {
		List<ItemsBean> items = itemsService.getAllItems();
		model.addAttribute("items", items);
		return "shopping";
	}

	@GetMapping(value = "/dashboard")
	public ModelAndView getDashboard() {
		return new ModelAndView("dashboard");
	}

	@GetMapping("/manageitems")
	public String getItems(Model model) {
		model.addAttribute("item", new ItemPayload());
		return "items";
	}

	@GetMapping("/updateprofile")
	public String getEditProfile(@RequestParam String username, Model model) {
		UserBean user = userService.getUserByUsername(username);
		model.addAttribute("user", user);
		return "editusers";
	}

	@GetMapping("/manageusers")
	public String manageUsers(Model model) {
		List<UserBean> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "manage_users";
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("role");
		session.removeAttribute("username");
		session.invalidate();
		return "redirect:/";
	}
}
