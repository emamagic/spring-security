package com.emamagic.spring_security.config.provider;

import com.emamagic.spring_security.config.authentication.CustomAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomProvider implements AuthenticationProvider {

    @Value("security_token")
    private final String securityToken;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var authReq = (CustomAuthentication) authentication;
        String tokenReq = (String) authReq.getCredentials();
        if (tokenReq.equals(securityToken)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername((String) authReq.getPrincipal());
            return new CustomAuthentication(userDetails);
        }
        throw new BadCredentialsException("Oh, No!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.isAssignableFrom(authentication);
    }
}
