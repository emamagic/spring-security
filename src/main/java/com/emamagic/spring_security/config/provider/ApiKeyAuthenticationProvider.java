package com.emamagic.spring_security.config.provider;

import com.emamagic.spring_security.config.authentication.ApiKeyAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Value("${secret_token}")
    private String secretToken;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var authReq = (ApiKeyAuthenticationToken) authentication;
        String tokenReq = authReq.getToken();
        if (tokenReq.equals(secretToken)) {
            // userDetailSvc.loadUserDetailsService()
            var auth = new ApiKeyAuthenticationToken(null);
            auth.setAuthenticated(true);
            return auth;
        }
        throw new BadCredentialsException("Oh, No! Token is not Valid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.equals(authentication);
    }
}
