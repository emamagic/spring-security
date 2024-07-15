package com.emamagic.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. using @PreAuthorize()  method level authentication [previous we used endpoint level authorization]
 * 2. @PreAuthorize() @PostAuthorize() @PreFilter() @PostFilter()
 * 3. endpoint(filter) level does on filters before controllers but method level does on controller (ASPECT)
 * */

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
