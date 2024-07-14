package com.emamagic.spring_security.config.filter;

import com.emamagic.spring_security.config.authentication.ApiKeyAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || header.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] auth = header.split(" ");
        String token = auth[1];
        if (auth[0].equals("Token") && token != null && !token.isBlank() && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                var authentication = authenticationManager.authenticate(new ApiKeyAuthenticationToken(token));
                if (authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (AuthenticationException e) {
                log.error("AuthenticationException : {}", e.getLocalizedMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
