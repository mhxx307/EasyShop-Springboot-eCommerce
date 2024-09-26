package com.easyshop.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

	/**
	 * Save product to database, if product id is null, then create new product, otherwise update existing product
	 * Alias is a unique identifier for the product, it is generated from product name by replacing spaces with hyphens
	 * Updated time is set to current date and time
	 * @param product
	 * @return saved product
	 */
	public Product save(Product product) {
		if (product.getId() == null) {
			product.setCreatedTime(new Date());
		}
		
		if (product.getAlias() == null || product.getAlias().isEmpty()) {
			String defaultAlias = product.getName().replace(" ", "-");
			product.setAlias(defaultAlias);
		} else {
			product.setAlias(product.getAlias().replace(" ", "-"));
		}
		
		product.setUpdatedTime(new Date());
		
		return productRepository.save(product);
	}

//	public void delete(Integer id) throws ProductNotFoundException {
//		Long countById = productRepository.countById(id);
//		if (countById == null || countById == 0) {
//			throw new ProductNotFoundException("Could not find any product with ID " + id);
//		}
//		productRepository.deleteById(id);
//	}
//
//	public Product get(Integer id) throws ProductNotFoundException {
//		try {
//			return productRepository.findById(id).get();
//		} catch (NoSuchElementException ex) {
//			throw new ProductNotFoundException("Could not find any product with ID " + id);
//		}
//	}
}