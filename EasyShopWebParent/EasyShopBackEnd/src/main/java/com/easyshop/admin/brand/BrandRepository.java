package com.easyshop.admin.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyshop.common.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
