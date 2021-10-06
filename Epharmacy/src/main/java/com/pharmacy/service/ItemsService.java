package com.pharmacy.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.repo.ItemsRepo;

@Service
public class ItemsService implements Serializable {
	
	@Autowired
	private ItemsRepo itemsRepo;

	@Autowired
	private DistributorItemService disItemService;
	
	public ItemsService(ItemsRepo itemsRepo) {
		super();
		this.itemsRepo = itemsRepo;
	}
	
	public ItemsBean addItem(ItemsBean itemBean, List<DistributorItemBean> disItems)
	{
		ItemsBean resItemBean = null;
		if(!checkItem(itemBean))
			resItemBean = this.itemsRepo.save(itemBean);
		else {
			String distributor = itemBean.getDistributor();
			String category = itemBean.getCategory();
			resItemBean = this.itemsRepo.findByDistributorAndCategory(distributor, category);
		}
			
		for(DistributorItemBean disItem:disItems)
		{
			disItem.setItemBean(resItemBean);
			this.disItemService.addDisItem(disItem);
		}
		return resItemBean;
	}
	
	public boolean checkItem(ItemsBean itemBean)
	{
		String distributor = itemBean.getDistributor();
		String category = itemBean.getCategory();
		ItemsBean item = this.itemsRepo.findByDistributorAndCategory(distributor, category);
		if(item == null)	
			return false;
		else
			return true;	
	}
	
}
