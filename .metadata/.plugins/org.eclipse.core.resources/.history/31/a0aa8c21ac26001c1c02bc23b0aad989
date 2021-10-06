package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderProdBean;

public interface ParticularProdRepo extends JpaRepository<ParticularOrderProdBean, Integer> {
	
	List<ParticularOrderProdBean> findByOrdersBean(OrdersBean order);

	

}
