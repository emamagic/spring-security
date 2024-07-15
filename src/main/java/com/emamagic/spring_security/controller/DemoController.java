package com.emamagic.spring_security.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @PreAuthorize("hasAuthority('write')")
    @GetMapping("/test1")
    public String test1() {
        return "test 1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test 2";
    }

    /** in method authorization we can access request info either */
    @GetMapping("/test3/{smth}")
    @PreAuthorize(
            """
            (#something == authentication.name) or
             hasAnyAuthority('read', 'write')
            """
    ) // authentication from SecurityContext
    public String test3(@PathVariable("smth") String something) {
        return "test3";
    }

    @GetMapping("/test4")
    @PreAuthorize("@demo4ConditionEvaluator.condition()")
    public String test4() {
        return "test4";
    }

    @GetMapping("/test5")
    @PostAuthorize("returnObject == 'test5'") // it is mainly used when we want to restrict the access to some returned value
    public String test5() { // never use it with methods that change data
        System.out.println(":)");
        return "test5";
    }

    @GetMapping("/test6")
    @PreFilter("filterObject.contains('a')") // works with either array or collection
    public String test6(@RequestBody List<String> values) {
        System.out.println("Values : " + values);
        return "test6";
    }

    @GetMapping("/test7")
    @PostFilter("filterObject.contains('a')") // works with either array or collection
    public List<String> test7() {
        return List.of("abcd", "wef", "qssz", "sdfsd"); // it does not work duo to List.of() create a immutable collection
    }


}
