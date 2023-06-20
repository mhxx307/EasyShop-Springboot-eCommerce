package com.easyshop.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.easyshop.common.entity.Category;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {
	
//	using mockito to test service layer
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;
	
	@Test
	public void testCheckUniqueInNewModeReturnDuplicatedName() {
		Integer id = null;
		String name = "Computers";
		String alias = "abc";
		
		Category category = new Category(id, name, alias);
		
		Mockito.when(categoryRepository.findByName(name)).thenReturn(category);
		Mockito.when(categoryRepository.findByAlias(alias)).thenReturn(null);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("DuplicateName");
	}
	
	@Test
	public void testCheckUniqueInNewModeReturnDuplicatedNameAlias() {
		Integer id = null;
		String name = "abc";
		String alias = "Computers";
		
		Category category = new Category(id, name, alias);
		
		Mockito.when(categoryRepository.findByName(name)).thenReturn(null);
		Mockito.when(categoryRepository.findByAlias(alias)).thenReturn(category);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("DuplicateAlias");
	}
	
	@Test
	public void testCheckUniqueInNewModeReturnOk() {
		Integer id = null;
		String name = "abc";
		String alias = "abc";
		
		Mockito.when(categoryRepository.findByName(name)).thenReturn(null);
		Mockito.when(categoryRepository.findByAlias(alias)).thenReturn(null);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("Ok");
	}
	
	@Test
	public void testCheckUniqueInEditModeReturnDuplicatedName() {
		Integer id = 1;
		String name = "Computers";
		String alias = "abc";
		
		Category category = new Category(id, name, alias);
		
		Mockito.when(categoryRepository.findByName(name)).thenReturn(category);
		Mockito.when(categoryRepository.findByAlias(alias)).thenReturn(null);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("DuplicateName");
	}
	
	@Test
	public void testCheckUniqueInEditModeReturnDuplicatedAlias() {
		Integer id = 1;
		String name = "abc";
		String alias = "Computers";
		
		Category category = new Category(id, name, alias);
		
		Mockito.when(categoryRepository.findByName(name)).thenReturn(null);
		Mockito.when(categoryRepository.findByAlias(alias)).thenReturn(category);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("DuplicateAlias");
	}
	
	@Test
	public void testCheckUniqueInEditModeReturnOk() {
		Integer id = 1;
		String name = "abc";
		String alias = "abc";
				
		Mockito.when(categoryRepository.findByName(name)).thenReturn(null);
		Mockito.when(categoryRepository.findByAlias(alias)).thenReturn(null);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("Ok");
	}
}
