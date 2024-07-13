package com.emamagic.spring_security.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityContextLoggingFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityContextLoggingFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Log SecurityContext before processing the request
        logger.info("Before chain.doFilter: SecurityContext={}", SecurityContextHolder.getContext());

        // Continue processing the request
        chain.doFilter(request, response);

        // Log SecurityContext after processing the request
        logger.info("After chain.doFilter: SecurityContext={}", SecurityContextHolder.getContext());
    }
}
