//package com.emamagic.spring_security.config.filter;
//
//import com.emamagic.userservice.entity.UserEntity;
//import com.emamagic.userservice.model.AuthenticationRequest;
//import com.emamagic.userservice.service.IJWTService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Component
//public class UsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final IJWTService jwtSvc;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        try {
//            AuthenticationRequest userRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
//            return getAuthenticationManager()
//                    .authenticate(new UsernamePasswordAuthenticationToken(
//                            userRequest.getUsername(),
//                            userRequest.getPassword()
//                    ));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        UserEntity userEntity = (UserEntity) authResult.getPrincipal();
//        String token = jwtSvc.generateToken(userEntity);
//
//        response.addHeader("token", token);
//        response.addHeader("userId", userEntity.getId());
//    }
//}
