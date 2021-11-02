package com.pharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.dao.DistributorItemDAO;
import com.pharmacy.dao.ItemsDAO;
import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.payload.ItemPayload;

@RestController
public class ItemsController {

	@Autowired
	private ItemsDAO itemsService;

	@Autowired
	private DistributorItemDAO distributorService;

	@PostMapping("/additem")
	public ModelAndView addItem(@ModelAttribute("item") ItemPayload item, @RequestParam("username") String distributor,
			Model model) {
		ItemsBean itemBean = new ItemsBean();
		if (item.getDistributor() == null) {
			itemBean.setDistributor(distributor);
		} else {
			itemBean.setDistributor(item.getDistributor());
		}
		DistributorItemBean distributorItem = new DistributorItemBean();
		distributorItem.setDescription(item.getDescription());
		distributorItem.setItemName(item.getItemName());
		distributorItem.setPrice(item.getPrice());
		distributorItem.setQuantity(item.getQuantity());
		itemBean.setCategory(item.getCategory());
		String result = itemsService.addItem(distributorItem, itemBean);
		if (result.equals("SUCCESS")) {
			model.addAttribute("item", new ItemPayload());
			model.addAttribute("message", "SUCCESS");
			return new ModelAndView("items");
		} else {
			return new ModelAndView("error");
		}
	}

	@GetMapping("/getitems")
	public ModelAndView getItems(@RequestParam("username") String distributor, Model model,
			HttpServletRequest request) {
		String role = (String) request.getSession().getAttribute("role");
		List<DistributorItemBean> distributorItem = new ArrayList<>();
		if (role.contains("ADMIN")) {
			distributorItem = distributorService.getItems();
		} else {
			distributorItem = itemsService.getAllDistributorItems(distributor);
		}
		model.addAttribute("items", distributorItem);
		return new ModelAndView("itemstable");
	}

	@GetMapping("/getdistributoritems")
	public ModelAndView getProducts(@RequestParam("category") String category,
			@RequestParam("distributor") String distributor, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (category != null && distributor != null) {
			session.setAttribute("category", category);
			session.setAttribute("distributor", distributor);
		} else {
			category = (String) session.getAttribute("category");
			distributor = (String) session.getAttribute("distributor");
		}
		List<DistributorItemBean> items = itemsService.getAllDistributorItems(distributor);
		if (items != null && !items.isEmpty()) {
			session.setAttribute("distributoritems", items);
		} else {
			session.setAttribute("message", "FAILED");
		}
		return new ModelAndView("itemslist");
	}

	@GetMapping("/edititem")
	public ModelAndView editItem(@RequestParam("id") int id, Model model) {
		DistributorItemBean distributorItem = distributorService.getItemById(id);
		model.addAttribute("item", distributorItem);
		return new ModelAndView("edititem");
	}

	@PostMapping("/edititem")
	public ModelAndView editItem(@RequestParam("itemId") int itemId,
			@ModelAttribute("item") DistributorItemBean distributorItem, Model model) {
		distributorItem.setItemsId(itemId);
		String result=distributorService.updateItemById(distributorItem);
		if(result.equals("SUCCESS")) {
		model.addAttribute("item", new ItemPayload());
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("items");}
		else {
			return new ModelAndView("error");
		}
	}

	@GetMapping("deleteitem/{id}")
	public ModelAndView deleteItem(@PathVariable int id, Model model) {
		int row=distributorService.deleteItem(id);
		if(row>0) {
		model.addAttribute("message", "SUCCESS");
		model.addAttribute("item", new ItemPayload());
		return new ModelAndView("items");}
		else {
			return new ModelAndView("error");
		}

	}

}
