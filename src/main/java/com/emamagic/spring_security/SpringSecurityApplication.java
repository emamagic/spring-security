package com.emamagic.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. implement our custom Authentication
 * 2. implementing custom -> AuthenticationManager, AuthenticationProvider, Authentication
 * 3. exception handling for filters and logging
 * */

/**
 * tip: when you create UserDetailsService and UserDetails,
 * 		Spring Security creates AuthenticationManager and AuthenticationProvider by default
 * */
@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
