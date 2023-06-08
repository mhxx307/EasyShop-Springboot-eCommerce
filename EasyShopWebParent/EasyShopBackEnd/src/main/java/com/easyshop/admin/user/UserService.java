package com.easyshop.admin.user;

import java.util.List;

import com.easyshop.common.entity.Role;
import com.easyshop.common.entity.User;

public interface UserService {
	List<User> listAll();
	List<Role> listRoles();
	void save(User user);
	boolean isEmailUnique(Integer userId, String email);
	User findUserById(Integer id) throws UserNotFoundException;
	void deleteById(Integer id) throws UserNotFoundException;
	void updateUserEnabledStatus(Integer id, boolean status);
}
