package com.easyshop.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyshop.common.entity.Brand;

@Service
public class BrandService {
	public static final int BRANDS_PER_PAGE = 10;

	@Autowired
	private BrandRepository repo;

	public List<Brand> listAll() {
		return (List<Brand>) repo.findAll();
	}

	public Brand save(Brand brand) {
		return repo.save(brand);
	}

}
