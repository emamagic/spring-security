package com.emamagic.spring_security.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(SecurityContext securityContext) {
        return "hello ".concat(securityContext.getAuthentication().toString());
    }

}
