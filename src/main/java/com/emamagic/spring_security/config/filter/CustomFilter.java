package com.emamagic.spring_security.config.filter;

import com.emamagic.spring_security.config.authentication.CustomAuthentication;
import com.emamagic.spring_security.config.manager.CustomAuthenticationManger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManger authenticationManager;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization").split(" ")[1];
        if (token != null && !token.isBlank()) {
            var auth = authenticationManager.authenticate(new CustomAuthentication(token));
            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            }
        }
    }
}
