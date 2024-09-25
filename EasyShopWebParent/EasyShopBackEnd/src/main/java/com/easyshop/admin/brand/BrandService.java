package com.easyshop.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easyshop.common.entity.Brand;

@Service
@Transactional
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
	
	public Brand get(Integer id) throws BrandNotFoundException {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id) throws BrandNotFoundException {
		Boolean isExists = repo.existsById(id);
		
		if (!isExists) {
			throw new BrandNotFoundException("Could not found any brand with ID " + id);
		}
		
		repo.deleteById(id);
	}
	
	public String checkUnique(Integer id, String name) {
	    boolean isCreatingNew = (id == null || id == 0);
	    Brand brandByName = repo.findByName(name);

	    if (isCreatingNew) {
	        if (brandByName != null) return "Duplicate";
	    } else {
	        if (brandByName != null && brandByName.getId() != id) {
	            return "Duplicate";
	        }
	    }

	    return "OK";
	}
	
	public Page<Brand> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        
        Pageable pageable = PageRequest.of(pageNum - 1, BRANDS_PER_PAGE, sort);
        
        if (keyword != null) {
            return repo.findAll(keyword, pageable);
        }
        
        return repo.findAll(pageable);
    }
}
