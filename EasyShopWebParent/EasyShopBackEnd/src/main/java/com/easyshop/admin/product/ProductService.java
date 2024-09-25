package com.easyshop.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.easyshop.common.entity.Product;

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> listAll() {
		return productRepository.findAll();
	}
}