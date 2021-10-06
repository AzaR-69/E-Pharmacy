package com.pharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.model.ItemsBean;

public interface ItemsRepo extends JpaRepository<ItemsBean, Integer> {

	ItemsBean findByDistributorAndCategory(String distributor, String Category);
	
	
}
