package com.pharmacy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.model.OrdersBean;

public interface OrdersRepo extends JpaRepository<OrdersBean, Integer>{

	public OrdersBean findById(int order_id);
	public OrdersBean findByUsernameAndOrderDateAndTotalQuantity(String username, String orderDate, int i);
	public List<OrdersBean> findByUsername(String username);
	public List<OrdersBean> findByDistributorName(String dis_name);
	@Query(value="select * from orders where order_date =:date", nativeQuery=true)
	public List<OrdersBean> findByOrderDate(@Param("date") String date);
}
