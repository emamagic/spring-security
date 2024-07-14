package com.emamagic.spring_security.config.authentication;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
@ToString
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public ApiKeyAuthenticationToken(String token) {
        super(null);
        this.token = token;
    }

}
