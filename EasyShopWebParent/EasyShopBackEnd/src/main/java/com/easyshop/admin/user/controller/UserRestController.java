package com.easyshop.admin.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyshop.admin.user.UserServiceImpl;

/* why using rest controller ? Because checkDuplicatedEmail function using an email param (already @param) as variable and this variable
*  is incomming httpRequest, @RestController has @RequestBody in detail and this annotation helping convert httpReuqest(json) to java object
*/
@RestController
public class UserRestController {
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
		return userService.isEmailUnique(id, email) ? "Ok" : "Duplicated";
	}
}
