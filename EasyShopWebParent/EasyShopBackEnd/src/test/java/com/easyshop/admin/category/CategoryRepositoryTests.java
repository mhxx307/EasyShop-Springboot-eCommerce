package com.easyshop.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import com.easyshop.common.entity.Category;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE) // run the test again in real database
@Rollback(false)
public class CategoryRepositoryTests {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testCreateRootCategory() {
		Category category = new Category("Electronics");
		Category savedCategory = categoryRepository.save(category);
		System.out.println(savedCategory);
		
		assertThat(savedCategory.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateSubCategory() {
		Category parent = categoryRepository.findById(3).get();
//		Category parent = new Category(1);
		Category subCategory = new Category("Memory", parent);
		Category savedCategory = categoryRepository.save(subCategory);
		
//		Category laptops = new Category("Desktops", parent);
//		Category components = new Category("Computer components", parent);
//		Category cameras = new Category("Cameras", parent);
//		Category smartphones = new Category("Smartphones", parent);
//		categoryRepository.saveAll(List.of(cameras, smartphones));
		
		assertThat(savedCategory.getId()).isGreaterThan(0);	
	}
	
	@Test
	public void testGetCategory() {
		Category category = categoryRepository.findById(1).get();
		System.out.println(category.getName());
		
		Set<Category> children = category.getChildren();
		
		for (Category subCategory : children) {
			System.out.println(subCategory.getName());	
		}
		
		assertThat(children.size()).isGreaterThan(0);
	}
	
	@Test
	public void testPrintHierarchicalCategories() {
		Iterable<Category> categories = categoryRepository.findAll();
		
		for (Category category : categories) {
			if (category.getParent() == null) {
				System.out.println(category.getName());
				
				Set<Category> children = category.getChildren();
				
				for (Category subCategory : children) {
					System.out.println("--" + subCategory.getName());
					printChildren(subCategory, 1);
				}
			}
		}
	}
	
	private void printChildren(Category parent, int subLevel) {
		int newSubLevel = subLevel + 1;
		Set<Category> children = parent.getChildren();
		
		for (Category subCategory : children) {
			for (int i = 0; i < newSubLevel; i++) {				
				System.out.print("--");
			}
			
			System.out.println(subCategory.getName());
			
			printChildren(subCategory, newSubLevel);
		}		
	}
	
	@Test
	public void testListRootCategories() {
		List<Category> rootCategories = categoryRepository.findRootCategories(Sort.by("name").ascending());
		rootCategories.forEach(cat -> System.out.println(cat.getName()));
	}
	
	@Test
	public void testFindByName() {
		String name = "Computers";
		Category category = categoryRepository.findByName(name);
		System.out.println(category);
		
		assertThat(category).isNotNull();
		assertThat(category.getName()).isEqualTo(name);
	}
	
	@Test
	public void testFindByAlias() {
		String alias = "electronics";
		Category category = categoryRepository.findByAlias(alias);
		
		assertThat(category).isNotNull();
		assertThat(category.getAlias()).isEqualTo(alias);
	}
}
