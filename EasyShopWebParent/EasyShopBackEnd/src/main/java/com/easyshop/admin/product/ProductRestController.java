package com.easyshop.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
	@Autowired
	private ProductService productService;

    /*
    * Check for duplicate product name
    * What is this flow ? 
    * 1. User enters product name and clicks on the save button
    * 2. The product name is sent to the server
    * 3. The server checks for duplicate product name
    * 4. The server returns a response to the client
    * @param id
    * @param name
    * @return "OK" if the product name is unique, "Duplicate" if the product name is already exists
    */
	@PostMapping("/products/check_unique")
	public String checkUnique(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		return productService.checkUnique(id, name);
	}
}
