package com.easyshop.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyshop.common.entity.Role;
import com.easyshop.common.entity.User;

/**
 * 
 * @author La Vo Minh Quan
 * 
 * [[@]]: duong dan , path
 * [[$]]: bien, variable
 *
 */

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String listAll(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
	
	@GetMapping("/new")
	public String newUser(Model model) {
		User user = new User();
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		userService.save(user);
		
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
		return "redirect:/users";
	}
}