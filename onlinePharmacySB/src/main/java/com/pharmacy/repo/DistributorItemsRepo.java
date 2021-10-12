package com.pharmacy.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;

public interface DistributorItemsRepo extends JpaRepository<DistributorItemBean, Integer> {

	int deleteByIdAndItemBean(int id, ItemsBean itemBean);

	List<DistributorItemBean> findByItemBean(ItemsBean itemBean);
	
	DistributorItemBean findById(int id);
	
	DistributorItemBean findDistributorItemBeanByItemBean(ItemsBean itemBean);
	
	@Query("DELETE FROM DistributorItemBean D WHERE D.id=?1")
	@Modifying
	void deleteDistributorItemBeanById(int id);
	
	@Query("SELECT price FROM DistributorItemBean WHERE id=?1")
	float getPriceById(int id);
	
	
	
	DistributorItemBean findByItemBeanAndItemName(ItemsBean item,String itemName);
}
