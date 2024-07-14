package com.emamagic.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/test1")
    public String test1() {
        return "test 1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test 2";
    }

}
