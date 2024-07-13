package com.emamagic.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetails provideUserDetails() {
        return User.withUsername("emamagic")
                .password("{noop}1234")
                .build();
    }

    @Bean
    public UserDetailsService provideUserDetailsService(UserDetails user) {
        var uds = new InMemoryUserDetailsManager();
        uds.createUser(user);
        return uds;
    }

//    @Bean
//    public PasswordEncoder providePasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

}
