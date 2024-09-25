package com.easyshop.admin.brand;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyshop.admin.category.CategoryService;
import com.easyshop.admin.utils.FileUploadUtil;
import com.easyshop.common.entity.Brand;
import com.easyshop.common.entity.Category;

@Controller
@RequestMapping("/brands")
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@Autowired 
	private CategoryService categoryService;
	
//	@GetMapping
//	public String listAll(Model model) {
//		List<Brand> brands = brandService.listAll();
//		model.addAttribute("listBrands", brands);
//		
//		return "brands/brands";
//	}
	
	@GetMapping("/new")
	public String newBrand(Model model) {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("brand", new Brand());
		model.addAttribute("pageTitle", "Create new Brand");	
		
		return "brands/brands_form";
	}
	
	@PostMapping("/save")
	public String saveBrand(Brand brand, @RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {
		if (!multipartFile.isEmpty()) {
			String fileName = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
			brand.setLogo(fileName);
			
			Brand savedBrand = brandService.save(brand);
			String uploadDir = "../brand-logos/" + savedBrand.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} else {
			brandService.save(brand);
		}
		
		ra.addFlashAttribute("message", "The brand has been saved successfully");
		return "redirect:/brands";
	}
	
	@GetMapping("/edit/{id}")
	public String editBrand(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Brand brand = brandService.get(id);
			List<Category> categories = categoryService.listCategoriesUsedInForm();
			
			model.addAttribute("brand", brand);
			model.addAttribute("listCategories", categories);
			model.addAttribute("pageTitle", "Edit brand (ID: " + id + ")");
			
			return "brands/brand_form";
		} catch (Exception e) {
			// TODO: handle exception
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/brands";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBrand(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
		try {
			brandService.delete(id);
			String brandDir = "../brand-logos/" + id;
			FileUploadUtil.removeDir(brandDir);
			
			ra.addFlashAttribute("message", "The brand ID " + id + "has been deleted successfully");
		} catch (Exception e) {
			// TODO: handle exception
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/brands";
	}
	
	@GetMapping
    public String listFirstPage(Model model) {
        return listByPage(1, model, "name", "asc", null);
    }
    
    @GetMapping("/page/{pageNum}")
    public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
            @Param("sortField") String sortField, @Param("sortDir") String sortDir,
            @Param("keyword") String keyword) {
        
        Page<Brand> page = brandService.listByPage(pageNum, sortField, sortDir, keyword);
        List<Brand> listBrands = page.getContent();
        
        long startCount = (pageNum - 1) * BrandService.BRANDS_PER_PAGE + 1;
        long endCount = startCount + BrandService.BRANDS_PER_PAGE - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }
        
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listBrands", listBrands);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("keyword", keyword);
        
        return "brands/brands";
    }
	
}
