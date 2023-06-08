package com.easyshop.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/* why using rest controller ? Because checkDuplicatedEmail function using an email param (already @param) as variable and this variable
*  is incomming httpRequest, @RestController has @RequestBody in detail and this annotation helping convert httpReuqest(json) to java object
*/
@RestController
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("email") String email) {
		return userService.isEmailUnique(email) ? "Ok" : "Duplicated";
	}
}
