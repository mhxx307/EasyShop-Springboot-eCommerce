package com.easyshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.easyshop.common.entity.Role;
import com.easyshop.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userQuan = new User();
		userQuan.setEmail("minhquan.lavo@gmail.com");
		userQuan.setFirstName("Quan");
		userQuan.setLastName("La");
		userQuan.setPassword("hellohacker");
		
		userQuan.addRole(roleAdmin);
		
		User savedUser = userRepository.save(userQuan);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userRavi = new User();
		userRavi.setEmail("ravi@gmail.com");
		userRavi.setPassword("ravi2023");
		userRavi.setFirstName("Ravi");
		userRavi.setLastName("Kumar");
		userRavi.setEnabled(true);
		
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = userRepository.save(userRavi);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = userRepository.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userById = userRepository.findById(1).get();
		System.out.println(userById);
		assertThat(userById).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userById = userRepository.findById(1).get();
		userById.setEnabled(true);
		userById.setEmail("lavominhquan@gmail.com");
		
		userRepository.save(userById);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userRavi = userRepository.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesperson);
		System.out.println("RAVI ROLES: " + userRavi.getRoles());
		
		userRepository.save(userRavi);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		userRepository.deleteById(userId);
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "ammanollashirisha2020@gmail.com";

		User user = userRepository.findUserByEmail(email);
		System.out.println("Username: " + user.getFirstName());
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testExistsUserById() {
		Integer id = 1;
		boolean existedUser = userRepository.existsUserById(id);
		
		assertThat(existedUser).isNotNull().isEqualTo(true);
	}
	
	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById = userRepository.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Integer id = 1;
		userRepository.updateEnabledStatus(id, false);
	}
	
	@Test
	public void testEnableUser() {
		Integer id = 1;
		userRepository.updateEnabledStatus(id, true);
		
	}	
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = userRepository.findAll(pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	@Test
	public void testSearchUsers() {
		String keyword = "bruce";
	
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = userRepository.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));	
		
		assertThat(listUsers.size()).isGreaterThan(0);
	}
}
