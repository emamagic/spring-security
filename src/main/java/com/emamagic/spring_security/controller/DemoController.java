package com.emamagic.spring_security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "hello ".concat(authentication.getName());
    }

}
