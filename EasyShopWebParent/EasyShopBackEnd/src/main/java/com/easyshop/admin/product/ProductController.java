package com.easyshop.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easyshop.admin.brand.BrandService;
import com.easyshop.admin.category.CategoryService;
import com.easyshop.common.entity.Brand;
import com.easyshop.common.entity.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired private BrandService brandService;
	@Autowired private CategoryService categoryService;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.listAll();
        model.addAttribute("products", products);
        return "products/products"; // This will return products.html
    }
    
    @GetMapping("/new")
	public String newProduct(Model model) {
		List<Brand> listBrands = brandService.listAll();
		
		Product product = new Product();
		product.setEnabled(true);
		product.setInStock(true);
		
		model.addAttribute("product", product);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("pageTitle", "Create New Product");
		model.addAttribute("numberOfExistingExtraImages", 0);
		
		return "products/product_form";
	}
    
    @PostMapping("/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
    	System.out.println("Product name: " + product.getName());
    	System.out.println("Brand id: " + product.getBrand().getId());
    	System.out.println("Category id: " + product.getCategory().getId());

		productService.save(product);
		redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
    	return "redirect:/products";
    }
}