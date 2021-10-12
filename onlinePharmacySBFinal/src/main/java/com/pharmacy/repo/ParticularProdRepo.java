package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;

public interface ParticularProdRepo extends JpaRepository<ParticularOrderBean, Integer> {
	
	List<ParticularOrderBean> findByOrderBean(OrdersBean order);

	

}
