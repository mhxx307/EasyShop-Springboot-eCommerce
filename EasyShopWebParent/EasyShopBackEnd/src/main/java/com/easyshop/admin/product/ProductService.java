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

	/**
	 * Check if the product name is unique
	 * @param id
	 * @param name
	 * @return "OK" if the product name is unique, "Duplicate" if the product name is not unique
	 */
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew = (id == null || id == 0);
		Product productByName = productRepository.findByName(name);

		if (isCreatingNew) {
			if (productByName != null) return "Duplicate";
		} else {
			if (productByName != null && productByName.getId() != id) {
				return "Duplicate";
			}
		}

		return "OK";
	}

	public void updateProductEnabledStatus(Integer id, boolean enabled) {
		productRepository.updateEnabledStatus(id, enabled);
	}
}