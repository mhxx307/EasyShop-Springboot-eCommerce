package com.easyshop.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyshop.common.entity.Role;
import com.easyshop.common.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> listAll() {
		return userRepository.findAll();
	} 
	
	public List<Role> listRoles() {
		return roleRepository.findAll();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
}
