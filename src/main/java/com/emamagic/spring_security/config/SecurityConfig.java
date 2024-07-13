package com.emamagic.spring_security.config;

import com.emamagic.spring_security.config.filter.CustomFilter;
import com.emamagic.spring_security.config.filter.ExceptionHandlerFilter;
import com.emamagic.spring_security.config.filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configuration(
            HttpSecurity http,
            CustomFilter customFilter,
            ExceptionHandlerFilter exceptionHandlerFilter,
            LoggingFilter loggingFilter) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .authenticated())
                .addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, CustomFilter.class)
                .addFilterBefore(loggingFilter, ExceptionHandlerFilter.class)
                .build();
    }


//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return  new BCryptPasswordEncoder();
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(UserService userSvc, BCryptPasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userSvc);
//        authProvider.setPasswordEncoder(passwordEncoder);
//        return authProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(
//            HttpSecurity http,
//            CustomAuthenticationProvider authProvider) throws Exception {
//
//        AuthenticationManagerBuilder authenticationManagerBuilder = http
//                .getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationProvider provider) {
//        return new ProviderManager(provider);
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }


}
