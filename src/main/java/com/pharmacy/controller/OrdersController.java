package com.pharmacy.controller;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.model.UserBean;
import com.pharmacy.service.ItemsService;
import com.pharmacy.service.OrdersService;
import com.pharmacy.service.ParticularOrderProductService;

@Controller
@Transactional
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ParticularOrderProductService particularOrderService;

	@Autowired
	private ItemsService itemService;

	// Display the particular order page
	@GetMapping("/particularOrder/{orderId}")
	public ModelAndView particularOrder(@PathVariable("orderId") int orderId) {
		OrdersBean ordersBean = this.ordersService.getOrderByOrdeId(orderId);
		List<ParticularOrderBean> particularOrderBean = this.particularOrderService.getPartByOrderId(orderId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", particularOrderBean);
		modelAndView.addObject("order", ordersBean);
		modelAndView.setViewName("particularorder");
		return modelAndView;
	}

	@GetMapping("/showOrdersAdmin/{username}/{role}")
	public ModelAndView showOrdersAdmin(@PathVariable("username") String username, @PathVariable String role) {
		ModelAndView modelAndView = new ModelAndView();
		List<OrdersBean> allOrders = ordersService.getAllOrders();
		modelAndView.addObject("orders", allOrders);
		modelAndView.addObject("role", role);
		modelAndView.setViewName("orders");
		return modelAndView;
	}

	@GetMapping("/medicinerequest")
	public ModelAndView showMedicineRequest() {
		List<OrdersBean> orders = ordersService.getAllOrders();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("medicinerequest");
		return modelAndView;
	}

	// Display orders based on role and username
	@GetMapping("/showOrders/{username}/{role}")
	public ModelAndView showOrders(@PathVariable("username") String username, @PathVariable("role") String role,
			Model m) {
		List<OrdersBean> orders = ordersService.getOrdersByNameAndRole(username, role);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("role", role);
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("orders");
		return modelAndView;
	}

	@GetMapping("/DeleteOrder/{id}")
	public ModelAndView delete(@RequestParam int userId,@RequestParam boolean medicine, Model model) {
		particularOrderService.deleteInTable(userId,medicine);
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("dashboard");
	}

	@PostMapping("/UpdateOrder/{id}/{medicine}")
	public ModelAndView updateOrderStatus(@PathVariable("id") int id, @PathVariable("medicine") boolean medicine,
			HttpServletRequest request, Model model) {
		System.out.println("In update order");
		String role = (String) request.getSession().getAttribute("role");
		String status = request.getParameter("status");
		String message = request.getParameter("message");
		String username = (String) request.getSession().getAttribute("username");
		if (medicine) {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			float price = Float.parseFloat(request.getParameter("price"));
			OrdersBean order=new OrdersBean();
			order.setStatus(status);
			order.setMessage(message);
			order.setTotalQuantity(quantity);
			order.setPrice(price);
			order.setDistributorName(username);
			ordersService.updateOrder(order);
			
		}
		else {
			OrdersBean Update = ordersService.updateOrderStatus(id, status,message);
			if (status.equals("ACCEPTED") && role.equals("DISTRIBUTOR")) {
				List<ParticularOrderBean> products = particularOrderService.getPartByOrderId(id);
				itemService.updateDistributorItem(username, products);
			}
			
		}

		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("order-success");
	}

	@PostMapping("/orderplaced")
	public ModelAndView saveOrderDetails(HttpServletRequest request, Model model) {

		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		HttpSession session = request.getSession();
		List<ParticularOrderBean> products = new ArrayList<>();
		List<DistributorItemBean> items = (ArrayList<DistributorItemBean>) session.getAttribute("cartList");
		float totalPrice = 0.0f;
		int itemsId = 0;
		int totalQuantity = 0;
		ItemsBean itemBean = new ItemsBean();
		String username = (String) session.getAttribute("username");
		String date = LocalDate.now().toString();
		String distributor = "";
		System.out.println("address " + address + " phone " + phone);
		for (DistributorItemBean item : items) {
			ParticularOrderBean product = new ParticularOrderBean();
			product.setItemName(item.getItemName());
			int quantity = item.getQuantity();
			float price = item.getPrice();
			product.setPrice(price);
			product.setQuantity(quantity);
			totalQuantity += quantity;
			totalPrice += price;
			itemsId = item.getId();
//			distributor= item.getItemBean().getDistributor();
			itemBean.setId(item.getItemBean().getId());

			products.add(product);
		}
		System.out.println("item id " + itemsId);
		distributor = itemService.getDistributorName(itemBean.getId());
		OrdersBean order = new OrdersBean();
		order.setDistributorName(distributor);
		order.setAddress(address);
		order.setPrice(totalPrice);
		order.setTotalQuantity(totalQuantity);
		order.setPhoneNumber(phone);
		order.setUsername(username);
		order.setMessage("Order placed, Pending Approval");
		order.setOrderDate(date);
		order.setStatus("PENDING");
		String result = ordersService.addOrder(order, products);
		if (result.equals("SUCCESS")) {
			session.removeAttribute("cartList");
			session.removeAttribute("category");
			return new ModelAndView("order-success");
		}
		System.out.println(products);
		return new ModelAndView("order-success");

	}

}
