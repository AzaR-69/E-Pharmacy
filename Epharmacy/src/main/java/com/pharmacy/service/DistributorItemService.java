package com.pharmacy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.repo.DistributorItemsRepo;
import com.pharmacy.repo.ItemsRepo;

@Service
public class DistributorItemService implements Serializable{

	@Autowired
	private DistributorItemsRepo distributorItemRepo;

	@Autowired
	private ItemsRepo itemsRepo;
	public DistributorItemService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DistributorItemService(DistributorItemsRepo distributorItemRepo) {
		super();
		this.distributorItemRepo = distributorItemRepo;
	}
	
	public DistributorItemBean addDisItem(DistributorItemBean disItem)
	{
		return this.distributorItemRepo.save(disItem);
	}
	@Transactional
	public int deleteItem(int id , int itemId)
	{
		Optional<ItemsBean> item=  this.itemsRepo.findById(itemId);
		return this.distributorItemRepo.deleteByIdAndItemBean(id, item);
		
	}
	
	public List<DistributorItemBean> getAllItems()
	{
		return this.distributorItemRepo.findAll();
	}
	public Optional<DistributorItemBean> getItemById(int id)
	{
		return this.distributorItemRepo.findById(id);
	}
	public List<DistributorItemBean> getAllItemsByItemID(int item_id)
	{
		Optional<ItemsBean> items = this.itemsRepo.findById(item_id);
		return this.distributorItemRepo.findByItemBean(items);
	}
	public List<DistributorItemBean> getCategoryItems(ItemsBean item)
	{
		Optional<ItemsBean> items = Optional.ofNullable(this.itemsRepo.findByDistributorAndCategory(item.getDistributor(), item.getCategory()));
		System.out.println(" category "+this.distributorItemRepo.findByItemBean(items));
		return null;
	}
	
}
