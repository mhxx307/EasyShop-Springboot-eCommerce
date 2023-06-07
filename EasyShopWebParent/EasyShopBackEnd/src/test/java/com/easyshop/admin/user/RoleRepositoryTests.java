package com.easyshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.easyshop.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false) // do not rollback transaction when execute the test
public class RoleRepositoryTests {
	@Autowired
	RoleRepository roleRepository;

	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role();
		roleAdmin.setName("Admin");
		roleAdmin.setDescription("manage everything");
		Role savedRole = roleRepository.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role();
		roleSalesperson.setName("Salesperson");
		roleSalesperson.setDescription("manage product price, customers, shipping, orders and sales report");
		
		Role roleEditor = new Role();
		roleEditor.setName("Editor");
		roleEditor.setDescription("manage categories, brands, products, articles and menus");
		
		Role roleShipper = new Role();
		roleShipper.setName("Shipper");
		roleShipper.setDescription("view products, view orders and update order status");
		
		Role roleAssistant = new Role();
		roleAssistant.setName("Assistant");
		roleAssistant.setDescription("manage questions and reviews");
		
		roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}
}
