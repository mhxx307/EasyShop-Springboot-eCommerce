package com.easyshop.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easyshop.admin.user.UserRepository;
import com.easyshop.common.entity.User;

@Service
public class EasyshopUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Email: " + email);
		User user = userRepository.findUserByEmail(email);
		System.out.println(user);
		if (user != null) {
			return new EasyshopUserDetails(user);
		} else {
			throw new UsernameNotFoundException("Could not find user with email: " + email);
		}
	}

}
