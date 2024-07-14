package com.emamagic.spring_security.config;

import com.emamagic.spring_security.config.exception.AuthenticationException;
import com.emamagic.spring_security.config.filter.ApiKeyAuthenticationFilter;
import com.emamagic.spring_security.config.provider.ApiKeyAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configuration(
            HttpSecurity http,
            ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/test1", "/test1.1").authenticated()
                        .requestMatchers("/test2").hasAuthority("read")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                .addFilterBefore(apiKeyAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(new AuthenticationException())
                )
                .build();

        // matcher method + authorization rule
        // 1. which matcher methods should you use and how ( anyRequest(), mvcMatchers(), antMatchers(), regexMatchers() )
        // 2. how to apply different authorization roles
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails emaUser = User.withUsername("ema")
                .password("{noop}1234")
                .authorities("read")
                .build();
        UserDetails billUser = User.withUsername("bill")
                .password("{noop}1234")
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(emaUser, billUser);
    }

    @Bean
    public DaoAuthenticationProvider basicAuthenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider, ApiKeyAuthenticationProvider apiKeyAuthenticationProvider) {
        return new ProviderManager(daoAuthenticationProvider, apiKeyAuthenticationProvider);
    }


}
