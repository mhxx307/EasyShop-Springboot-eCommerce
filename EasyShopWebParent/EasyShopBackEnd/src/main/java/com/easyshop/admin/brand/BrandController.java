package com.easyshop.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easyshop.admin.category.CategoryService;
import com.easyshop.common.entity.Brand;
import com.easyshop.common.entity.Category;

@Controller
@RequestMapping("/brands")
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@GetMapping
	public String listAll(Model model) {
		List<Brand> brands = brandService.listAll();
		model.addAttribute("listBrands", brands);
		
		return "brands/brands";
	}
	
	@GetMapping("/new")
	public String newBrand(Model model) {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("brand", new Brand());
		model.addAttribute("pageTitle", "Create new Brand");	
		
		return "brands/brands_form";
	}
}
