package com.easyshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTests {
	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "raw123456";
		String encodeedPassword = passwordEncoder.encode(rawPassword);
		System.out.println("Encoded password: " + encodeedPassword);
		
		boolean matches = passwordEncoder.matches(rawPassword, encodeedPassword);
		
		assertThat(matches).isTrue();
	}
}
