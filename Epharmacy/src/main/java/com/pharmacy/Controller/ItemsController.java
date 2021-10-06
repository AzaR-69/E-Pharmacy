package com.pharmacy.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.service.DistributorItemService;
import com.pharmacy.service.ItemsService;

@RestController
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private DistributorItemService distributorService;
	
	public ItemsController(ItemsService itemsService,DistributorItemService distributorService) {
		super();
		this.itemsService = itemsService;
		this.distributorService = distributorService;
	}
	
	@PostMapping("/add")
	public ItemsBean addItems(@RequestBody ItemsBean itemsBean)
	{
		List<DistributorItemBean> lists = new ArrayList<DistributorItemBean>();
		
		DistributorItemBean disBean1 = new DistributorItemBean();
		disBean1.setItemName("para10");
		disBean1.setPrice(2000);
		disBean1.setQuantity(2);
		disBean1.setDescription("Solves Fever");
		DistributorItemBean disBean2 = new DistributorItemBean();
		disBean2.setItemName("para5");
		disBean2.setPrice(20000);
		disBean2.setQuantity(5);
		disBean2.setDescription("Solves Fever and Cough");
		DistributorItemBean disBean3 = new DistributorItemBean();
		disBean3.setItemName("para6");
		disBean3.setPrice(20);
		disBean3.setQuantity(200);
		disBean3.setDescription("Solves Fever and Headache");
		lists.add(disBean1);
		lists.add(disBean2);
		lists.add(disBean3);
		
		return this.itemsService.addItem(itemsBean, lists);
	}
	@GetMapping("/check/{dis}/{cat}")
	public void getItem(@PathVariable("dis") String dis , @PathVariable("cat") String cat)
	{
			ItemsBean items = new ItemsBean();
			System.out.println("all items "+this.distributorService.getCategoryItems(null));
			
//			ItemsBean item = new ItemsBean();
//			item.setDistributor(dis);
//			item.setCategory(cat);
//			System.out.println(this.itemsService.checkItem(item));
	}
}
