package com.easyshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.easyshop.common.entity", "com.easyshop.admin.user", "com.easyshop.admin.category"})
public class EasyShopBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyShopBackEndApplication.class, args);
	}

}
