package com.easyshop.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.easyshop.common.entity.Role;
import com.easyshop.common.entity.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> listAll() {
		return userRepository.findAll();
	}

	@Override
	public List<Role> listRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		if (isUpdatingUser) {
			User existingUser = userRepository.findById(user.getId()).get();
			// check the password in user form is empty or not, if is empty meaning the admin want to keep the password
			if (user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			} else {
				encodePassword(user);
			}
		} else {
			encodePassword(user);
		}
		userRepository.save(user);
	}

	@Override
	public boolean isEmailUnique(Integer userId, String email) {
		User userByEmail = userRepository.findUserByEmail(email);
		
		if (userByEmail == null) return true;
		
		boolean isCreatingNew = (userId == null);
		
		if (isCreatingNew) {
			if (userByEmail != null) return false;
		} else {
			if (userByEmail.getId() != userId) {
				return false;
			}
		}

		return true;
	}

	@Override
	public User findUserById(Integer id) throws UserNotFoundException {
		try {
			return userRepository.findById(id).get();
		} catch (Exception e) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
}
