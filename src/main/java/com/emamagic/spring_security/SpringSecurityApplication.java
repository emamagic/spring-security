package com.emamagic.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. using custom Authentication, and default Authentication at the same time
 * 2. using AbstractAuthenticationToken instead of Authentication
 * 3. AbstractAuthenticationProcessingFilter -> custom authentication base on custom url [UsernamePasswordAuthentication inherited from this filter]
 * */

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
