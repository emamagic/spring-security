package com.emamagic.spring_security.security;

import org.springframework.stereotype.Component;

@Component
public class Demo4ConditionEvaluator {

    public boolean condition() {
        return true; // your complex authorization condition
    }
}
