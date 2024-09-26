package com.easyshop.admin.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyshop.common.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Long countById(Integer id);	
	public Product findByName(String name);

	@Query("UPDATE Product p SET p.enabled = :enabled WHERE p.id = :id")	
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
}
