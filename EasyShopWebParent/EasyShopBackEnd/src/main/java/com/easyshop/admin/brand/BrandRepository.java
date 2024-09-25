package com.easyshop.admin.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyshop.common.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	Brand findByName(String name);
	
	@Query("SELECT b FROM Brand b WHERE b.name LIKE %?1%")
    public Page<Brand> findAll(String keyword, Pageable pageable);
	
	public Long countById(Integer id);
	
//	NEW Brand(b.id, b.name)
	@Query("SELECT b FROM Brand b ORDER BY b.name ASC")
	public List<Brand> findAll();
	
}
