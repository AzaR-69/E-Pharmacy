package com.pharmacy.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;

public interface DistributorItemsRepo extends JpaRepository<DistributorItemBean, Integer> {

	int deleteByIdAndItemBean(int id, Optional<ItemsBean> itemBean);
	
	List<DistributorItemBean> findByItemBean(Optional<ItemsBean> itemBean);
	
	
}
