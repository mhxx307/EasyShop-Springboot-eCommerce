package com.easyshop.admin.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.easyshop.common.entity.Role;
import com.easyshop.common.entity.User;

public interface UserService {
	List<User> listAll();
	List<Role> listRoles();
	User save(User user);
	boolean isEmailUnique(Integer userId, String email);
	User findUserById(Integer id) throws UserNotFoundException;
	void deleteById(Integer id) throws UserNotFoundException;
	void updateUserEnabledStatus(Integer id, boolean status);
	Page<User> listByPage(int pageNum, String sortField, String sortDir);
}
